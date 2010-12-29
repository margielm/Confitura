package eu.margiel.utils;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import org.apache.wicket.Application;
import org.apache.wicket.util.file.Folder;

import com.google.common.collect.Lists;

import eu.margiel.Javarsovia;
import eu.margiel.domain.PhotosFolder;

@SuppressWarnings("serial")
public abstract class PhotosProvider implements Serializable {

	public PhotosProvider() {
		super();
	}

	public PhotosFolder getPhotosFolder() {
		Folder folder = new Folder(getApp().getMainFilesFolder(), getSubpath());
		folder.mkdirs();
		return new PhotosFolder(folder);
	}

	public List<String> getPhotosURL() {
		return getUrlsTo(getPhotosFolder().listPhotos());
	}

	public List<String> getThumbnailsURL() {
		return getUrlsTo(getPhotosFolder().listThumbnails());
	}

	private List<String> getUrlsTo(File[] files) {
		List<String> urls = Lists.newArrayList();
		for (File file : files)
			urls.add(getPhotoURLFor(file));
		return urls;
	}

	protected Javarsovia getApp() {
		return (Javarsovia) Application.get();
	}

	public String getPhotoURLFor(File file) {
		return getApp().getBaseImageUrl() + getSubpath() + "/" + file.getName();
	}

	abstract String getSubpath();

	public void addImageToResources(File file) {
		getApp().addImageToResources(getSubpath() + "/" + file.getName(), file);
	}

}