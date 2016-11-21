package dev.freaking.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Handler {
    private FreakingGame freakingGame;

    public Handler(FreakingGame freakingGame) {
        this.freakingGame = freakingGame;
    }

    public FreakingGame getGame() {
        return freakingGame;
    }

    public void setGame(FreakingGame freakingGame) {
        this.freakingGame = freakingGame;
    }

    public int getWidth() {
        return Gdx.graphics.getWidth();
    }

    public int getHeight() {
        return Gdx.graphics.getHeight();
    }
}
