package eu.margiel.pages.javarsovia.c4p;

import org.apache.wicket.markup.html.form.upload.FileUpload;

import eu.margiel.domain.User;

public class SpeakerPhotoProvider extends PhotoProvider {

	public void savePhoto(FileUpload fileUpload, User speaker) {
		saveFile(fileUpload, ROOT_FOLDER + "/speakers", getFileNameFor(speaker));
	}

	public String getPathTo(User speaker) {
		return getPathTo(getFileNameFor(speaker));
	}

	private String getFileNameFor(User speaker) {
		return speaker.getId() + ".jpg";
	}

	@Override
	protected String getMainFolder() {
		return ROOT_FOLDER + "/speakers";
	}

}
