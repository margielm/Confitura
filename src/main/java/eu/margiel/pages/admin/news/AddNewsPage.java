package eu.margiel.pages.admin.news;

import static eu.margiel.utils.Components.*;
import static eu.margiel.utils.Models.*;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import eu.margiel.domain.News;
import eu.margiel.pages.admin.AdminBasePage;
import eu.margiel.repositories.NewsRepository;

public class AddNewsPage extends AdminBasePage {
	private News news;
	@SpringBean
	private NewsRepository repository;

	public AddNewsPage(News news) {
		this.news = news;
		add(new FeedbackPanel("feedback"));
		add(new AddNewsForm("form"));
	}

	public AddNewsPage() {
		this(new News());
	}

	@SuppressWarnings("serial")
	private class AddNewsForm extends Form<News> {

		private AddNewsForm(String id) {
			super(id);
			add(textField("title", propertyModel(news, "title")));
			add(richEditor("shortDescription", propertyModel(news, "shortDescription")));
			add(richEditor("description", propertyModel(news, "description")));
		}

		@Override
		protected void onSubmit() {
			repository.save(news);
			setResponsePage(ListNewsPage.class);
		}
	}
}
