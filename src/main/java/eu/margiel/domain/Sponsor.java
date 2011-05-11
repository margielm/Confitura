package eu.margiel.domain;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Transient;

import org.apache.wicket.markup.html.form.upload.FileUpload;

import eu.margiel.pages.javarsovia.c4p.SubfolderPhotoProvider;

@SuppressWarnings("serial")
@Entity
public class Sponsor extends AbstractEntity {
	private String name;
	private String type;
	private String webPage;
	@Lob
	private String description;
	@SuppressWarnings("unused")
	@Lob
	private String folderDescription;
	@Transient
	private transient SubfolderPhotoProvider provider;

	public Sponsor() {
	}

	public Sponsor(String name, String webPage) {
		this.name = name;
		this.webPage = webPage;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getWebPage() {
		return webPage;
	}

	public String getPathToPhoto() {
		return getProvider().getPathTo(this);
	}

	public void savePhoto(FileUpload fileUpload) {
		getProvider().savePhoto(fileUpload, this);
	}

	public String getDescription() {
		return description;
	}

	private SubfolderPhotoProvider getProvider() {
		if (provider == null)
			provider = new SubfolderPhotoProvider("sponsors");
		return provider;
	}
}
