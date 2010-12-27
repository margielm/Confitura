package eu.margiel.utils;

import static eu.margiel.utils.OfferPhotosProvider.*;
import static org.fest.assertions.Assertions.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.File;
import java.util.List;

import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;

import eu.margiel.CasaensolApplication;
import eu.margiel.domain.Offer;
import eu.margiel.domain.PhotosFolder;

public class OfferPhotosProviderShould {
	private Offer offer = new Offer().creationDate(new LocalDateTime(2010, 1, 1, 1, 1, 1));
	private OfferPhotosProvider provider = spy(new OfferPhotosProvider(offer));
	private PhotosFolder folder = mock(PhotosFolder.class);
	private CasaensolApplication app = mock(CasaensolApplication.class);
	private File[] files = new File[] { new File("file1"), new File("file2") };

	@Before
	public void setupMocks() {
		when(folder.listPhotos()).thenReturn(files);
		when(app.getBaseImageUrl()).thenReturn("base/url/");
		doReturn(folder).when(provider).getPhotosFolder();
		doReturn(app).when(provider).getApp();
	}

	@Test
	public void getUrlsOfPhotosForOffer() {

		List<String> urls = provider.getPhotosURL();

		assertThat(urls).containsOnly(getUrlFor(offer, files[0]), getUrlFor(offer, files[1]));
	}

	@Test
	public void getUrlToPhotoInOffer() {

		String url = provider.getPhotoURLFor(files[0]);

		assertEquals(getUrlFor(offer, files[0]), url);
	}

	@Test
	public void addPhotoToresource() {
		File newPhotoFile = new File("new_photo");

		provider.addImageToResources(newPhotoFile);

		verify(app).addImageToResources(getOfferSubpath(offer, newPhotoFile), newPhotoFile);
	}

	private String getUrlFor(Offer offer, File file) {
		return "base/url/" + getOfferSubpath(offer, file);
	}

	private String getOfferSubpath(Offer offer, File file) {
		return "offer/" + offer.getCreationDate().toString(OFFER_DATE_FORMAT) + "/" + file.getName();
	}
}
