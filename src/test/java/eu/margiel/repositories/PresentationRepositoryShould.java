package eu.margiel.repositories;

import static org.fest.assertions.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import eu.margiel.domain.Presentation;
import eu.margiel.domain.Speaker;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-spring.xml")
@Transactional
public class PresentationRepositoryShould {
	@Autowired
	private PresentationRepository repository;
	@Autowired
	private SpeakerRepository speakerRepository;

	@Test
	public void deletePresentationFromTheSpeaker() {
		Speaker speaker = speakerRepository.saveAndFlush(new Speaker());
		Presentation presentation = new Presentation();
		speaker.addPresentation(presentation);
		presentation = repository.saveAndFlush(presentation);
		System.out.println(presentation.getId());

		repository.delete(presentation);
		repository.flush();
		speakerRepository.flush();

		assertThat(repository.readByPrimaryKey(presentation.getId())).isNull();

	}
}
