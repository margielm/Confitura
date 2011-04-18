package eu.margiel.components.user;

import static eu.margiel.utils.Components.*;
import static eu.margiel.utils.Models.*;

import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import eu.margiel.Javarsovia;
import eu.margiel.JavarsoviaSession;
import eu.margiel.components.DeleteLink;
import eu.margiel.components.RedirectLink;
import eu.margiel.components.StaticImage;
import eu.margiel.domain.Presentation;
import eu.margiel.domain.Speaker;
import eu.margiel.pages.admin.c4p.presentation.ViewPresentationPage;
import eu.margiel.pages.javarsovia.c4p.AddPresentationPage;
import eu.margiel.pages.javarsovia.c4p.ChangePasswordPage;
import eu.margiel.pages.javarsovia.c4p.EditSpeakerPage;
import eu.margiel.pages.javarsovia.c4p.ViewSpeakerPage;
import eu.margiel.repositories.PresentationRepository;

@SuppressWarnings("serial")
public class UserInfoPanel extends Panel {
	@SpringBean
	private PresentationRepository repository;
	private final boolean editable;

	public UserInfoPanel(String id, Speaker speaker, boolean editable) {
		super(id);
		this.editable = editable;
		add(new StaticImage("photo", speaker.getPathToPhoto()));
		add(label("firstName", speaker.getFirstName()));
		add(label("lastName", speaker.getLastName()));
		add(label("eMail", speaker.getMail()));
		add(new ExternalLink("webPage", speaker.getWebPage(), speaker.getWebPage()));
		add(new ExternalLink("twitter", speaker.getTwitterUrl(), speaker.getTwitter()));
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
			item.add(new AttributeModifier("class", getCssClass(item)));
			item.add(label("title", presentation.getTitle()));
			item.add(new RedirectLink("view", presentation, ViewPresentationPage.class).setVisible(!editable));
			item.add(new RedirectLink("edit", presentation, AddPresentationPage.class).setVisible(editable));
			item.add(new DeleteLink(presentation, repository, ViewSpeakerPage.class).setVisible(editable));
		}

		private Model<String> getCssClass(Item<?> item) {
			return model(item.getIndex() % 2 == 0 ? "odd" : "");
		}
	}

}
