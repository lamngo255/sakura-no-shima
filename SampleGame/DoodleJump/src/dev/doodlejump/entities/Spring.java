package dev.doodlejump.entities;

import dev.doodlejump.Handler;
import dev.doodlejump.gfx.Assets;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Spring extends Entity {

    public static int DEFAULT_WIDTH = 26;
    public static int DEFAULT_HEIGHT = 30;

    private int state = 0;
    private boolean appear = false;

    public static Spring generate(Handler handler) {
	return new Spring(handler, 0, 0);
    }

    public Spring(Handler handler, float x, float y) {
	super(handler, x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
	if (appear) {
	    g.drawImage(getCurrentFrame(),
		    (int) this.x, (int) this.y,
		    this.width, this.height, null);
	}
    }

    public BufferedImage getCurrentFrame() {
	return Assets.spring[this.state];
    }

    public int getState() {
	return state;
    }

    public void setState(int state) {
	this.state = state;
    }

    public boolean isAppear() {
	return appear;
    }

    public void setAppear(boolean appear) {
	this.appear = appear;
    }
}
