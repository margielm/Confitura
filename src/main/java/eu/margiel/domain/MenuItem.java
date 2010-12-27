package eu.margiel.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

@SuppressWarnings("serial")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
		name = "menu_type",
		discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("I")
public class MenuItem implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private boolean published = true;

	@ManyToOne
	private MenuItem parent;

	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@OrderBy("position")
	private List<MenuItem> children = Lists.newArrayList();

	private int position;

	@ManyToOne(cascade = CascadeType.ALL)
	private DynamicContent content;

	MenuItem() {
	}

	public MenuItem(String name) {
		this.name = name;
	}

	public MenuItem children(MenuItem... children) {
		for (MenuItem child : children)
			add(child);
		return this;
	}

	public MenuItem addEmptyChild() {
		add(new MenuItem(""));
		return this;
	}

	private void add(MenuItem child) {
		child.position = children.size();
		child.parent = this;
		children.add(child);
	}

	public List<MenuItem> getChildren() {
		return children;
	}

	public String getName() {
		return name;
	}

	public boolean removeChild(MenuItem child) {
		boolean removed = children.remove(child);
		reorderChildren();
		return removed;
	}

	public boolean up() {
		return parent != null ? parent.childUp(this) : false;
	}

	public boolean down() {
		return parent != null ? parent.childDown(this) : false;
	}

	private boolean childDown(MenuItem child) {
		int index = children.indexOf(child);
		boolean notLast = isNotLastIdx(index);
		if (notLast) {
			MenuItem child2 = children.set(index + 1, child);
			children.set(index, child2);
		}
		reorderChildren();
		return notLast;
	}

	private boolean isNotLastIdx(int index) {
		return index < children.size() - 1;
	}

	private boolean isNotFirstIdx(int index) {
		return index > 0;
	}

	private boolean childUp(MenuItem child) {
		int index = children.indexOf(child);
		boolean notFirst = isNotFirstIdx(index);
		if (notFirst) {
			MenuItem child2 = children.set(index - 1, child);
			children.set(index, child2);
		}
		reorderChildren();
		return notFirst;
	}

	private void reorderChildren() {
		for (int idx = 0; idx < children.size(); idx++)
			children.get(idx).position = idx;
	}

	public boolean remove() {
		return parent != null ? parent.removeChild(this) : false;
	}

	public void togglePublished() {
		published = !published;
	}

	public boolean isPublished() {
		return published;
	}

	public Integer getId() {
		return id;
	}

	public int getPosition() {
		return position;
	}

	public DynamicContent getContent() {
		return content;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof MenuItem) {
			MenuItem o = (MenuItem) obj;
			return Objects.equal(id, o.id)
					&& Objects.equal(name, o.name)
					&& Objects.equal(published, o.published)
					&& Objects.equal(parent, o.parent)
					&& Objects.equal(children, o.children);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id, name, published);
	}

	@Override
	public String toString() {
		return "MenuItem [id=" + id + ", name=" + name + ", published=" + published + ", children=" + children + "]";
	}

	public MenuItem addMenuItem(MenuItem menuItem) {
		add(menuItem);
		return this;
	}
}
