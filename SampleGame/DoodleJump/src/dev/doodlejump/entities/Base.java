package dev.doodlejump.entities;

import dev.doodlejump.Handler;
import dev.doodlejump.gfx.Assets;
import java.awt.Graphics;

public class Base extends Entity {

    public static final int DEFAULT_WIDTH = 1000;
    public static final int DEFAULT_HEIGHT = 5;

    private boolean moved = false;

    public static Base generate(Handler handler) {
	return new Base(handler,
		0, handler.getHeight() - Assets.base.getHeight());
    }

    public Base(Handler handler, float x, float y) {
	super(handler, x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
	g.drawImage(Assets.base,
		(int) this.x, (int) this.y,
		this.width, this.height, null);
    }

}
