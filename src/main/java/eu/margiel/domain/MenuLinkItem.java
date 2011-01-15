package eu.margiel.domain;

import javax.persistence.Entity;

import com.google.common.base.Objects;

@SuppressWarnings("serial")
@Entity
public class MenuLinkItem extends AbstractEntity {

	private String title;
	private Integer itemId;

	public MenuLinkItem() {
	}

	public MenuLinkItem(Integer itemId, String title) {
		this.itemId = itemId;
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public Integer getItemId() {
		return itemId;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id, title, itemId);
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
				&& Objects.equal(title, other.title)
				&& Objects.equal(itemId, other.itemId);
	}

}
