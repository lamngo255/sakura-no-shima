package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.main.Handler;
import com.mygdx.game.worlds.World;


public class GameState extends State {
    private World world;

    public GameState(Handler handler, GameStateManager gsm) {
        super(handler, gsm);
        world = new World(handler);
        handler.setWorld(world);

    }

    @Override
    public void tick() {
        if (!world.isGameOver()) {
            world.tick();
        } else {
            world.setGameOver(false);
            Gdx.app.log("Game Over State", "Loading");
            gsm.set(new GameOverState(handler, gsm, world.getScore()));
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        world.render(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        world.dispose();
    }
}
