package eu.margiel.pages.admin.menu;

import static eu.margiel.utils.Components.*;
import static eu.margiel.utils.Models.*;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.apache.wicket.ajax.calldecorator.AjaxPreprocessingCallDecorator;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import eu.margiel.components.menu.MenuLinks;
import eu.margiel.domain.MenuItem;
import eu.margiel.domain.MenuLinkItem;
import eu.margiel.pages.admin.AdminBasePage;
import eu.margiel.repositories.MenuRepository;

@SuppressWarnings("serial")
public abstract class DefineMenuBasePage extends AdminBasePage {

	@SpringBean
	protected MenuRepository repository;
	@SpringBean
	protected MenuLinks menuItemList;

	protected Form<MenuItem> form = new Form<MenuItem>("form");
	protected MenuItem menu = getMenu();
	private ListView<MenuItem> itemsView;

	public DefineMenuBasePage() {
		initComponents();
	}

	protected void initComponents() {
		createAddItemButton();
		createSaveButton();
		initMenuItems();
		add(form);
	}

	protected void initMenuItems() {
		itemsView = new ListView<MenuItem>("menu_item", menu.getChildren()) {
			@Override
			protected void populateItem(final ListItem<MenuItem> item) {
				createManageItemButtons("item", item);
			}
		};
		form.add(itemsView);
	}

	protected void createSaveButton() {
		form.add(new AjaxButton("save", form) {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				menu = repository.save(menu);
				itemsView.setModelObject(menu.getChildren());
				target.addComponent(form);
			}
		});
	}

	void createManageItemButtons(String id, final ListItem<MenuItem> item) {
		createItemName(id, item);
		createRemoveButton(id, item);
		createUpButton(id, item);
		createDownButton(id, item);
		createPublishButton(id, item);
		createDropDown(id, item);
	}

	private void createDropDown(String id, final ListItem<MenuItem> item) {
		PropertyModel<MenuLinkItem> model = propertyModel(item.getModelObject(), "linkItem");
		DropDownChoice<MenuLinkItem> comboBox = dropDown(id + "_content", model, menuItemList.getAllItems());
		comboBox.setChoiceRenderer(new ChoiceRenderer<MenuLinkItem>("title", "title"));
		item.add(comboBox);
	}

	private void createPublishButton(String id, final ListItem<MenuItem> item) {
		AbstractReadOnlyModel<String> labelModel = new AbstractReadOnlyModel<String>() {

			@Override
			public String getObject() {
				return item.getModelObject().isPublished() + "";
			}
		};
		AjaxButton publishButton = new AjaxButton(id + "_publish", labelModel, form) {

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				item.getModelObject().togglePublished();
				target.addComponent(form);
			}

		};
		item.add(publishButton);

	}

	private void createRemoveButton(String id, final ListItem<MenuItem> item) {
		item.add(new RemoveButton(id + "_remove", form, item));
	}

	protected MarkupContainer createAddItemButton() {
		return form.add(new AjaxButton("item_add", form) {

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				menu.addEmptyChild();
				target.addComponent(form);
			}

		});
	}

	private MarkupContainer createItemName(String id, final ListItem<MenuItem> item) {
		return item.add(new TextField<String>(id + "_name", new PropertyModel<String>(item.getModelObject(), "name")));
	}

	private MarkupContainer createUpButton(String id, final ListItem<MenuItem> item) {
		return item.add(new AjaxButton(id + "_up", form) {

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				item.getModelObject().up();
				target.addComponent(form);
			}
		});
	}

	private MarkupContainer createDownButton(String id, final ListItem<MenuItem> item) {
		return item.add(new AjaxButton(id + "_down", form) {

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				item.getModelObject().down();
				target.addComponent(form);
			}
		});
	}

	protected abstract MenuItem getMenu();

	private final class RemoveButton extends AjaxButton {
		private final ListItem<MenuItem> item;

		private RemoveButton(String id, Form<?> form, ListItem<MenuItem> item) {
			super(id, form);
			this.item = item;
		}

		@Override
		protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
			item.getModelObject().remove();
			target.addComponent(form);
		}

		@Override
		protected IAjaxCallDecorator getAjaxCallDecorator() {
			return new AjaxPreprocessingCallDecorator(super.getAjaxCallDecorator()) {
				private static final long serialVersionUID = 7495281332320552876L;

				@Override
				public CharSequence preDecorateScript(CharSequence script) {
					return "if(!confirm('Na pewno usunąć?')) return false;" + script;
				}
			};
		}
	}

}
