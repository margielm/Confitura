package eu.margiel.components;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.*;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.tester.ITestPanelSource;
import org.junit.Ignore;
import org.junit.Test;

@SuppressWarnings("serial")
public class LinkOrLabelShould extends WicketBaseTest {
	private LinkOrLabel linkOrLabel;

	@Test
	@Ignore
	public void renderOnlyLabel() {
		final String label = "label_text";

		createPanel(label, null);

		assertNull(get("link"));
		assertEquals(label, get("label").getValue());
	}

	@Test
	@Ignore
	public void renderLink() {
		final String label = "label_text";
		final Page page = mock(Page.class);

		createPanel(label, page);

		assertNull(get("label"));
		assertNotNull(get("link"));
		assertEquals(label, get("link_label").getValue());
	}

	private void createPanel(final String label, final Page page) {
		tester.startPanel(new ITestPanelSource() {

			@Override
			public Panel getTestPanel(String id) {
				linkOrLabel = new LinkOrLabel(id, label, page);
				return linkOrLabel;
			}
		});
	}
}
