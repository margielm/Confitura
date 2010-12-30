package eu.margiel.repositories;

import org.synyx.hades.dao.GenericDao;
import org.synyx.hades.dao.Modifying;
import org.synyx.hades.dao.Query;

import eu.margiel.domain.SimpleContent;

public interface SimpleContentRepository extends GenericDao<SimpleContent, Integer> {
	@Modifying
	@Query("DELETE FROM StaticContent WHERE id = ?")
	public void deleteById(Integer id);

}
