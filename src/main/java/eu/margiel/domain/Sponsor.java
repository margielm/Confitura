package eu.margiel.domain;

import eu.margiel.pages.confitura.c4p.SubfolderPhotoProvider;

import org.apache.wicket.markup.html.form.upload.FileUpload;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Transient;

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

    private Long money;

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

    public Long getMoney() {
        return money;
    }
}
