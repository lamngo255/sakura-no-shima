package dev.game.doremon.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dev.game.doremon.main.DoremonHandler;
import dev.game.doremon.worlds.World;
import dev.game.main.GameHandler;
import dev.game.modules.GameModuleManager;
import dev.game.modules.ModuleDoremon;


public class GameState extends State {
    private World world;

    public GameState(DoremonHandler doremonHandler, GameStateManager gsm,
                     GameModuleManager cpanel, GameHandler gameHandler) {
        super(doremonHandler, gsm);

        gameHandler.playBackgroundMusic();

        this.cpanel = cpanel;
        this.gameHandler = gameHandler;
        world = new World(doremonHandler);
        doremonHandler.setWorld(world);

    }

    @Override
    public void tick() {
        if (!world.isGameOver()) {
            world.tick();
        } else {
            world.setGameOver(false);
            Gdx.app.log("Game Over State", "Loading");

            //game Over
            ModuleDoremon.updateBestScore(world.getScore());
            gsm.set(new GameOverState(doremonHandler, gsm, world.getScore(), cpanel, gameHandler));
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
