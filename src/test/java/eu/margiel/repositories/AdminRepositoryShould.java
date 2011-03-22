package eu.margiel.repositories;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import eu.margiel.domain.Admin;
import eu.margiel.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-spring.xml")
@Transactional
public class AdminRepositoryShould {
	@Autowired
	private AdminRepository repository;

	@Test
	public void findUserByUserName() {
		Admin user = new Admin("userName").firstName("Michał").lastName("Margiel");
		repository.saveAndFlush(user);

		Admin foundUser = repository.readByUserName("userName");

		assertEquals("userName", foundUser.getUserName());
	}

	@Test
	public void notFindUserByNotExistingUserName() {
		Admin user = new Admin("userName").firstName("Michał").lastName("Margiel");
		repository.saveAndFlush(user);

		User foundUser = repository.readByUserName("xyz");

		assertNull(foundUser);
	}
}
