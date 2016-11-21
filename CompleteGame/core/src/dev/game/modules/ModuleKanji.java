package dev.game.modules;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dev.game.kanji.worlds.World;
import dev.game.main.GameHandler;

/**
 * Created by Lam Ngo on 11/21/2016.
 */
public class ModuleKanji extends Module {

    public static OrthographicCamera camera;
    private World world;

    public ModuleKanji(GameHandler gameHandler, GameModuleManager cpanel) {
        super(gameHandler, cpanel);
        world = new World();

        camera = new OrthographicCamera(GameHandler.GAME_WIDTH, GameHandler.GAME_HEIGHT);
        camera.setToOrtho(false, GameHandler.GAME_WIDTH, GameHandler.GAME_HEIGHT);
    }

    @Override
    public void tick() {
        world.tick();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        world.render(batch);
    }

    @Override
    public void dispose() {
        world.dispose();
    }
}
