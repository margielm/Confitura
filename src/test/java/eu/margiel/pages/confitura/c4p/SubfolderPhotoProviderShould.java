package eu.margiel.pages.confitura.c4p;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.junit.Test;
import org.mockito.Mockito;

import eu.margiel.domain.Speaker;
import eu.margiel.pages.confitura.c4p.SubfolderPhotoProvider;

public class SubfolderPhotoProviderShould {
	@Test
	public void doNothingWhenFileUploadIsNull() {
		SubfolderPhotoProvider provider = Mockito.spy(new SubfolderPhotoProvider(""));

		provider.savePhoto(null, new Speaker());

		verify(provider, never()).saveFile(any(FileUpload.class), anyString(), anyString());
	}
}
