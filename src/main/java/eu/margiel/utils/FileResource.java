package eu.margiel.utils;

import java.io.File;

import org.apache.wicket.markup.html.WebResource;
import org.apache.wicket.util.resource.FileResourceStream;
import org.apache.wicket.util.resource.IResourceStream;

@SuppressWarnings("serial")
public class FileResource extends WebResource {
	private final File file;

	public FileResource(File file) {
		this.file = file;
	}

	@Override
	public IResourceStream getResourceStream() {
		return new FileResourceStream(file);
	}
}