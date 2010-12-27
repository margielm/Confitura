package eu.margiel.utils;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class ImageUtils {
	public static BufferedImage scale(BufferedImage source, double ratio) {
		int wight = (int) (source.getWidth() * ratio);
		int height = (int) (source.getHeight() * ratio);
		BufferedImage bi = getCompatibleImage(wight, height);
		Graphics2D g2d = bi.createGraphics();
		double xScale = (double) wight / source.getWidth();
		double yScale = (double) height / source.getHeight();
		AffineTransform at = AffineTransform.getScaleInstance(xScale, yScale);
		g2d.drawRenderedImage(source, at);
		g2d.dispose();
		return bi;
	}

	private static BufferedImage getCompatibleImage(int wight, int height) {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		GraphicsConfiguration gc = gd.getDefaultConfiguration();
		return gc.createCompatibleImage(wight, height);
	}

}
