package dev.game.modules;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dev.game.freaking.controller.Music;
import dev.game.freaking.main.FreakingHandler;
import dev.game.freaking.model.Assets;
import dev.game.freaking.states.GameStateManager;
import dev.game.freaking.states.MenuState;
import dev.game.main.GameHandler;

/**
 * Created by Lam Ngo on 11/21/2016.
 */
public class ModuleFreaking extends Module {
    public static OrthographicCamera camera;

    private GameStateManager gsm;
    private FreakingHandler freakingHandler;

    public ModuleFreaking(GameHandler gameHandler, GameModuleManager cpanel) {
        super(gameHandler, cpanel);
        Assets.init();
        init();

        camera = new OrthographicCamera(GameHandler.GAME_WIDTH, GameHandler.GAME_HEIGHT);
        camera.setToOrtho(false, GameHandler.GAME_WIDTH, GameHandler.GAME_HEIGHT);
    }
    
    private void init() {
        Music.load("point", "sounds/sfx_point.wav");
        Music.load("gameover", "sounds/sfx_over.wav");

        gameHandler.changeBackgroundMusic(GameHandler.BACKGROUND_MUSIC_FREAKING);

        freakingHandler = new FreakingHandler(this);
        gsm = new GameStateManager();
        gsm.push(new MenuState(freakingHandler, gsm, cpanel, gameHandler));
    }

    @Override
    public void tick() {
        gsm.tick();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        gsm.render(batch);
    }

    @Override
    public void dispose() {
        gsm.dispose();
        Assets.dispose();
    }
}
