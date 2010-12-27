package eu.margiel.casaensol;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringBean;

import eu.margiel.components.MainMenuPanel;
import eu.margiel.services.MenuService;

@SuppressWarnings("serial")
public class BaseWebPage extends WebPage {
	@SpringBean
	private MenuService testService;
	private WebMarkupContainer wrapper;

	public BaseWebPage() {
		wrapper = new WebMarkupContainer("main_content") {
			@Override
			public boolean isTransparentResolver() {
				return true;
			}

		};
		wrapper.setOutputMarkupId(true);
		add(wrapper);
		add(new MainMenuPanel("mainMenu", testService.getMainMenu()));
		add(new MainMenuPanel("horizontalMenu", testService.getHorizontalMenu()));
	}
}
