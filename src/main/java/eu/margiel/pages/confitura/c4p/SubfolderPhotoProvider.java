package eu.margiel.pages.confitura.c4p;

import org.apache.wicket.markup.html.form.upload.FileUpload;

import eu.margiel.domain.AbstractEntity;

public class SubfolderPhotoProvider extends PhotoProvider {
	private String subfolder;

	public SubfolderPhotoProvider(String subfolder) {
		super();
		this.subfolder = subfolder;
	}

	public void savePhoto(FileUpload fileUpload, AbstractEntity entity) {
		if (fileUpload != null)
			saveFile(fileUpload, getMainFolder(), getFileNameFor(entity));
	}

	public String getPathTo(AbstractEntity entity) {
		return getPathTo(getFileNameFor(entity));
	}

	private String getFileNameFor(AbstractEntity entity) {
		return entity.getId() + ".jpg";
	}

	@Override
	protected String getMainFolder() {
		return ROOT_FOLDER + "/" + subfolder;
	}

}
