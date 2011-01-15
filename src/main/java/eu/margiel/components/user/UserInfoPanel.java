package eu.margiel.components.user;

import static eu.margiel.utils.Components.*;

import java.util.List;

import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.spring.injection.annot.SpringBean;

import eu.margiel.components.RedirectLink;
import eu.margiel.components.nogeneric.Link;
import eu.margiel.domain.Presentation;
import eu.margiel.domain.Speaker;
import eu.margiel.pages.javarsovia.c4p.AddPresentationPage;
import eu.margiel.pages.javarsovia.c4p.RegisterSpeakerPage;
import eu.margiel.pages.javarsovia.c4p.SpeakerPhotoProvider;
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
		add(new Image("photo", new SpeakerPhotoProvider().getPhotoFor(speaker)));
		add(label("firstName", speaker.getFirstName()));
		add(label("lastName", speaker.getLastName()));
		add(label("eMail", speaker.getMail()));
		add(label("webPage", speaker.getWebPage()));
		add(label("twitter", speaker.getTwitter()));
		add(richLabel("bio", speaker.getBio()));
		add(new RedirectLink("edit", RegisterSpeakerPage.class) {
			@Override
			public boolean isVisible() {
				return UserInfoPanel.this.editable;
			}
		});
		add(new RedirectLink("add_presentation", AddPresentationPage.class) {
			@Override
			public boolean isVisible() {
				return UserInfoPanel.this.editable;
			}
		});
		add(new PresentationGrid(speaker.getPresentations()));
	}

	private class PresentationGrid extends DataView<Presentation> {

		private PresentationGrid(List<Presentation> presentations) {
			super("presentations", new ListDataProvider<Presentation>(presentations));
		}

		@Override
		protected void populateItem(Item<Presentation> item) {
			final Presentation presentation = item.getModelObject();
			item.add(label("title", presentation.getTitle()));
			item.add(new Link("edit") {
				@Override
				public void onClick() {
					setResponsePage(new AddPresentationPage(presentation));
				}

				@Override
				public boolean isVisible() {
					return UserInfoPanel.this.editable;
				}
			});
			item.add(new Link("delete") {
				@Override
				public void onClick() {
					repository.delete(presentation);
					setResponsePage(ViewSpeakerPage.class);
				}

				@Override
				public boolean isVisible() {
					return UserInfoPanel.this.editable;
				}
			});
		}
	}
}
