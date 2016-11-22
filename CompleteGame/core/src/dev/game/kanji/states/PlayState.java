package dev.game.kanji.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dev.game.kanji.worlds.World;
import dev.game.main.GameHandler;
import dev.game.modules.GameModuleManager;

/**
 * Created by Ha San~ on 11/22/2016.
 */
public class PlayState extends State {
    private World world;


    public PlayState(GameStateManager gsm, GameModuleManager cpanel, GameHandler gameHandler) {
        super(gsm);

        this.cpanel = cpanel;
        this.gameHandler = gameHandler;
        this.world = new World(cpanel, gameHandler);
    }

    @Override
    public void tick() {
        world.tick();
    }

    @Override
    public void render(SpriteBatch batch) {
        world.render(batch);
    }

    @Override
    public void dispose() {
        world.dispose();
    }
}
