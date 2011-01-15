package eu.margiel.repositories;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import eu.margiel.domain.Speaker;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-spring.xml")
@Transactional
public class SpeakerRepositoryShould {
	@Autowired
	private SpeakerRepository repository;

	@Test
	public void findSpeakerByEMail() {
		String mail = "jan.kowalski@domena.pl";
		Speaker expectedSpeaker = new Speaker().mail(mail);
		repository.saveAndFlush(expectedSpeaker);
		repository.saveAndFlush(new Speaker().mail("michal.nowak@domena.pl"));

		Speaker foundSpeaker = repository.readByMail(mail);

		assertEquals(expectedSpeaker, foundSpeaker);
	}
}
