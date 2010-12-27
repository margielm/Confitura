package eu.margiel.repositories;

import org.synyx.hades.dao.GenericDao;
import org.synyx.hades.dao.Modifying;
import org.synyx.hades.dao.Query;

import eu.margiel.domain.StaticContent;

public interface StaticContentRepository extends GenericDao<StaticContent, Integer> {
	@Modifying
	@Query("DELETE FROM StaticContent WHERE id = ?")
	public void deleteById(Integer id);

}
