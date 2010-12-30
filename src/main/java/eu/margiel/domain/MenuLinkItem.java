package eu.margiel.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.common.base.Objects;

@SuppressWarnings("serial")
@Entity
public class MenuLinkItem implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String name;
	private Integer linkTo;

	public MenuLinkItem() {
	}

	public MenuLinkItem(Integer id, String name) {
		this.linkTo = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Integer getLinkTo() {
		return linkTo;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id, name, linkTo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuLinkItem other = (MenuLinkItem) obj;
		return Objects.equal(id, other.id)
				&& Objects.equal(name, other.name)
				&& Objects.equal(linkTo, other.linkTo);
	}

}
