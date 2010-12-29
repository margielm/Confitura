package eu.margiel;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.wicket.Application;
import org.apache.wicket.extensions.ajax.markup.html.form.upload.UploadWebRequest;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WebRequest;
import org.apache.wicket.protocol.http.WebRequestCycle;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.util.file.Folder;
import org.wicketstuff.annotation.scan.AnnotatedMountScanner;

import eu.margiel.utils.FileResource;

public class Javarsovia extends WebApplication {
	private Folder mainFilesFolder;

	public Javarsovia() {
	}

	@Override
	protected void init() {
		super.init();
		getMarkupSettings().setStripWicketTags(true);
		getMarkupSettings().setDefaultMarkupEncoding("UTF-8");
		getRequestCycleSettings().setResponseRequestEncoding("UTF-8");
		new AnnotatedMountScanner().scanPackage("eu.margiel.pages").mount(this);

		getResourceSettings().setThrowExceptionOnMissingResource(false);

		productionInit();
	}

	protected void productionInit() {
		addComponentInstantiationListener(new SpringComponentInjector(this));
		initFolders();
		getSharedResources().putClassAlias(Application.class, "images");
		loadAllFiles();
	}

	public void addImageToResources(String path, File file) {
		System.out.println("adding " + path);
		getSharedResources().add(path, new FileResource(file));
	}

	private void initFolders() {
		mainFilesFolder = new Folder("files");
		mainFilesFolder.mkdir();
	}

	@Override
	public Class<HomePage> getHomePage() {
		return HomePage.class;
	}

	@Override
	protected WebRequest newWebRequest(HttpServletRequest servletRequest) {
		return new UploadWebRequest(servletRequest);
	}

	public Folder getMainFilesFolder() {
		return mainFilesFolder;
	}

	public String getBaseImageUrl() {
		HttpServletRequest request = ((WebRequestCycle) WebRequestCycle.get()).getWebRequest()
				.getHttpServletRequest();
		return request.getRequestURL() + "resources/images/";
	}

	private void loadAllFiles() {
		loadAllFiles(mainFilesFolder);
	}

	private void loadAllFiles(Folder folder) {
		for (File file : folder.listFiles()) {
			if (file.isDirectory())
				loadAllFiles(new Folder(file));
			else {
				String path = file.getPath().replace(mainFilesFolder.getPath(), "").replace("\\", "/");
				addImageToResources(path.substring(1), file);
			}
		}

	}

}
