package eu.margiel.pages.admin.menu;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.wicketstuff.annotation.mount.MountPath;

import eu.margiel.domain.MainMenu;
import eu.margiel.domain.MenuItem;

@SuppressWarnings("serial")
@MountPath(path = "admin/mainMenu")
public class DefineMainMenuPage extends DefineMenuBasePage {

	@Override
	protected MainMenu getMenu() {
		return menuService.getMainMenu();
	}

	@Override
	void createManageItemButtons(String id, final ListItem<MenuItem> item) {
		super.createManageItemButtons(id, item);
		createAddSubitemButton(item);
		item.add(new ListView<MenuItem>("subitems", item.getModelObject().getChildren()) {
			@Override
			protected void populateItem(final ListItem<MenuItem> subitem) {
				DefineMainMenuPage.super.createManageItemButtons("subitem", subitem);
			}
		});
	}

	private MarkupContainer createAddSubitemButton(final ListItem<MenuItem> item) {
		return item.add(new AjaxButton("subitem_add", form) {

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				item.getModelObject().addEmptyChild();
				target.addComponent(form);
			}

		});
	}

}
