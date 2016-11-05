package dev.doodlejump;
import dev.doodlejump.Game;

public class Launcher {
    public static int GAME_WIDTH = 420;
    public static int GAME_HEIGHT = 600;

    public static void main(String[] args) {
	Game game = new Game("Doodle Jump", GAME_WIDTH, GAME_HEIGHT);
	game.start();
    }
}
