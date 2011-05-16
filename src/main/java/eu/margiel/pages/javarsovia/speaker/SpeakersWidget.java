package eu.margiel.pages.javarsovia.speaker;

import static eu.margiel.utils.Components.*;
import static eu.margiel.utils.Models.*;

import java.util.Collections;
import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import eu.margiel.components.StaticImage;
import eu.margiel.domain.Speaker;
import eu.margiel.repositories.SpeakerRepository;

@SuppressWarnings("serial")
public class SpeakersWidget extends Panel {
	@SpringBean
	private SpeakerRepository repository;

	public SpeakersWidget(String id) {
		super(id);
		add(createSpeakerList(getSpeakers()));
	}

	private List<Speaker> getSpeakers() {
		List<Speaker> speakers = repository.readWithAcceptedPresentations();
		Collections.shuffle(speakers);
		while (speakers.size() < 4)
			speakers.add(new Speaker());
		return speakers.subList(0, 4);
	}

	private ListView<Speaker> createSpeakerList(List<Speaker> speakers) {
		return new ListView<Speaker>("speaker", speakers) {

			@Override
			protected void populateItem(ListItem<Speaker> item) {
				Speaker speaker = item.getModelObject();
				item.add(createLinkWithPhoto(speaker));
				item.add(label("name", speaker.getFullName()));
			}

			private ExternalLink createLinkWithPhoto(Speaker speaker) {
				ExternalLink link = getLinkTo(speaker);
				link.add(new StaticImage("photo", getPathTo(speaker))
						.add(new AttributeModifier("alt", model(speaker.getFullName()))));
				return link;
			}

			private String getPathTo(Speaker speaker) {
				if (speaker.isNew())
					return "/files/photos/speaker.png";
				else
					return speaker.getPathToPhoto();
			}

			private ExternalLink getLinkTo(Speaker speaker) {
				return new ExternalLink("link", getUrl(speaker));
			}

			private String getUrl(Speaker speaker) {
				if (speaker.isNew())
					return "#";
				else
					return "/" + urlFor(ListSpeakerPage.class, new PageParameters()) + "#" + speaker.getFullName();
			}

		};
	}
}
