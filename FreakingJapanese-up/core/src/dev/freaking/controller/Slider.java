package dev.freaking.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dev.freaking.main.Handler;

/**
 * Created by Lam Ngo on 11/21/2016.
 */
public class Slider {

    private static final int SPEED = Gdx.graphics.getWidth() / 45;
    private Handler handler;
    private ShapeRenderer slider;
    private boolean running;
    private float x, y;
    private float SLIDER_HEIGHT = Gdx.graphics.getHeight() / 108f;

    public Slider(Handler handler) {
        this.handler = handler;
        this.running = true;
        this.slider = new ShapeRenderer();

        this.x = 0;
        this.y = handler.getHeight() * 0.8f;
    }

    public void render(SpriteBatch batch) {
        batch.begin();
        batch.end();

        slider.begin(ShapeRenderer.ShapeType.Filled);
        slider.setColor(Color.valueOf("#f9f6f2"));
        slider.rect(x, y, handler.getWidth(), SLIDER_HEIGHT);
        slider.end();
    }

    public void update() {
        if (running) {
            this.x -= SPEED;
        }
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getX() {
        return this.x;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
