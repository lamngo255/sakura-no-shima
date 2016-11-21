package dev.freaking.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dev.freaking.controller.Music;
import dev.freaking.model.Assets;
import dev.freaking.states.GameStateManager;
import dev.freaking.states.MenuState;

public class FreakingGame extends ApplicationAdapter {

    private int WIDTH;
    private int HEIGHT;
    public static OrthographicCamera camera;

    private SpriteBatch batch;
    private GameStateManager gsm;
    private Handler handler;

    @Override
    public void create() {
        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();
        Assets.init();
        init();

        camera = new OrthographicCamera(handler.getWidth(), handler.getHeight());
        camera.setToOrtho(false, handler.getWidth(), handler.getHeight());
    }

    public void init() {
        Music.load("point", "sounds/sfx_point.wav");
        Music.load("gameover", "sounds/sfx_over.wav");

        handler = new Handler(this);
        gsm = new GameStateManager();
        gsm.push(new MenuState(handler, gsm));
        batch = new SpriteBatch();
    }

    @Override
    public void render() {
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render(batch);
    }

    @Override
    public void dispose() {
        batch.dispose();
        gsm.dispose();
        Assets.dispose();
    }
}
