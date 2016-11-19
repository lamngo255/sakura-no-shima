package dev.doremon.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import dev.doremon.gfx.Assets;
import dev.doremon.main.Handler;


public class MenuState extends State {
    TextureRegion playButton;
    TextureRegion background;
    TextureRegion homeButton;

    public MenuState(Handler handler, GameStateManager gsm) {
        super(handler, gsm);
        playButton = Assets.playButton[0];
        background = Assets.background;
        homeButton = Assets.playButton[2];

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
//        batch.draw(playButton, handler.getWidth() / 2 - playButton.getRegionWidth() / 2 * 1.5f,
//                                handler.getHeight() / 2 - playButton.getRegionHeight() / 2 * 1.5f,
//                                playButton.getRegionWidth() * 1.5f,
//                                playButton.getRegionHeight() * 1.5f);
        batch.draw(playButton, handler.getWidth() * 0.056f, handler.getHeight() *0.6f,handler.getWidth() * 0.47f, handler.getHeight() * 0.09f)
        ;
        batch.draw(homeButton, handler.getWidth() * 0.056f, handler.getHeight() * 0.73f,handler.getWidth() * 0.47f, handler.getHeight() * 0.09f);

        batch.end();
    }

    @Override
    public void dispose() {

    }
}

