package dev.freaking.main;

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

    public Stage getStage() {
        return this.freakingGame.getStage();
    }

    public int getWidth() {
        return freakingGame.getWIDTH();
    }

    public int getHeight() {
        return freakingGame.getHEIGHT();
    }
}
