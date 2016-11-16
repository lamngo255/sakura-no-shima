package dev.doodlejump.entities;

import dev.doodlejump.Handler;
import dev.doodlejump.gfx.Assets;
import dev.doodlejump.worlds.World;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Platform extends Entity {

    public static final int DEFAULT_WIDTH = 85;
    public static final int DEFAULT_HEIGHT = 20;
    public static final int PLATFORM_COUNT = 9;
    public static float POSITION = 0;
    public static boolean BROKEN = false;

    private int type;
    private int flag;
    private int state;
    private float vx;

    public static Platform generate(Handler handler) {
	return new Platform(handler,
		(float) Math.random() * (handler.getWidth() - DEFAULT_WIDTH),
		Platform.POSITION);
    }

    public Platform(Handler handler, float x, float y) {
	super(handler, x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);

	POSITION += handler.getHeight() / PLATFORM_COUNT;
	this.vx = 1;
	this.type = (int) (Math.random() * 2);
    }

    @Override
    public void tick() {
	moveAround();
	crackUp();
    }

    @Override
    public void render(Graphics g) {
	if (getCurrentFrame() == null) {
	    return;
	}
	g.drawImage(getCurrentFrame(),
		(int) this.x, (int) this.y,
		this.width, this.height, null);
    }

    private void moveAround() {
	if (this.type == 1 || this.type == 2) {
	    if (x < 0 || x + width > World.WORLD_WIDTH) {
		this.vx *= -1;
	    }
	    this.x += this.vx;
	}
    }

    private void crackUp() {
	PlatformBroken subs = handler.getWorld().getPlatformBroken();
	if (this.flag == 1 && !subs.isAppear() && this.state == 1) {
	    subs.setX(this.x);
	    subs.setY(this.y);
	    subs.setAppear(true);
	    this.state = 0;
	}
    }

    public int generateType() {
	int score = handler.getWorld().getScore();
	int[] types;

	if (score >= 5000) {
	    types = new int[]{1, 2, 2, 2, 3, 3, 3, 3};
	} else if (score >= 2000 && score < 5000) {
	    types = new int[]{1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3};
	} else if (score >= 1000 && score < 2000) {
	    types = new int[]{1, 1, 1, 2, 2, 2, 2};
	} else if (score >= 500 && score < 1000) {
	    types = new int[]{0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2};
	} else if (score >= 50 && score < 500) {
	    types = new int[]{0, 0, 0, 1, 1, 1};
	} else {
	    types = new int[]{0};
	}

	this.type = types[(int) (Math.random() * types.length)];
	if (!BROKEN && this.type == 2) {
	    BROKEN = true;
	} else if (BROKEN && this.type == 2) {
	    BROKEN = false;
	    this.type = 0;
	}
	return this.type;
    }

    public void setType(int type) {
	this.type = type;
    }

    public int getType() {
	return this.type;
    }

    public BufferedImage getCurrentFrame() {
	switch (this.type) {
	    case 0:
		return Assets.platform[0];
	    case 1:
		return Assets.platform[1];
	    case 2:
		if (this.flag == 0) {
		    return Assets.platform[2];
		} else {
		    return null;
		}
	    case 3:
		if (this.flag == 0) {
		    return Assets.platform[3];
		} else {
		    return null;
		}
	    default:
		return Assets.platform[0];
	}
    }

    public int getFlag() {
	return flag;
    }

    public void setFlag(int flag) {
	this.flag = flag;
    }

    public int getState() {
	return state;
    }

    public void setState(int state) {
	this.state = state;
    }

}
