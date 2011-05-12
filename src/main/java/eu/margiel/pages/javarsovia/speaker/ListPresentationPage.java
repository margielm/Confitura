package eu.margiel.pages.javarsovia.speaker;

import static eu.margiel.utils.Components.*;
import static eu.margiel.utils.Models.*;

import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import eu.margiel.domain.Presentation;
import eu.margiel.pages.javarsovia.BaseWebPage;
import eu.margiel.repositories.PresentationRepository;

@MountPath(path = "presentations")
public class ListPresentationPage extends BaseWebPage {
	@SpringBean
	private PresentationRepository repository;

	public ListPresentationPage() {
		add(new SpeakersList("presentation", repository.readAllAccepted()));
	}

	@SuppressWarnings("serial")
	private final class SpeakersList extends ListView<Presentation> {
		private SpeakersList(String id, List<Presentation> list) {
			super(id, list);
		}

		@Override
		protected void populateItem(ListItem<Presentation> item) {
			Presentation presentation = item.getModelObject();
			item.add(new AttributeModifier("class", getCssClass(item)));
			item.add(new AttributeModifier("id", model(presentation.getId() + "")));
			item.add(label("title", presentation.getTitle()));
			item.add(getLinkToSpeaker(presentation));
			item.add(richLabel("desc", presentation.getDescription()));
		}

		private ExternalLink getLinkToSpeaker(Presentation presentation) {
			return new ExternalLink("speaker", getUrl(presentation), presentation.getSpeaker().getFullName());
		}

		private String getUrl(Presentation speaker) {
			return "/" + urlFor(ListSpeakerPage.class, new PageParameters()) + "#" + speaker.getSpeaker().getFullName();
		}

		private Model<String> getCssClass(ListItem<?> item) {
			return model(item.getIndex() % 2 == 0 ? "odd" : "");
		}
	}
}
