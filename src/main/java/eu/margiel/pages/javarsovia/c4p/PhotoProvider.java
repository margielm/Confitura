package eu.margiel.pages.javarsovia.c4p;

import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.util.file.File;
import org.apache.wicket.util.file.Folder;

public abstract class PhotoProvider {

	protected static final String ROOT_FOLDER = "files";

	protected void saveFile(FileUpload fileUpload, String photoPath, String fileName) {
		try {
			Folder folder = new Folder(photoPath);
			folder.mkdirs();
			File file = new File(folder, fileName);
			file.createNewFile();
			fileUpload.writeTo(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected abstract String getMainFolder();

	protected String getPathTo(String fileName) {
		return "/" + getMainFolder() + "/" + fileName;
	}

}