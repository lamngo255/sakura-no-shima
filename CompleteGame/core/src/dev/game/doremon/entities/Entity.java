package dev.game.doremon.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import dev.game.doremon.main.DoremonHandler;


public abstract class Entity {
    protected DoremonHandler doremonHandler;
    protected float x, y;
    protected int width, height;
    protected Rectangle bounds;


    public Entity() {

    }

    public Entity(DoremonHandler doremonHandler, float x, float y, int width, int height) {
        this.doremonHandler = doremonHandler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        bounds = new Rectangle(x, y, width, height);

    }

    public abstract void tick();

    public abstract void render(SpriteBatch batch);

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        System.out.println(String.format("setting base y  = %s", y));
        this.y = y;

    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    protected void updateBounds() {
        bounds = new Rectangle(x, y, getWidth(), getHeight());
    }


    public void move(Vector2 velocity) {
        this.x += velocity.x;
        this.y += velocity.y;

    }

    public float getMiddleX() {
        return x + width / 2;
    }

    public float getMiddleY() {
        return y + height / 2;
    }

    public Vector2 getMiddleLocationVector() {
        return new Vector2(getMiddleX(), getMiddleY());
    }



}
