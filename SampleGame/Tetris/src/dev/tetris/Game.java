package dev.tetris;

import dev.tetris.display.Display;
import dev.tetris.input.KeyManager;
import dev.tetris.worlds.World;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {

    private final int width;
    private final int height;
    private final String title;

    private Display display;
    private Thread thread;
    private boolean running = false;
    private boolean gameOn = false;

    private BufferStrategy bs;
    private Graphics g;

    private KeyManager keyManager;
    private Handler handler;
    private World world;
    private long timer;

    public Game(String title, int width, int height) {
	this.width = width;
	this.height = height;
	this.title = title;
	this.keyManager = new KeyManager();
    }

    private void init() {
	display = new Display(title, width, height);
	display.getFrame().addKeyListener(keyManager);

	handler = new Handler(this);
	world = new World(handler);
	handler.setWorld(world);
	
	keyManager.setHandler(handler);
    }

    private void tick() {
	keyManager.tick();
	world.tick();
    }

    private void render() {
	bs = display.getCanvas().getBufferStrategy();
	if (bs == null) {
	    display.getCanvas().createBufferStrategy(3);
	    return;
	}
	g = bs.getDrawGraphics();

	// Clear screen
	g.clearRect(0, 0, width, height);
	g.setColor(Color.BLACK);
	g.fillRect(0, 0, width, height);

	// Draw here!
	world.render(g);

	// End drawing!
	bs.show();
	g.dispose();
    }

    public void run() {
	init();

	int fps = 60;
	double timePerTick = 1000000000 / fps;
	double delta = 0;
	long now;
	long lastTime = System.nanoTime();
	timer = 0;

	while (running) {
	    now = System.nanoTime();
	    delta += (now - lastTime) / timePerTick;
	    timer += now - lastTime;
	    lastTime = now;

	    if (delta >= 1) {
		tick();
		render();
		delta--;
	    }

	    if (timer >= 1000000000) {
		// for drop internal
		setGameOn(true);
	    }
	}
	stop();
    }

    public KeyManager getKeyManager() {
	return this.keyManager;
    }

    public int getWidth() {
	return this.width;
    }

    public int getHeight() {
	return this.height;
    }

    public boolean isGameOn() {
	return gameOn;
    }

    public void setGameOn(boolean gameOn) {
	this.gameOn = gameOn;
    }

    public synchronized void start() {
	if (running) {
	    return;
	}
	running = true;
	thread = new Thread(this);
	thread.start();
    }

    public synchronized void stop() {
	if (!running) {
	    return;
	}
	running = false;
	try {
	    thread.join();
	} catch (InterruptedException ex) {
	}

    }

    public void setTimer(long timer) {
	this.timer = timer;
    }
}
