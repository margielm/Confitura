package eu.margiel.pages.admin.sponsor;

import static eu.margiel.utils.Components.*;
import static eu.margiel.utils.Models.*;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;
import org.wicketstuff.annotation.strategy.MountMixedParam;

import eu.margiel.components.StaticImage;
import eu.margiel.domain.Sponsor;
import eu.margiel.domain.SponsorType;
import eu.margiel.pages.admin.AdminBasePage;
import eu.margiel.repositories.SponsorRepository;
import eu.margiel.utils.Models;

@MountPath(path = "admin/sponsors/edit")
@MountMixedParam(parameterNames = "id")
public class AddSponsorPage extends AdminBasePage {

	@SpringBean
	private SponsorRepository repository;

	public AddSponsorPage(PageParameters params) {
		initPage(repository.readByPrimaryKey(params.getAsInteger("id")));
	}

	public AddSponsorPage() {
		initPage(new Sponsor());
	}

	private void initPage(Sponsor sponsor) {
		add(new SponsorForm(sponsor));
	}

	@SuppressWarnings("serial")
	private final class SponsorForm extends Form<Void> {
		private FileUploadField fileUpload = new FileUploadField("logo");
		private final Sponsor sponsor;

		private SponsorForm(Sponsor sponsor) {
			super("form");
			this.sponsor = sponsor;
			add(new StaticImage("logoView", sponsor.getPathToPhoto()).setVisible(sponsor.isNotNew()));
			add(textField("name", propertyModel(sponsor, "name"), true));
			add(textField("webPage", propertyModel(sponsor, "webPage"), true));
			add(dropDown("type", Models.<String> propertyModel(sponsor, "type"), SponsorType.allShortNames())
					.setRequired(true));
			add(richEditor("desc", propertyModel(sponsor, "description"), true));
			add(richEditor("folderDesc", propertyModel(sponsor, "folderDescription")));
			add(fileUpload.setRequired(true));
			add(cancelLink(ListSponsorPage.class));
			add(new FeedbackPanel("feedback"));
		}

		@Override
		protected void onSubmit() {
			repository.save(sponsor);
			sponsor.savePhoto(fileUpload.getFileUpload());
			setResponsePage(ListSponsorPage.class);
		}
	}
}
