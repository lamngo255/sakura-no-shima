package dev.breakout;

public class Launcher {

	public static void main(String[] args) {
		Game game = new Game("Break Out", 500, 400);
		game.start();
	}
}
