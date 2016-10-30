package dev.breakout.entities;

import dev.breakout.Handler;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class Paddle extends Entity {

    public static final int PADDLE_HEIGHT = 15;
    public static final int PADDLE_WIDTH = 90;
    public static final int DEFAULT_SPEED = 5;

    private int xMove;
    private int speed;

    public Paddle(Handler handler, float x, float y) {
        super(handler, x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
        this.xMove = 0;
        this.speed = DEFAULT_SPEED;
    }

    @Override
    public void tick() {
        getInput();
        move();
    }

    private void move() {
        x += xMove;
    }

    private void getInput() {
        xMove = 0;

        if (handler.getKeyManager().left && x > 0) {
            xMove = -speed;
        }
        if (handler.getKeyManager().right && x < handler.getWidth() - PADDLE_WIDTH) {
            xMove = speed;
        }
    }

    public void onMouseMove(MouseEvent e) {
        this.x = e.getX() - PADDLE_WIDTH / 2;
        if (this.x < 0) {
            this.x = 0;
        } else if (this.x + PADDLE_WIDTH >= handler.getWidth()) {
            this.x = handler.getWidth() - PADDLE_WIDTH;
        }
    }

    @Override
    public void render(Graphics g) {
        Color aColor = new Color(Integer.parseInt("0095DD", 16));
        g.setColor(aColor);
        g.fillRect((int) x, (int) y, width, height);
    }
}
