package dev.breakout.entities;

import dev.breakout.Handler;
import java.awt.Color;
import java.awt.Graphics;

public class Ball extends Entity {

    public static final int BALL_RADIUS = 18;
    private int dx;
    private int dy;

    public Ball(Handler handler, float x, float y) {
        super(handler, x, y, BALL_RADIUS, BALL_RADIUS);
        this.dx = 2;
        this.dy = -2;
    }

    @Override
    public void tick() {
        move();
    }

    private void move() {
        if (x + dx > handler.getWidth() - BALL_RADIUS || x + dx < 0) {
            dx = -dx;
        }
        if (y + dy < BALL_RADIUS) {
            dy = -dy;
        } else if (y + dy > handler.getHeight() - BALL_RADIUS - 20) {
            Paddle paddle = handler.getWorld().getPaddle();
            if (this.getCollisionBounds().intersects(paddle.getCollisionBounds())) {
                dy = -dy;
            } else {
                x = paddle.getX() + paddle.getWidth() / 2;
                y = paddle.getY() - BALL_RADIUS;
                handler.getWorld().loseALife();
            }
        }

        x += dx;
        y += dy;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillOval((int) x, (int) y, width, height);
    }

    public void revertDx() {
        this.dx = -dx;
    }

    public void revertDy() {
        this.dy = -dy;
    }
}
