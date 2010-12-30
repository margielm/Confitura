package eu.margiel.utils;

@SuppressWarnings("serial")
public class GalleryPhotosProvider extends PhotosProvider {

	@Override
	String getSubpath() {
		return "gallery";
	}

}
