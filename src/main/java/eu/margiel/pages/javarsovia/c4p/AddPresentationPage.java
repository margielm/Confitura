package eu.margiel.pages.javarsovia.c4p;

import static eu.margiel.utils.Components.*;
import static eu.margiel.utils.Models.*;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.spring.injection.annot.SpringBean;

import eu.margiel.domain.Presentation;
import eu.margiel.domain.Speaker;
import eu.margiel.pages.javarsovia.BaseWebPage;
import eu.margiel.repositories.SpeakerRepository;

public class AddPresentationPage extends BaseWebPage {
	@SpringBean
	private SpeakerRepository repository;
	private Speaker speaker = new Speaker();
	private Presentation presentation = new Presentation();

	@SuppressWarnings("serial")
	public AddPresentationPage() {
		Form<Void> form = new Form<Void>("form") {
			@Override
			protected void onSubmit() {
				repository.save(speaker.presentation(presentation));
			}
		};
		form.add(textField("firstName", propertyModel(speaker, "firstName")));
		form.add(textField("lastName", propertyModel(speaker, "lastName")));
		form.add(textField("eMail", propertyModel(speaker, "eMail")));
		form.add(textField("webPage", propertyModel(speaker, "webPage")));
		form.add(textField("twitter", propertyModel(speaker, "twitter")));
		form.add(richEditorSimple("bio", propertyModel(speaker, "bio")));
		form.add(textField("title", propertyModel(presentation, "title")));
		form.add(richEditorSimple("description", propertyModel(presentation, "description")));
		add(form);
	}
}
