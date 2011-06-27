package eu.margiel.pages.admin.news;

import static eu.margiel.utils.Components.*;
import static eu.margiel.utils.Models.*;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;
import org.wicketstuff.annotation.strategy.MountMixedParam;

import eu.margiel.ConfituraSession;
import eu.margiel.domain.News;
import eu.margiel.pages.admin.AdminBasePage;
import eu.margiel.repositories.NewsRepository;

@MountPath(path = "admin/news/view")
@MountMixedParam(parameterNames = "id")
public class AddNewsPage extends AdminBasePage {
	@SpringBean
	private NewsRepository repository;

	public AddNewsPage(PageParameters params) {
		initPage(repository.readByPrimaryKey(params.getAsInteger("id")));
	}

	public AddNewsPage() {
		initPage(new News());
	}

	private void initPage(News news) {
		add(new FeedbackPanel("feedback"));
		add(new AddNewsForm(news));
	}

	@SuppressWarnings("serial")
	private class AddNewsForm extends Form<News> {

		private final News news;

		private AddNewsForm(News news) {
			super("form");
			this.news = news;

			add(new CheckBox("published", new PropertyModel<Boolean>(news, "published")));
			add(textField("title", propertyModel(news, "title"), true));
			add(richEditor("shortDescription", propertyModel(news, "shortDescription"), true));
			add(richEditor("description", propertyModel(news, "description")));
			add(cancelLink(ListNewsPage.class));
		}

		@Override
		protected void onSubmit() {
			if (news.isNew())
				news.autor(ConfituraSession.get().getAdmin());
			repository.save(news);
			setResponsePage(ListNewsPage.class);
		}
	}
}
