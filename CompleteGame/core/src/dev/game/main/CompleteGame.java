package dev.game.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dev.game.modules.GameModuleManager;
import dev.game.modules.MainGameModule;

public class CompleteGame extends ApplicationAdapter {
    public static OrthographicCamera camera;
    private SpriteBatch batch;
    private GameHandler gameHandler;
    private GameModuleManager cpanel;

    @Override
    public void create() {
        batch = new SpriteBatch();
        gameHandler = new GameHandler(this);
        cpanel = new GameModuleManager();
        cpanel.push(new MainGameModule(gameHandler, cpanel));

        camera = new OrthographicCamera(GameHandler.GAME_WIDTH, GameHandler.GAME_HEIGHT);
        camera.setToOrtho(false, GameHandler.GAME_WIDTH, GameHandler.GAME_HEIGHT);
    }

    @Override
    public void render() {
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glEnable(GL20.GL_BLEND);

        cpanel.render(batch);
        cpanel.tick();
    }

    @Override
    public void dispose() {
        batch.dispose();
        cpanel.dispose();
    }
}
