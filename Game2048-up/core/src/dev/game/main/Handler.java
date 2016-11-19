package dev.game.main;

import com.badlogic.gdx.Gdx;

/**
 * Created by Lam Ngo on 11/19/2016.
 */
public class Handler {
    private Game2048 game2048;

    public Handler(Game2048 game2048) {
        this.game2048 = game2048;
    }

    public int getWidth() {
        return Gdx.graphics.getWidth();
    }

    public int getHeight() {
        return Gdx.graphics.getHeight();
    }
}
