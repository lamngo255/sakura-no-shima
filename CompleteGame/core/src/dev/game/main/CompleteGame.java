package dev.game.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.sun.org.apache.xpath.internal.operations.Mod;
import dev.game.modules.GameModuleManager;
import dev.game.modules.MainGameModule;
import dev.game.modules.ModuleDoremon;

public class CompleteGame extends ApplicationAdapter {
    public static final int WORLD_WIDTH_TEST = 1080 ;
    public static final int WORLD_HEIGHT_TEST = 1920;
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

        //added
        ModuleDoremon.camera = new OrthographicCamera();
        ModuleDoremon.camera.setToOrtho(true);
        ModuleDoremon.viewport = new StretchViewport(WORLD_WIDTH_TEST, WORLD_HEIGHT_TEST, ModuleDoremon.camera);


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

    @Override
    public void resize(int width, int height) {
        if (ModuleDoremon.viewport != null) {
            System.out.println("updating");
            ModuleDoremon.viewport.update(width, height);
            ModuleDoremon.camera.position.set(ModuleDoremon.camera.viewportWidth / 2, ModuleDoremon.camera.viewportHeight / 2, 0);
        } else {

            super.resize(width, height);
        }

    }
}
