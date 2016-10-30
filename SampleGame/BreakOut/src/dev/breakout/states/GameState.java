package dev.breakout.states;

import dev.breakout.Handler;
import dev.breakout.worlds.World;
import java.awt.Graphics;

public class GameState extends State {

    private World world;

    public GameState(Handler handler) {
        super(handler);
        world = new World(handler);
        handler.setWorld(world);
    }

    @Override
    public void tick() {
        world.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
    }
}
