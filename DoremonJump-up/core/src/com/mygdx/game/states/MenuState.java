package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.gfx.Assets;
import com.mygdx.game.main.Handler;


public class MenuState extends State {
    TextureRegion playButton;
    TextureRegion background;

    public MenuState(Handler handler, GameStateManager gsm) {
        super(handler, gsm);
        playButton = Assets.playButton[0];
        background = Assets.background;
    }

    public void handleInput() {
        if (Gdx.input.justTouched()) {
            Gdx.app.log("GameState", "loaded");
            gsm.set(new GameState(handler, gsm));
        }
    }

    @Override
    public void tick() {
        handleInput();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(background, 0, 0, handler.getWidth(), handler.getHeight());
        batch.draw(playButton, handler.getWidth() / 2 - playButton.getRegionWidth() / 2 * 1.5f,
                                handler.getHeight() / 2 - playButton.getRegionHeight() / 2 * 1.5f,
                                playButton.getRegionWidth() * 1.5f,
                                playButton.getRegionHeight() * 1.5f);
        batch.end();
    }

    @Override
    public void dispose() {
    }
}
