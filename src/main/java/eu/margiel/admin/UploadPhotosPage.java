package eu.margiel.admin;

import org.apache.wicket.PageParameters;

import eu.margiel.components.UploadPhotosPanel;
import eu.margiel.utils.GalleryPhotosProvider;

public class UploadPhotosPage extends AdminBasePage {

	public UploadPhotosPage(PageParameters parameters) {
		add(new UploadPhotosPanel("upload_photos", new GalleryPhotosProvider()));
	}
}
