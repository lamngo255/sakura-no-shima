package dev.freaking.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import dev.freaking.controller.HighScoreData;
import dev.freaking.states.GameStateManager;
import dev.freaking.controller.Music;
import dev.freaking.states.MenuState;

public class FreakingGame extends ApplicationAdapter {

    private int WIDTH;
    private int HEIGHT;
    private Stage stage;
    private GameStateManager gsm;
    private Handler handler;

    @Override
    public void create() {
        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();
        stage = new Stage();
        init();
    }

    public void init() {
        Music.load("point", "sounds/sfx_point.wav");
        Music.load("gameover", "sounds/sfx_over.wav");
        HighScoreData.load();
        Gdx.input.setInputProcessor(stage);

        handler = new Handler(this);
        gsm = new GameStateManager();
        gsm.push(new MenuState(handler, gsm));
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.draw();
    }

    public Stage getStage() {
        return stage;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        stage.dispose();
    }

}
