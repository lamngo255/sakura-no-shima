package dev.game.freaking.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dev.game.freaking.main.FreakingHandler;
import dev.game.main.GameHandler;

/**
 * Created by Lam Ngo on 11/21/2016.
 */
public class Slider {

    private static int SPEED = Gdx.graphics.getWidth() / 55;
    private FreakingHandler freakingHandler;
    private ShapeRenderer slider;
    private boolean running;
    private float x, y;
    private float SLIDER_HEIGHT = Gdx.graphics.getHeight() / 108f;

    public Slider(FreakingHandler freakingHandler, int SPEED) {
        this.freakingHandler = freakingHandler;
        this.running = true;
        this.slider = new ShapeRenderer();

        this.x = 0;
        this.y = GameHandler.GAME_HEIGHT * 0.8f;
        if(SPEED==1){
            Slider.SPEED=Gdx.graphics.getWidth() / 55;
        }else if(SPEED==2){
            Slider.SPEED=Gdx.graphics.getWidth() / 75;
        }else if(SPEED==3){
            Slider.SPEED=Gdx.graphics.getWidth() / 100;
        }
    }

    public void render(SpriteBatch batch) {
        batch.begin();
        batch.end();

        slider.begin(ShapeRenderer.ShapeType.Filled);
        slider.setColor(Color.valueOf("#f9f6f2"));
        slider.rect(x, y, GameHandler.GAME_WIDTH, SLIDER_HEIGHT);
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
