package eu.margiel.pages;

import org.wicketstuff.annotation.mount.MountPath;

import eu.margiel.pages.javarsovia.BaseWebPage;

@MountPath(path = "error")
public class PageNotFound extends BaseWebPage {
	@Override
	public boolean isErrorPage() {
		return true;
	}

	@Override
	public boolean isVersioned() {
		return false;
	}

}
