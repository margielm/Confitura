package eu.margiel.admin;

import org.wicketstuff.annotation.mount.MountPath;

import eu.margiel.domain.MenuItem;

@MountPath(path = "admin/horizontalMenu")
public class DefineHorizontalMenuPage extends DefineMenuBasePage {

	@Override
	protected MenuItem getMenu() {
		return menuService.getHorizontalMenu();
	}

}
