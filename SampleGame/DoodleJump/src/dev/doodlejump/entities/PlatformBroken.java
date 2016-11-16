package dev.doodlejump.entities;

import dev.doodlejump.Handler;
import dev.doodlejump.gfx.Assets;
import dev.doodlejump.worlds.World;
import java.awt.Graphics;

public class PlatformBroken extends Entity {

    public static int DEFAULT_WIDTH = 70;
    public static int DEFAULT_HEIGHT = 45;

    private boolean appear = false;

    public static PlatformBroken generate(Handler handler) {
	return new PlatformBroken(handler, 0, 0);
    }

    public PlatformBroken(Handler handler, float x, float y) {
	super(handler, x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);

    }

    @Override
    public void tick() {
	moveDown();
    }

    @Override
    public void render(Graphics g) {
	if (appear) {
	    g.drawImage(Assets.platform_broken,
		    (int) this.x, (int) this.y,
		    this.width, this.height, null);
	}
    }

    private void moveDown() {
	if (appear) {
	    this.y += 8;
	    if (this.y > World.WORLD_HEIGHT) {
		appear = false;
	    }
	}
    }

    public boolean isAppear() {
	return appear;
    }

    public void setAppear(boolean appear) {
	this.appear = appear;
    }

}
