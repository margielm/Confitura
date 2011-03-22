package eu.margiel.pages.admin;

import org.apache.wicket.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import eu.margiel.components.UploadPhotosPanel;

@MountPath(path = "admin/photos")
public class UploadPhotosPage extends AdminBasePage {

	public UploadPhotosPage(PageParameters parameters) {
		add(new UploadPhotosPanel("upload_photos"));
	}
}
