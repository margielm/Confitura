package eu.margiel.components;

import static com.google.common.collect.Lists.*;

import java.util.List;

import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.util.file.Folder;

import eu.margiel.pages.javarsovia.c4p.PhotoProvider;

public class GalleryPhotoProvider extends PhotoProvider {

	public void savePhoto(FileUpload fileUpload) {
		saveFile(fileUpload, getMainFolder(), fileUpload.getClientFileName());
	}

	@Override
	public String getMainFolder() {
		return ROOT_FOLDER + "/photos/";
	}

	public String getPathTo(java.io.File file) {
		return getPathTo(file.getName());
	}

	public List<java.io.File> listFiles() {
		return newArrayList(new Folder(getMainFolder()).listFiles());
	}
}
