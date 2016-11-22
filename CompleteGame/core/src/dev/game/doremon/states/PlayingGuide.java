package dev.game.doremon.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import dev.game.doremon.gfx.Assets;
import dev.game.doremon.main.DoremonHandler;
import dev.game.main.CompleteGame;
import dev.game.main.GameHandler;
import dev.game.modules.GameModuleManager;
import dev.game.modules.ModuleDoremon;

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
        gameHandler.playBackgroundMusic();
    }

    public void handleInput() {
        if (Gdx.input.isTouched()) {
//            if (Gdx.input.getX() >= GameHandler.GAME_WIDTH * 0.5f &&
//                    Gdx.input.getX() <= GameHandler.GAME_WIDTH * 0.92f &&
//                    Gdx.input.getY() >= GameHandler.GAME_HEIGHT * 0.7f &&
//                    Gdx.input.getY() <= GameHandler.GAME_HEIGHT * 0.86f)
            if (ModuleDoremon.checkIfButtonPressed(CompleteGame.WORLD_WIDTH_TEST *  0.5f,
                    CompleteGame.WORLD_WIDTH_TEST* 0.92f,
                    CompleteGame.WORLD_HEIGHT_TEST * 0.7f,
                    CompleteGame.WORLD_HEIGHT_TEST * 0.86f
            ))
            {
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
        batch.draw(playingGuide, 0, 0, CompleteGame.WORLD_WIDTH_TEST, CompleteGame.WORLD_HEIGHT_TEST);
        batch.end();
    }

    @Override
    public void tick() {
        handleInput();
    }
}
