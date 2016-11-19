package dev.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import dev.game.main.Game2048;

/**
 * Created by Lam Ngo on 11/19/2016.
 */
public class Tile {
    public static int TILE_WIDTH = (int) (Gdx.graphics.getWidth() / 4.6f);
    public static int TILE_HEIGHT = (int) (Gdx.graphics.getWidth() / 4.6f);
    public static final int SLIDE_SPEED = 65;
    private int x;
    private int y;
    private int value;
    private Color background, textColor;
    private ShapeRenderer shape;
    private boolean canCombine;
    private Point slideTo;

    public Tile(int value, int x, int y) {
        this.value = value;
        this.x = x;
        this.y = y;
        shape = new ShapeRenderer();
    }

    public void drawImage() {
        if (value == 2) {
            background = Color.WHITE;
            textColor = new Color(0x000000);
        } else if (value == 4) {
            background = Color.YELLOW;
            textColor = new Color(0x000000);
        } else if (value == 8) {
            background = Color.ORANGE;
            textColor = new Color(0x000000);
        } else if (value == 16) {
            background = Color.RED;
            textColor = new Color(0x000000);
        } else if (value == 32) {
            background = Color.BLUE;
            textColor = new Color(0x000000);
        } else if (value == 64) {
            background = Color.BROWN;
            textColor = new Color(0x000000);
        } else if (value == 128) {
            background = Color.BLACK;
            textColor = new Color(0x000000);
        } else if (value == 256) {
            background = new Color(0xf6e873);
            textColor = new Color(0x000000);
        } else if (value == 512) {
            background = new Color(0xf5e455);
            textColor = new Color(0x000000);
        } else if (value == 1024) {
            background = new Color(0xf7e12c);
            textColor = new Color(0x000000);
        } else if (value == 2048) {
            background = new Color(0xffe400);
            textColor = new Color(0x000000);
        } else {
            background = Color.BLACK;
            textColor = Color.WHITE;
        }
    }

    public void render(SpriteBatch batch) {
        batch.begin();
        batch.end();
        drawImage();
        shape.setProjectionMatrix(Game2048.camera.combined);
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(background);
        shape.rect(x, y, TILE_WIDTH, TILE_HEIGHT);
        shape.end();
    }

    public void update() {

    }

    public void setCanCombine(boolean canCombine) {
        this.canCombine = canCombine;
    }

    public boolean canCombine() {
        return canCombine;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setSlideTo(Point slideTo) {
        this.slideTo = slideTo;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
