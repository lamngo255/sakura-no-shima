package dev.doodlejump.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage sheet;

    public SpriteSheet(BufferedImage sheet) {
	this.sheet = sheet;
    }

    public BufferedImage crop(int x, int y, int width, int height) {
	return sheet.getSubimage(x, y, width, height);
    }

    public int getWidth() {
	return sheet.getWidth();
    }

    public int getHeight() {
	return sheet.getHeight();
    }
}
