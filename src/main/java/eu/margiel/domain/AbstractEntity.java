package eu.margiel.domain;

import javax.persistence.MappedSuperclass;

import org.synyx.hades.domain.AbstractPersistable;

@SuppressWarnings("serial")
@MappedSuperclass
public class AbstractEntity extends AbstractPersistable<Integer> {

	@SuppressWarnings("unchecked")
	public <T extends AbstractEntity> T id(Integer id) {
		setId(id);
		return (T) this;
	}

	public boolean isNotNew() {
		return isNew() == false;
	}

}
