package dev.tetris.worlds;

import dev.tetris.Handler;
import dev.tetris.entities.Arena;
import dev.tetris.entities.Player;
import java.awt.Font;
import java.awt.Graphics;

public class World {

    public static final int SCALE = 30;
    public static final int SPAWN_X = 5; // 14 unit width
    public static final int SPAWN_Y = 0; // 20 unit height
    public static final int DEFAULT_WIDTH = 14;
    public static final int DEFAULT_HEIGHT = 20;

    private Handler handler;
    private Player player;
    private Arena arena;
    private int score = 0;

    public World(Handler handler) {
	this.handler = handler;
	loadWorld();
    }

    public void tick() {
	player.tick();
	arena.tick();
    }

    public void render(Graphics g) {
	player.render(g);
	arena.render(g);
	drawScore(g);
    }

    public final void loadWorld() {
	player = new Player(handler, SPAWN_X, SPAWN_Y);
	arena = new Arena(handler, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	player.setArena(arena);
    }

    private void drawScore(Graphics g) {
	g.setFont(new Font("Arial", Font.BOLD, 18));
	g.drawString("Score: " + score, 10, 30);
    }

    public Player getPlayer() {
	return player;
    }

    public void setPlayer(Player player) {
	this.player = player;
    }

    public Arena getArena() {
	return arena;
    }

    public void setArena(Arena arena) {
	this.arena = arena;
    }

    public void setScore(int score) {
	this.score = score;
    }

    public void addScore(int score) {
	this.score += score;
    }

}
