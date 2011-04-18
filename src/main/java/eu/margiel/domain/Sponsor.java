package eu.margiel.domain;

import javax.persistence.Entity;
import javax.persistence.Lob;

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
	private transient SubfolderPhotoProvider provider = new SubfolderPhotoProvider("sponsors");

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
		return provider.getPathTo(this);
	}

	public void savePhoto(FileUpload fileUpload) {
		provider.savePhoto(fileUpload, this);
	}

	public String getDescription() {
		return description;
	}

}
