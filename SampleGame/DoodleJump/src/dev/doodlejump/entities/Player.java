package dev.doodlejump.entities;

import dev.doodlejump.Handler;
import dev.doodlejump.gfx.Assets;
import static dev.doodlejump.worlds.World.WORLD_HEIGHT;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    public static final int DEFAULT_WIDTH = 60;
    public static final int DEFAULT_HEIGHT = 45;
    public static float GRAVITY = 0.2f;
    public static int JUMP = -8;
    public static int JUMP_HIGH = -35;

    private float vx, vy;
    private boolean isMovingLeft, isMovingRight;
    private String dir;
    private boolean dead = false;

    public static Player generate(Handler handler) {
	return new Player(handler,
		handler.getWidth() / 2 - DEFAULT_WIDTH / 2,
		handler.getHeight() - Player.DEFAULT_HEIGHT);
    }

    public Player(Handler handler, float x, float y) {
	super(handler, x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);

	// vy > 0: moving downwards
	// vy < 0: moving upwards, JUMP: negative value
	this.dir = "right";
	this.vx = 0;
	this.vy = GRAVITY;
	this.isMovingLeft = false;
	this.isMovingRight = false;
    }

    @Override
    public void tick() {
	playerDirection();
	playerMove();
	playerJump();
	playerFallDown();

	if (handler.getWorld().getBase().getY() > WORLD_HEIGHT 
		&& (this.y + this.height > WORLD_HEIGHT)
		&& !dead) {
	    this.setDead(true);
	}
    }

    private void playerDirection() {
	isMovingLeft = handler.getKeyManager().left;
	isMovingRight = handler.getKeyManager().right;
	if (isMovingLeft) {
	    this.dir = "left";
	    if (this.vy < -7 && this.vy > -15) {
		this.dir = "left_land";
	    }
	} else if (isMovingRight) {
	    this.dir = "right";
	    if (this.vy < -7 && this.vy > -15) {
		this.dir = "right_land";
	    }
	}
    }

    private void playerMove() {
	// Moving back and forth with acceleration
	if (isMovingLeft) {
	    this.x += this.vx;
	    this.vx -= 0.12;
	} else {
	    this.x += this.vx;
	    if (this.vx < 0) {
		this.vx += 0.1;
	    }
	}

	if (isMovingRight) {
	    this.x += this.vx;
	    this.vx += 0.12;
	} else {
	    this.x += this.vx;
	    if (this.vx > 0) {
		this.vx -= 0.1;
	    }
	}

	// Move through the wall
	if (this.x > handler.getWidth()) {
	    this.x = 0 - this.width;
	} else if (this.x < 0 - this.width) {
	    this.x = handler.getWidth();
	}
    }

    public void playerJump() {
	// Jump from the base
	if (this.y + this.height > handler.getWorld().getBase().y) {
	    jump();
	}

	// keep the player below half the screen when jumping
	if (this.y >= (handler.getHeight() - this.height) / 2) {
	    this.y += this.vy;
	    this.vy += GRAVITY;
	}
    }

    public void jump() {
	this.vy = JUMP;
    }

    public void jumpHigh() {
	this.vy = JUMP_HIGH;
    }

    private void playerFallDown() {
	if (this.y >= WORLD_HEIGHT / 2 - height / 2) {
	    return;
	}
	this.vy += GRAVITY;
	if (this.vy >= 0) {
	    this.y += this.vy;
	    this.vy += GRAVITY;
	}

	
    }

    @Override
    public void render(Graphics g) {
	g.drawImage(getCurrentFrame(),
		(int) this.x, (int) this.y,
		this.width, this.height, null);
    }

    public BufferedImage getCurrentFrame() {
	switch (dir) {
	    case "right":
		return Assets.player[0];
	    case "left":
		return Assets.player[1];
	    case "right_land":
		return Assets.player[2];
	    case "left_land":
		return Assets.player[3];
	    default:
		return Assets.player[0];
	}
    }

    public float getVx() {
	return vx;
    }

    public void setVx(float vx) {
	this.vx = vx;
    }

    public float getVy() {
	return vy;
    }

    public void setVy(float vy) {
	this.vy = vy;
    }

    public boolean isDead() {
	return dead;
    }

    public void setDead(boolean dead) {
	this.dead = dead;
    }
}
