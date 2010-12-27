package eu.margiel.domain;

import java.io.File;

import org.apache.wicket.util.file.Folder;

public class PhotosFolder {

	private Folder photosFolder;
	private Folder thumbnailsFolder;

	public PhotosFolder(Folder folder) {
		photosFolder = new Folder(folder, "photos");
		photosFolder.mkdirs();
		thumbnailsFolder = new Folder(folder, "thumbnails");
		thumbnailsFolder.mkdirs();
	}

	public File[] listPhotos() {
		return photosFolder.listFiles();
	}

	public File[] listThumbnails() {
		return thumbnailsFolder.listFiles();
	}

	public Folder getPhotosFolder() {
		return photosFolder;
	}

	public Folder getThumbnailsFolder() {
		return thumbnailsFolder;
	}

}
