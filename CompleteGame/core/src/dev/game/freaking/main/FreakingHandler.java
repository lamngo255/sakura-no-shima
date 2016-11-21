package dev.game.freaking.main;

public class FreakingHandler {
    private FreakingGame freakingGame;

    public FreakingHandler(FreakingGame freakingGame) {
        this.freakingGame = freakingGame;
    }

    public FreakingGame getGame() {
        return freakingGame;
    }

    public void setGame(FreakingGame freakingGame) {
        this.freakingGame = freakingGame;
    }
}
