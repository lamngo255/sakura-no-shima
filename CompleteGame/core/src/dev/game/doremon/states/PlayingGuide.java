package dev.game.doremon.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import dev.game.doremon.gfx.Assets;
import dev.game.doremon.main.DoremonHandler;
import dev.game.main.GameHandler;
import dev.game.modules.GameModuleManager;

/**
 * Created by Ha San~ on 11/20/2016.
 */
public class PlayingGuide extends State {
    TextureRegion playingGuide;

    public PlayingGuide(DoremonHandler doremonHandler, GameStateManager gsm,
                        GameModuleManager cpanel, GameHandler gameHandler) {
        super(doremonHandler, gsm);
        this.cpanel = cpanel;
        this.gameHandler = gameHandler;
        playingGuide = Assets.playingGuide;
    }

    public void handleInput() {
        if (Gdx.input.isTouched()) {
            if (Gdx.input.getX() >= DoremonHandler.GAME_WIDTH * 0.5f &&
                    Gdx.input.getX() <= DoremonHandler.GAME_WIDTH * 0.92f &&
                    Gdx.input.getY() >= DoremonHandler.GAME_HEIGHT * 0.7f &&
                    Gdx.input.getY() <= DoremonHandler.GAME_HEIGHT * 0.86f) {
                gsm.set(new GameState(doremonHandler, gsm, cpanel, gameHandler));
            }
        }
    }

    @Override
    public void dispose() {

    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(playingGuide, 0, 0, DoremonHandler.GAME_WIDTH, DoremonHandler.GAME_HEIGHT);
        batch.end();
    }

    @Override
    public void tick() {
        handleInput();
    }
}
