package eu.margiel.components;

import java.util.List;

import org.apache.wicket.util.tester.TagTester;
import org.apache.wicket.util.tester.WicketTester;

import eu.margiel.TestApplication;

public class WicketBaseTest {

	protected WicketTester tester = new WicketTester(new TestApplication());

	public WicketBaseTest() {
		super();
	}

	protected TagTester get(String wicketId) {
		return tester.getTagByWicketId(wicketId);
	}

	protected List<TagTester> getTags(String wicketId) {
		return WicketTester.getTagsByWicketId(tester, wicketId);
	}

}