package eu.margiel.pages.javarsovia;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringBean;

import eu.margiel.components.MainMenuPanel;
import eu.margiel.pages.admin.MenuItemList;
import eu.margiel.repositories.MenuRepository;

@SuppressWarnings("serial")
public class BaseWebPage extends WebPage {
	@SpringBean
	private MenuRepository repository;
	@SpringBean
	private MenuItemList menuItemList;

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
		add(new MainMenuPanel("mainMenu", repository.getMainMenu(), menuItemList));
	}
}
