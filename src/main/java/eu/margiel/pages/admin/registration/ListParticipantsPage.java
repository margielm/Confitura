package eu.margiel.pages.admin.registration;

import static eu.margiel.utils.Components.*;
import static eu.margiel.utils.DateUtils.*;

import java.util.List;

import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.synyx.hades.domain.Sort;

import eu.margiel.domain.Participant;
import eu.margiel.pages.admin.AdminBasePage;
import eu.margiel.repositories.ParticipantRepository;

public class ListParticipantsPage extends AdminBasePage {

	@SpringBean
	private ParticipantRepository repository;

	public ListParticipantsPage() {
		add(new ParticipantsList(repository.readAll(new Sort("registrationDate"))));
	}

	@SuppressWarnings("serial")
	private final class ParticipantsList extends ListView<Participant> {
		private ParticipantsList(List<? extends Participant> list) {
			super("rows", list);
		}

		@Override
		protected void populateItem(ListItem<Participant> item) {
			Participant participant = item.getModelObject();
			item.add(label("id", (item.getIndex() + 1) + ""));
			item.add(label("firstName", participant.getFirstName()));
			item.add(label("lastName", participant.getLastName()));
			item.add(label("mail", participant.getMail()));
			item.add(label("city", participant.getCity()));
			item.add(label("sex", participant.getSex()));
			item.add(label("date", dateToString(participant.getRegistrationDate())));
			item.add(label("state", participant.getRegistrationStatus().getName()));
			item.add(label("lunch", participant.isLunch() ? "Tak" : "Nie"));
		}
	}
}
