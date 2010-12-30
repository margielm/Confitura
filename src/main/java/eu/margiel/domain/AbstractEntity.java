package eu.margiel.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@SuppressWarnings("serial")
@MappedSuperclass
public class AbstractEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;

	public Integer getId() {
		return id;
	}

	@SuppressWarnings("unchecked")
	public <T extends AbstractEntity> T id(Integer id) {
		this.id = id;
		return (T) this;
	}

}
