package eu.margiel;

import javax.servlet.http.HttpServletRequest;

import org.apache.wicket.Application;
import org.apache.wicket.Page;
import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.extensions.ajax.markup.html.form.upload.UploadWebRequest;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WebRequest;
import org.apache.wicket.settings.IExceptionSettings;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.wicketstuff.annotation.scan.AnnotatedMountScanner;

import eu.margiel.pages.ErrorPage;
import eu.margiel.pages.confitura.news.ViewNewsPage;

public class Confitura extends WebApplication {

	public static Confitura get() {
		return (Confitura) WebApplication.get();
	}

	public Confitura() {

	}

	@Override
	public Session newSession(Request request, Response response) {
		return new ConfituraSession(request);
	}

	@Override
	protected void init() {
		getMarkupSettings().setStripWicketTags(true);
		getMarkupSettings().setDefaultMarkupEncoding("UTF-8");
		getRequestCycleSettings().setResponseRequestEncoding("UTF-8");
		new AnnotatedMountScanner().scanPackage("eu.margiel.pages").mount(this);
		getResourceSettings().setThrowExceptionOnMissingResource(false);
		getApplicationSettings().setInternalErrorPage(ErrorPage.class);
		getExceptionSettings().setUnexpectedExceptionDisplay(IExceptionSettings.SHOW_INTERNAL_ERROR_PAGE);
		productionInit();
	}

	protected void productionInit() {
		addComponentInstantiationListener(new SpringComponentInjector(this));
		getSharedResources().putClassAlias(Application.class, "confitura");
	}

	@Override
	public Class<? extends Page> getHomePage() {
		return ViewNewsPage.class;
	}

	@Override
	protected WebRequest newWebRequest(HttpServletRequest servletRequest) {
		return new UploadWebRequest(servletRequest);
	}

}
