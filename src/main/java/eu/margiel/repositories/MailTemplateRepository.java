package eu.margiel.repositories;

import org.synyx.hades.dao.GenericDao;

import eu.margiel.domain.MailTemplate;

public interface MailTemplateRepository extends GenericDao<MailTemplate, Integer> {

	MailTemplate readByType(String type);

}
