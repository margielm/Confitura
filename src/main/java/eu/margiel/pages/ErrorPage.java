package eu.margiel.pages;

import org.wicketstuff.annotation.mount.MountPath;

import eu.margiel.pages.confitura.BaseWebPage;

@MountPath(path = "404")
public class ErrorPage extends BaseWebPage {
	@Override
	public boolean isErrorPage() {
		return true;
	}

	@Override
	public boolean isVersioned() {
		return false;
	}

}
