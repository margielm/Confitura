package eu.margiel.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import org.apache.wicket.extensions.markup.html.image.resource.ThumbnailImageResource;

@SuppressWarnings("serial")
public class ImageScaler extends ThumbnailImageResource {
	public ImageScaler(File file, int maxSize) {
		super(new FileResource(file), maxSize);
		setScaleHints(Image.SCALE_SMOOTH);
	}

	public BufferedImage getThumbnail() {
		return getScaledImageInstance();
	}
}