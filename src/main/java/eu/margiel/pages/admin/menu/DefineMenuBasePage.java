package eu.margiel.pages.admin.menu;

import static eu.margiel.utils.Components.*;
import static eu.margiel.utils.Models.*;

import java.util.List;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import eu.margiel.domain.DynamicContent;
import eu.margiel.domain.MenuItem;
import eu.margiel.domain.OfferContent;
import eu.margiel.pages.admin.AdminBasePage;
import eu.margiel.pages.admin.MenuItemList;
import eu.margiel.services.MenuService;
import eu.margiel.services.StaticContentService;

@SuppressWarnings("serial")
public abstract class DefineMenuBasePage extends AdminBasePage {

	@SpringBean
	protected MenuService menuService;
	@SpringBean
	protected StaticContentService contentService;

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
				menu = menuService.save(menu);
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
		// PropertyModel<DynamicContent> model =
		// propertyModel(item.getModelObject(), "content");
		// DropDownChoice<DynamicContent> contentDropDown = dropDown(id +
		// "_content", model, getDynamicContents());
		// contentDropDown.setChoiceRenderer(new
		// ChoiceRenderer<DynamicContent>("name", "name"));
		// if (item.getModelObject().getContent() != null)
		// contentDropDown.setDefaultModelObject(item.getModelObject().getContent());
		PropertyModel<String> model = propertyModel(item.getModelObject(), "linkTo");
		DropDownChoice<String> comboBox = dropDown(id + "_content", model, MenuItemList.all());
		// DropDownChoice<String> comboBox = new DropDownChoice<String>(id +
		// "_content", MenuItemList.all());
		item.add(comboBox);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<DynamicContent> getDynamicContents() {
		List dynamicContents = contentService.getAll();
		dynamicContents.addAll(OfferContent.getAll());
		return dynamicContents;
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
		item.add(new AjaxButton(id + "_remove", form) {

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				item.getModelObject().remove();
				target.addComponent(form);
			}

		});
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

}
