package dev.tetris;

public class Launcher {

    public static void main(String[] args) {
	Game game = new Game("Tetris", 420, 600);
	game.start();
    }
}
