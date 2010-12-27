package eu.margiel.components;

import static com.google.common.collect.Lists.*;
import static eu.margiel.utils.Components.*;
import static eu.margiel.utils.Models.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.ajax.markup.html.form.upload.UploadProgressBar;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.MultiFileUploadField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.file.Files;
import org.apache.wicket.util.file.Folder;
import org.apache.wicket.util.lang.Bytes;

import eu.margiel.components.nogeneric.Link;
import eu.margiel.utils.FileResource;
import eu.margiel.utils.ImageScaler;
import eu.margiel.utils.PhotosProvider;

@SuppressWarnings("serial")
public class UploadPhotosPanel extends Panel {
	private ListView<File> imagesList;
	private final PhotosProvider photosProvider;

	public UploadPhotosPanel(String id, PhotosProvider photosProvider) {
		super(id);
		this.photosProvider = photosProvider;
		add(new FeedbackPanel("uploadFeedback"));
		add(new FileUploadForm("photo_upload"));
		add(createImagesList());
	}

	private Component createImagesList() {
		WebMarkupContainer container = new WebMarkupContainer("images_container");
		container.setOutputMarkupId(true);
		imagesList = new PhotosList("images", getThumbnails());
		container.add(imagesList);
		return container;
	}

	private Component createUrlToPhoto(final File file) {
		return textField("photo_url", model(getUrlToFile(file)));
	}

	private Serializable getUrlToFile(File file) {
		return photosProvider.getPhotoURLFor(file);
	}

	private Link createRemoveFileLink(final File file) {
		return new Link("remove") {
			@Override
			public void onClick() {
				file.delete();
				getFullSizeImageFor(file).delete();
				imagesList.setModelObject(getThumbnails());
			}

		};
	}

	private File getFullSizeImageFor(File thumbnail) {
		return new File(getImagesFolder(), thumbnail.getName());
	}

	private List<File> getThumbnails() {
		return newArrayList(getThumbnailsFolder().listFiles());
	}

	private Folder getThumbnailsFolder() {
		return photosProvider.getPhotosFolder().getThumbnailsFolder();
	}

	private Folder getImagesFolder() {
		return photosProvider.getPhotosFolder().getPhotosFolder();
	}

	private final class PhotosList extends ListView<File> {
		private PhotosList(String id, List<? extends File> list) {
			super(id, list);
		}

		@Override
		protected void populateItem(ListItem<File> item) {
			File file = item.getModelObject();
			item.add(createRemoveFileLink(file));
			item.add(createUrlToPhoto(getFullSizeImageFor(file)));
			item.add(new Image("image", new FileResource(file)));
		}
	}

	private class FileUploadForm extends Form<Void> {

		List<FileUpload> images = newArrayList();

		public FileUploadForm(String name) {
			super(name);
			setMultiPart(true);
			add(new MultiFileUploadField("file_input", new PropertyModel<List<FileUpload>>(this, "images"), 10));
			setMaxSize(Bytes.kilobytes(10000));
			add(new UploadProgressBar("upload_progress", this));
		}

		@Override
		protected void onSubmit() {
			for (FileUpload image : images)
				saveImage(image);
			imagesList.setModelObject(getThumbnails());
		}

		private void saveImage(FileUpload upload) {
			File newFile = new File(getImagesFolder(), upload.getClientFileName());
			checkFileExists(newFile);
			try {
				doSaveFile(upload, newFile);
			} catch (Exception ex) {
				throw new IllegalStateException("Unable to write file", ex);
			}

		}

		private void doSaveFile(FileUpload upload, File newFile) throws IOException {
			newFile.createNewFile();
			upload.writeTo(newFile);
			addImageToResources(newFile);
			createAndSaveThumbnail(upload, newFile);
			UploadPhotosPanel.this.info("saved file: " + upload.getClientFileName());
		}

		private void addImageToResources(File newFile) {
			photosProvider.addImageToResources(newFile);
		}

		private void createAndSaveThumbnail(FileUpload upload, File newFile) throws IOException {
			ImageScaler imageScaler = new ImageScaler(newFile, 100);
			BufferedImage thumbnail = imageScaler.getThumbnail();
			File thumbnailFile = new File(getThumbnailsFolder(), upload.getClientFileName());
			ImageIO.write(thumbnail, "png", thumbnailFile);
		}
	}

	private void checkFileExists(File newFile) {
		if (newFile.exists()) {
			if (!Files.remove(newFile))
				throw new IllegalStateException("Unable to overwrite " + newFile.getAbsolutePath());
		}
	}

}
