package eu.margiel.pages.javarsovia.c4p;

import org.apache.wicket.Application;
import org.apache.wicket.Resource;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.util.file.File;
import org.apache.wicket.util.file.Folder;

import eu.margiel.Javarsovia;
import eu.margiel.domain.Speaker;

public class SpeakerPhotoProvider {

	private static final String IMAGE_NAME = "photo.jpg";

	public void savePhoto(FileUpload fileUpload, Speaker speaker) {
		try {
			String photoPath = getFolderFor(speaker);
			Folder folder = new Folder(getApp().getMainFilesFolder(), photoPath);
			folder.mkdirs();
			File file = new File(folder, IMAGE_NAME);
			file.createNewFile();
			fileUpload.writeTo(file);
			getApp().addImageToResources(getPathTo(speaker), file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getFolderFor(Speaker speaker) {
		return "speakers/" + speaker.getId();
	}

	private String getPathTo(Speaker speaker) {
		return getFolderFor(speaker) + "/" + IMAGE_NAME;
	}

	public Resource getPhotoFor(Speaker speaker) {
		return getResource(getPathTo(speaker));
	}

	private Resource getResource(String path) {
		return getApp().getSharedResources().get(Application.class, path, null, null, false);
	}

	private Javarsovia getApp() {
		return Javarsovia.get();
	}

}
