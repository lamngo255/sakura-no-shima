package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.gfx.Assets;
import com.mygdx.game.main.Handler;

/**
 * Created by Ha San~ on 11/20/2016.
 */
public class PlayingGuide extends State {
    TextureRegion playingGuide;

    public PlayingGuide(Handler handler, GameStateManager gsm) {
        super(handler, gsm);
        playingGuide = Assets.playingGuide;
    }

    public void handleInput() {
        if (Gdx.input.isTouched()) {
            if (Gdx.input.getX() >= Handler.GAME_WIDTH * 0.5f &&
                    Gdx.input.getX() <= Handler.GAME_WIDTH * 0.92f &&
                    Gdx.input.getY() >= Handler.GAME_HEIGHT * 0.7f &&
                    Gdx.input.getY() <= Handler.GAME_HEIGHT * 0.86f) {
                gsm.set(new GameState(handler, gsm));
            }
        }
    }

    @Override
    public void dispose() {

    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(playingGuide, 0, 0, Handler.GAME_WIDTH, Handler.GAME_HEIGHT);
        batch.end();
    }

    @Override
    public void tick() {
        handleInput();
    }
}
