package dev.game.kanji.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dev.game.main.GameHandler;

/**
 * Created by Lam Ngo on 11/19/2016.
 */
public class Tile {
    public static int TILE_WIDTH = (int) (GameHandler.GAME_WIDTH / 4.6f);
    public static int TILE_HEIGHT = (int) (GameHandler.GAME_WIDTH / 4.6f);
    public static final int SLIDE_SPEED = GameHandler.GAME_WIDTH / 11;
    private int x;
    private int y;
    private String value;
    private Color background, textColor;
    private ShapeRenderer shape;
    private boolean canCombine = true;
    private Point slideTo;

    public Tile(String value, int x, int y) {
        this.value = value;
        this.x = x;
        this.y = y;
        shape = new ShapeRenderer();
    }

    public void drawImage() {
        if (value.equals("一")) {
            background = Color.valueOf("#eee4da");
            textColor = Color.DARK_GRAY;
        } else if (value.equals("ニ")) {
            background = Color.valueOf("#ede0c8");
            textColor = Color.DARK_GRAY;
        } else if (value.equals("三")) {
            background = Color.valueOf("#f2b179");
            textColor = Color.WHITE;
        } else if (value.equals("四")) {
            background = Color.valueOf("#f59563");
            textColor = Color.WHITE;
        } else if (value.equals("五")) {
            background = Color.valueOf("#f67c5f");
            textColor = Color.valueOf("#f9f6f2");
        } else if (value.equals("六")) {
            background = Color.valueOf("#f65e3b");
            textColor = Color.valueOf("#f9f6f2");
        } else if (value.equals("七")) {
            background = Color.valueOf("#edcf72");
            textColor = Color.valueOf("#f9f6f2");
        } else if (value.equals("八")) {
            background = Color.valueOf("#edcc61");
            textColor = Color.valueOf("#f9f6f2");
        } else if (value.equals("九")) {
            background = Color.valueOf("#edc850");
            textColor = Color.valueOf("#f9f6f2");
        } else if (value.equals("十")) {
            background = Color.valueOf("#edc53f");
            textColor = Color.valueOf("#f9f6f2");
        } else if (value.equals("百")) {
            background = Color.valueOf("#edc22e");
            textColor = Color.valueOf("#f9f6f2");
        } else {
            background = Color.BLACK;
            textColor = Color.valueOf("#f9f6f2");
        }
    }

    public void render(SpriteBatch batch, BitmapFont tileFont) {
        drawImage();
//        shape.setProjectionMatrix(ModuleKanji.camera.combined);
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(background);
        shape.rect(x, y, TILE_WIDTH, TILE_HEIGHT);
        shape.end();

        batch.begin();
        tileFont.setColor(textColor);
        tileFont.draw(batch, value, x + TILE_WIDTH / 5, y + TILE_HEIGHT - TILE_HEIGHT / 4);
        batch.end();
    }

    public void update() {

    }

    public void setCanCombine(boolean canCombine) {
        this.canCombine = canCombine;
    }

    public boolean canCombine() {
        return canCombine;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
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

    public void dispose() {
        shape.dispose();
    }
}
