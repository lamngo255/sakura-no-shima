package com.mygdx.game.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.gfx.Assets;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.MenuState;


public class DoremonJump extends ApplicationAdapter {
    public static OrthographicCamera camera;
    public static int bestScore = 0;

    private GameStateManager gsm;
    private SpriteBatch batch;
    private Handler handler;

    @Override
    public void create() {
        //debuging
        batch = new SpriteBatch();
        Assets.init();
        init();
    }

    public void init() {
        camera = new OrthographicCamera();
        camera.setToOrtho(true);

        handler = new Handler(this);
        gsm = new GameStateManager();
        gsm.push(new MenuState(handler, gsm));
    }

    @Override
    public void render() {
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gsm.render(batch);
        gsm.tick();
    }

    @Override
    public void dispose() {
        batch.dispose();
        gsm.dispose();
        Assets.dispose();
    }

    public static OrthographicCamera getCamera() {
        return camera;
    }
}
