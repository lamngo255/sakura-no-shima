package dev.game.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dev.game.worlds.World;

public class Game2048 extends ApplicationAdapter {
    public static OrthographicCamera camera;
    private SpriteBatch batch;
    private Handler handler;
    private World world;

    @Override
    public void create() {
        batch = new SpriteBatch();
        handler = new Handler(this);
        world = new World(handler);

        camera = new OrthographicCamera(handler.getWidth(), handler.getHeight());
        camera.setToOrtho(false, handler.getWidth(), handler.getHeight());
    }

    @Override
    public void render() {
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        world.render(batch);
        world.update();
    }

    @Override
    public void dispose() {
        batch.dispose();
        world.dispose();
    }
}
