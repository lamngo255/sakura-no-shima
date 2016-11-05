package dev.doodlejump.gfx;

import java.awt.image.BufferedImage;

public class Assets {

    public static BufferedImage base, platform_broken;
    public static BufferedImage[] player, platform, spring;

    public static void init() {
	SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/res/textures/sheet.png"));

	player = new BufferedImage[4];
	platform = new BufferedImage[5];
	spring = new BufferedImage[2];

	base = sheet.crop(0, 614, 100, 5);
	player[0] = sheet.crop(0, 121, 110, 80);
	player[1] = sheet.crop(0, 201, 110, 80);
	player[2] = sheet.crop(0, 289, 110, 80);
	player[3] = sheet.crop(0, 371, 110, 80);

	platform[0] = sheet.crop(0, 0, 105, 31);
	platform[1] = sheet.crop(0, 61, 105, 31);
	platform[2] = sheet.crop(0, 31, 105, 31);
	platform[3] = sheet.crop(0, 90, 105, 31);
	platform[4] = sheet.crop(0, 645, 105, 31);

	spring[0] = sheet.crop(0, 450, 45, 53);
	spring[1] = sheet.crop(0, 501, 45, 53);

	platform_broken = sheet.crop(0, 554, 105, 60);
    }
}
