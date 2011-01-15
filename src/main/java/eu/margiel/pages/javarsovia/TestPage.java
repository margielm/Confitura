package eu.margiel.pages.javarsovia;

import org.apache.wicket.Application;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.util.file.File;
import org.apache.wicket.util.file.Folder;

import eu.margiel.Javarsovia;

public class TestPage extends WebPage {
	private final class TestForm extends Form<Void> {
		private FileUploadField fileField = new FileUploadField("file");

		private TestForm(String id) {
			super(id);
			add(fileField);
			add(new Image("image", Javarsovia.get().getSharedResources()
					.get(Application.class, "a", null, null, false)));
		}

		@Override
		protected void onSubmit() {
			try {
				FileUpload fileUpload = fileField.getFileUpload();
				String path = "speakers/speaker";
				Folder folder = new Folder(Javarsovia.get().getMainFilesFolder(), path);
				folder.mkdirs();
				File file = new File(folder, "photo.jpg");
				System.out.println(file.getAbsolutePath());
				System.out.println(file.getCanonicalPath());
				System.out.println(file.getPath());
				file.createNewFile();
				fileUpload.writeTo(file);
				Javarsovia.get().addImageToResources("a", file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public TestPage() {
		add(new TestForm("form"));
	}

}
