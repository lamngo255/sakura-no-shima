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

	public int WORLD_WIDTH ;
	public int WORLD_HEIGHT ;
	private GameStateManager gsm;
	private SpriteBatch batch;
	private Handler handler;

	private Viewport viewport;


	@Override
	public void create() {

		//debuging
		WORLD_WIDTH =Gdx.graphics.getWidth();
		WORLD_HEIGHT = Gdx.graphics.getHeight();


		batch = new SpriteBatch();
		Assets.init();
		init();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2,0);
	}

	public void init() {
//		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//		camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera = new OrthographicCamera();
		camera.setToOrtho(true);

		viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
		viewport.apply();

		camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2,0);

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

	public int getHeight() {
		return WORLD_HEIGHT;
	}

	public int getWidth() {
		return WORLD_WIDTH;
	}
}
