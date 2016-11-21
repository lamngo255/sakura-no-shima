package dev.game.modules;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dev.game.doremon.gfx.Assets;
import dev.game.doremon.main.DoremonHandler;
import dev.game.doremon.states.GameStateManager;
import dev.game.doremon.states.MenuState;
import dev.game.main.GameHandler;

/**
 * Created by Lam Ngo on 11/21/2016.
 */
public class DoremonModule extends Module {
    public static OrthographicCamera camera;
    public static int bestScore = 0;
    private DoremonHandler doremonHandler;
    private GameStateManager gsm;

    public DoremonModule(GameHandler gameHandler, GameModuleManager cpanel) {
        super(gameHandler, cpanel);
        Assets.init();
        init();
    }

    private void init() {
        camera = new OrthographicCamera();
        camera.setToOrtho(true);

        doremonHandler = new DoremonHandler(this);
        gsm = new GameStateManager();
        gsm.push(new MenuState(doremonHandler, gsm, cpanel, gameHandler));
    }

    @Override
    public void tick() {
        gsm.tick();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gsm.render(batch);
    }

    @Override
    public void dispose() {
        gsm.dispose();
        Assets.dispose();
    }
}
