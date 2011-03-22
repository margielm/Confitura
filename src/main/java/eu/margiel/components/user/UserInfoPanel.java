package eu.margiel.components.user;

import static eu.margiel.utils.Components.*;

import java.util.List;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.spring.injection.annot.SpringBean;

import eu.margiel.Javarsovia;
import eu.margiel.JavarsoviaSession;
import eu.margiel.components.DeleteLink;
import eu.margiel.components.RedirectLink;
import eu.margiel.components.StaticImage;
import eu.margiel.domain.Presentation;
import eu.margiel.domain.Speaker;
import eu.margiel.pages.javarsovia.c4p.AddPresentationPage;
import eu.margiel.pages.javarsovia.c4p.ChangePasswordPage;
import eu.margiel.pages.javarsovia.c4p.EditSpeakerPage;
import eu.margiel.pages.javarsovia.c4p.SpeakerPhotoProvider;
import eu.margiel.pages.javarsovia.c4p.ViewSpeakerPage;
import eu.margiel.repositories.PresentationRepository;

@SuppressWarnings("serial")
public class UserInfoPanel extends Panel {
	@SpringBean
	private PresentationRepository repository;
	private transient SpeakerPhotoProvider provider = new SpeakerPhotoProvider();
	private final boolean editable;

	public UserInfoPanel(String id, Speaker speaker, boolean editable) {
		super(id);
		this.editable = editable;
		add(new StaticImage("photo", provider.getPathTo(speaker)));
		add(label("firstName", speaker.getFirstName()));
		add(label("lastName", speaker.getLastName()));
		add(label("eMail", speaker.getMail()));
		add(label("webPage", speaker.getWebPage()));
		add(label("twitter", speaker.getTwitter()));
		add(richLabel("bio", speaker.getBio()));
		add(redirectLink("edit", EditSpeakerPage.class).setVisible(editable));
		add(redirectLink("changePassword", ChangePasswordPage.class).setVisible(editable));
		add(redirectLink("add_presentation", AddPresentationPage.class).setVisible(editable));
		add(new RedirectLink("logout", Javarsovia.get().getHomePage()) {
			@Override
			public void onClick() {
				JavarsoviaSession.get().invalidateNow();
				super.onClick();
			}

		}.setVisible(editable));
		add(new PresentationGrid(speaker.getPresentations()));
	}

	private class PresentationGrid extends DataView<Presentation> {

		private PresentationGrid(List<Presentation> presentations) {
			super("presentations", new ListDataProvider<Presentation>(presentations));
		}

		@Override
		protected void populateItem(Item<Presentation> item) {
			Presentation presentation = item.getModelObject();
			item.add(label("title", presentation.getTitle()));
			item.add(new RedirectLink("edit", presentation, AddPresentationPage.class).setVisible(editable));
			item.add(new DeleteLink(presentation, repository, ViewSpeakerPage.class).setVisible(editable));
		}
	}
}
