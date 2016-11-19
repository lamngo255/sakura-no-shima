package dev.freaking.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Slider {

    private static final float SPEED = 0.008f;
    private Image slider;
    private float ilong;
    private boolean running;
    private int screenWidth = Gdx.graphics.getWidth();
    private int screenHeight = Gdx.graphics.getHeight();

    public Slider() {
        super();
        running = true;
        ilong = screenWidth;
        slider = new Image(new Texture(Gdx.files.internal("data/load.PNG")));
        slider.setX(0);
        slider.setY((float) (screenHeight * 0.85));
        slider.setWidth(ilong);
        slider.setHeight((float) (screenHeight * 0.01));
    }

    public Image getSlider() {
        return slider;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void update() {
        if (running) {
            ilong -= screenWidth * 0.05;
            slider.setWidth(ilong);
            slider.invalidate();
        }
    }

    public float getIlong() {
        return ilong;
    }

    public void setIlong(float ilong) {
        this.ilong = ilong;
    }

}
