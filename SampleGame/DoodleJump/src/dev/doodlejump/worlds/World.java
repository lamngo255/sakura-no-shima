package dev.doodlejump.worlds;

import dev.doodlejump.Handler;
import dev.doodlejump.Launcher;
import dev.doodlejump.entities.Base;
import dev.doodlejump.entities.Platform;
import dev.doodlejump.entities.PlatformBroken;
import dev.doodlejump.entities.Player;
import dev.doodlejump.entities.Spring;
import java.awt.Graphics;
import java.util.ArrayList;

public final class World {

    private final Handler handler;
    public static final int WORLD_WIDTH = Launcher.GAME_WIDTH;
    public static final int WORLD_HEIGHT = Launcher.GAME_HEIGHT;

    // Entities
    private Base base;
    private Player player;
    private Spring spring;
    private PlatformBroken platformBroken;
    private ArrayList<Platform> platforms;

    // Game HUB
    private int score = 0;
    private int falling = 0;

    public World(Handler handler) {
	this.handler = handler;
	loadWorld();
    }

    public void tick() {
	base.tick();
	player.tick();
	spring.tick();
	platformBroken.tick();

	for (Platform platform : platforms) {
	    platform.tick();
	}
	platformScrolling();
	createSpring();
	collides();

	if (player.isDead()) 
	    gameOver();
    }

    public void render(Graphics g) {
	base.render(g);
	player.render(g);
	spring.render(g);
	platformBroken.render(g);
	platforms.forEach((platform) -> {
	    platform.render(g);
	});
	spring.render(g);
	platformBroken.render(g);
    }

    private void gameOver() {
	for (Platform platform : platforms) {
	    platform.setY(platform.getY() - 12);
	}

	if (player.getY() > WORLD_HEIGHT / 2 && falling == 0) {
	    player.setY(player.getY() - 8);
	    player.setVy(0);
	} else if (player.getY() < WORLD_HEIGHT / 2) {
	    falling = 1;
	} else if (player.getY() + player.getHeight() > WORLD_HEIGHT) {
	    player.setY(1000);
	}
    }

    private void createSpring() {
	// attach spring to the platform of index 0
	Platform p = platforms.get(0);
	if (p.getType() == 1 || p.getType() == 3 || p.getType() == 2) {
	    spring.setX(p.getX() + p.getWidth() / 2 - spring.getWidth() / 2);
	    spring.setY(p.getY() - p.getHeight());
	    spring.setAppear(true);

	    if (spring.getY() > WORLD_HEIGHT / 1.1 || p.getFlag() == 1) {
		spring.setState(0);
		spring.setAppear(false);
	    }
	} else {
	    // if not the specified type, remove the spring
	    spring.setX(0 - spring.getX());
	    spring.setY(0 - spring.getY());
	}
    }

    private void platformScrolling() {
	// create a illusion of scrolling when player reach above half screen
	if (player.getY() >= WORLD_HEIGHT / 2 - player.getHeight() / 2) {
	    return;
	}
	for (Platform platform : platforms) {
	    // move the platform downward while player get higher
	    if (player.getVy() < 0) {
		platform.setY(platform.getY() - player.getVy());
	    }

	    // reset the platform's position which was below the screen
	    if (platform.getY() > WORLD_HEIGHT) {
		platform.setType(platform.generateType());
		platform.setY(platform.getY() - WORLD_HEIGHT);
		platform.setFlag(0);
	    }
	}

	// moving the base downward
	base.setY(base.getY() - player.getVy());
	score++;
    }

    private void collides() {
	// collides with platforms
	int offset = 15;
	for (Platform platform : platforms) {
	    if (player.getVy() > 0 && platform.getState() == 0
		    && (player.getX() + offset < platform.getX() + platform.getWidth())
		    && (player.getX() + player.getWidth() - offset > platform.getX())
		    && (player.getY() + player.getHeight() > platform.getY())
		    && (player.getY() + player.getHeight()
		    < platform.getY() + platform.getHeight())) {

		if (platform.getType() == 2 && platform.getFlag() == 0) {
		    platform.setFlag(1);
		    platform.setState(1);
		    return;
		} else if (platform.getType() == 3 && platform.getFlag() == 0) {
		    player.jump();
		    platform.setFlag(1);
		    platform.setState(1);
		}
		if (platform.getFlag() == 1) {
		    return;
		}
		player.jump();
	    }
	}

	// collides with spring
	if (player.getVy() > 0 && spring.getState() == 0
		&& (player.getX() + offset < spring.getX() + spring.getWidth())
		&& (player.getX() + player.getWidth() - offset > spring.getX())
		&& (player.getY() + player.getHeight() > spring.getY())
		&& (player.getY() + player.getHeight()
		< spring.getY() + spring.getHeight())) {
	    spring.setState(1);
	    player.jumpHigh();
	}
    }

    public void loadWorld() {
	base = Base.generate(handler);
	player = Player.generate(handler);
	spring = Spring.generate(handler);
	platformBroken = PlatformBroken.generate(handler);

	platforms = new ArrayList<>();
	for (int i = 0; i < Platform.PLATFORM_COUNT; i++) {
	    platforms.add(Platform.generate(handler));
	}
	platformBroken = PlatformBroken.generate(handler);
    }

    public int getScore() {
	return score;
    }

    public void setScore(int score) {
	this.score = score;
    }

    public Base getBase() {
	return base;
    }

    public void setBase(Base base) {
	this.base = base;
    }

    public Player getPlayer() {
	return player;
    }

    public void setPlayer(Player player) {
	this.player = player;
    }

    public Spring getSpring() {
	return spring;
    }

    public void setSpring(Spring spring) {
	this.spring = spring;
    }

    public PlatformBroken getPlatformBroken() {
	return platformBroken;
    }

    public void setPlatformBroken(PlatformBroken platformBroken) {
	this.platformBroken = platformBroken;
    }

    public ArrayList<Platform> getPlatforms() {
	return platforms;
    }

    public void setPlatforms(ArrayList<Platform> platforms) {
	this.platforms = platforms;
    }

}
