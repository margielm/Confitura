package eu.margiel.repositories;

import static org.fest.assertions.Assertions.*;

import java.util.List;

import org.joda.time.LocalDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import eu.margiel.domain.News;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-spring.xml")
@Transactional
public class NewsRepositoryShould {
	@Autowired
	private NewsRepository repository;

	@Test
	public void fetchOnlyPublishedNewsSortedByCreationDate() {
		News published1 = new News().title("n1").published(true)
				.creationDate(new LocalDateTime(2010, 10, 1, 10, 10).toDateTime().toDate());
		News published2 = new News().title("n2").published(true)
				.creationDate(new LocalDateTime(2011, 10, 1, 10, 10).toDateTime().toDate());
		repository.saveAndFlush(published1);
		repository.saveAndFlush(published2);
		repository.saveAndFlush(new News().title("n3").published(false));

		List<News> news = repository.fetchPublished();

		assertThat(news).containsExactly(published2, published1);
	}

	@Test
	public void fetchOnlyPublishedNews() {
		News published1 = new News().title("n1").published(true)
				.creationDate(new LocalDateTime(2010, 10, 1, 10, 10).toDateTime().toDate());
		repository.saveAndFlush(published1);
		repository.saveAndFlush(new News().title("n3").published(false));

		List<News> news = repository.readAll(new PublishedNews());

		assertThat(news).containsExactly(published1);
		assertThat(repository.count(new PublishedNews())).isEqualTo(1);
	}
}
