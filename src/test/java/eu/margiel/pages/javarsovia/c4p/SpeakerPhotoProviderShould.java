package eu.margiel.pages.javarsovia.c4p;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.junit.Test;
import org.mockito.Mockito;

import eu.margiel.domain.Speaker;

public class SpeakerPhotoProviderShould {
	@Test
	public void doNothingWhenFileUploadIsNull() {
		SpeakerPhotoProvider provider = Mockito.spy(new SpeakerPhotoProvider(""));

		provider.savePhoto(null, new Speaker());

		verify(provider, never()).saveFile(any(FileUpload.class), anyString(), anyString());
	}
}
