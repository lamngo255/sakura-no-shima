package dev.breakout.entities;

import dev.breakout.Handler;
import java.awt.Color;
import java.awt.Graphics;

public class Brick extends Entity {

    public static final int BRICK_HEIGHT = 20;
    public static final int BRICK_WIDTH = 88;
    public static final int BRICK_OFFSET_LEFT = 10;
    public static final int BRICK_OFFSET_TOP = 50;
    public static final int BRICK_PADDING = 10;
    private boolean active = true;

    public Brick(Handler handler, float x, float y) {
        super(handler, x, y, BRICK_WIDTH, BRICK_HEIGHT);
    }

    @Override
    public void tick() {
        Ball ball = handler.getWorld().getBall();
        if (isActive()) {
            if (this.getCollisionBounds().intersects(ball.getCollisionBounds())) {
                ball.revertDy();
                setActive(false);
                handler.getWorld().addScore();
            }
        }
    }

    @Override
    public void render(Graphics g) {
        Color aColor = new Color(Integer.parseInt("0095DD", 16));
        g.setColor(aColor);
        g.fillRect((int) (x + bounds.x), (int) (y + bounds.y), width, height);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
