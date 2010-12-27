package eu.margiel.domain;

import static org.fest.assertions.Assertions.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class MenuItemShould {
	private MenuItem child1 = new MenuItem("child 1");
	private MenuItem child2 = new MenuItem("child 2");
	private MenuItem menu = new MenuItem("parent").children(child1, child2);

	@Test
	public void assignOrderOnAddingEmptyChild() {
		menu.addEmptyChild();

		assertOrder();
	}

	@Test
	public void assignOrderOnAddingChildren() {
		menu.children(new MenuItem(""), new MenuItem(""));

		assertOrder();
	}

	@Test
	public void moveUp() {

		boolean moved = child2.up();

		assertThat(moved).isTrue();
		assertThat(menu.getChildren()).containsSequence(child2, child1);
		assertOrder();
	}

	private void assertOrder() {
		List<MenuItem> children = menu.getChildren();
		for (int idx = 0; idx < children.size(); idx++)
			assertEquals(idx, children.get(idx).getPosition());
	}

	@Test
	public void notMoveUpWhenMovingFirstOne() {

		boolean moved = child1.up();

		assertThat(moved).isFalse();
		assertThat(menu.getChildren()).containsSequence(child1, child2);
		assertOrder();
	}

	@Test
	public void moveDown() {

		boolean moved = child1.down();

		assertThat(moved).isTrue();
		assertThat(menu.getChildren()).containsSequence(child2, child1);
		assertOrder();
	}

	@Test
	public void notMoveDownWhenMovingLastOne() {

		boolean moved = child2.down();

		assertThat(moved).isFalse();
		assertThat(menu.getChildren()).containsSequence(child1, child2);
		assertOrder();
	}

	@Test
	public void notMoveUpOrDownIfParentIsNull() {
		assertThat(menu.up()).isFalse();
		assertThat(menu.down()).isFalse();
	}

	@Test
	public void beRemovedFromParent() {
		boolean removed = child1.remove();

		assertThat(removed).isTrue();
		assertThat(menu.getChildren()).containsOnly(child2);
		assertOrder();
	}

	@Test
	public void notBeRemovedWhenParentIsNull() {
		boolean removed = menu.remove();

		assertThat(removed).isFalse();
	}
}
