package eu.margiel.pages.confitura.speaker;

import static eu.margiel.utils.Components.*;
import static eu.margiel.utils.Models.*;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import eu.margiel.components.StaticImage;
import eu.margiel.domain.Presentation;
import eu.margiel.domain.Speaker;
import eu.margiel.pages.confitura.BaseWebPage;
import eu.margiel.repositories.SpeakerRepository;

@MountPath(path = "speakers")
public class ListSpeakerPage extends BaseWebPage {
	@SpringBean
	private SpeakerRepository repository;

	public ListSpeakerPage() {
		add(new SpeakersList("speaker", repository.readWithAcceptedPresentations()));
	}

	@SuppressWarnings("serial")
	private final class SpeakersList extends ListView<Speaker> {
		private SpeakersList(String id, List<Speaker> list) {
			super(id, list);
		}

		@Override
		protected void populateItem(ListItem<Speaker> item) {
			Speaker speaker = item.getModelObject();
			item.add(new AttributeModifier("class", getCssClass(item)));
			item.add(new AttributeModifier("id", model(speaker.getFullName())));
			item.add(label("name", speaker.getFullName()));
			item.add(getLinkTo(speaker.getAcceptedPresentations().get(0)));
			item.add(getLink("www", speaker.getWebPage()));
			item.add(getLink("twitter", speaker.getTwitterUrl()));
			item.add(richLabel("bio", speaker.getBio()));
			item.add(new StaticImage("photo", speaker.getPathToPhoto()));
		}

		private Component getLink(String id, String link) {
			return new ExternalLink(id, model(link), model(link)).setVisible(StringUtils.isNotBlank(link));
		}

		private Model<String> getCssClass(ListItem<?> item) {
			return model(item.getIndex() % 2 == 0 ? "odd" : "");
		}

		private ExternalLink getLinkTo(Presentation presentation) {
			return new ExternalLink("title", getUrl(presentation), presentation.getTitle());
		}

		private String getUrl(Presentation presentation) {
			return "/" + urlFor(ListPresentationPage.class, new PageParameters()) + "#" + presentation.getId();
		}
	}
}
