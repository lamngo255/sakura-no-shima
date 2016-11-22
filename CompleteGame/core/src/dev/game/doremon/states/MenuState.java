package dev.game.doremon.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import dev.game.doremon.gfx.Assets;
import dev.game.doremon.main.DoremonHandler;
import dev.game.main.CompleteGame;
import dev.game.main.GameHandler;
import dev.game.modules.GameModuleManager;
import dev.game.modules.MainGameModule;
import dev.game.modules.ModuleDoremon;


public class MenuState extends State {
    private TextureRegion playButton;
    private TextureRegion background;
    private TextureRegion homeButton;

    public MenuState(DoremonHandler doremonHandler, GameStateManager gsm,
                     GameModuleManager cpanel, GameHandler gameHandler) {
        super(doremonHandler, gsm);
        this.cpanel = cpanel;
        this.gameHandler = gameHandler;
        this.playButton = Assets.playButton[0];
        this.background = Assets.background;
        this.homeButton = Assets.playButton[2];
    }

    public void handleInput() {


        if (Gdx.input.justTouched()) {
//            if(Gdx.input.getX()>= GameHandler.GAME_WIDTH * 0.056f&&
//                    Gdx.input.getX()<= GameHandler.GAME_WIDTH*0.53f&&
//                    Gdx.input.getY()>= GameHandler.GAME_HEIGHT *0.6f&&
//                    Gdx.input.getY()<= GameHandler.GAME_HEIGHT*0.7f)
            if (ModuleDoremon.checkIfButtonPressed(CompleteGame.WORLD_WIDTH_TEST * 0.056f,
                    CompleteGame.WORLD_WIDTH_TEST*0.53f,
                    CompleteGame.WORLD_HEIGHT_TEST * 0.6f,
                    CompleteGame.WORLD_HEIGHT_TEST * 0.7f
            ))
            {

                gsm.set(new PlayingGuide(doremonHandler, gsm, cpanel, gameHandler));
            }

//            else if(Gdx.input.getX()>= GameHandler.GAME_WIDTH * 0.056f&&
//                    Gdx.input.getX()<= GameHandler.GAME_WIDTH*0.53f&&
//                    Gdx.input.getY()>= GameHandler.GAME_HEIGHT *0.73f&&
//                    Gdx.input.getY()<= GameHandler.GAME_HEIGHT*0.83f)
            else if (ModuleDoremon.checkIfButtonPressed(CompleteGame.WORLD_WIDTH_TEST * 0.056f,
                    CompleteGame.WORLD_WIDTH_TEST*0.53f,
                    CompleteGame.WORLD_HEIGHT_TEST * 0.73f,
                    CompleteGame.WORLD_HEIGHT_TEST * 0.83f
            ))
            {
                cpanel.set(new MainGameModule(gameHandler, cpanel));
            }
        }
    }

    @Override
    public void tick() {
        handleInput();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();

        batch.draw(background, 0, 0, CompleteGame.WORLD_WIDTH_TEST, CompleteGame.WORLD_HEIGHT_TEST);
        batch.draw(playButton, CompleteGame.WORLD_WIDTH_TEST * 0.056f, CompleteGame.WORLD_HEIGHT_TEST * 0.6f, CompleteGame.WORLD_WIDTH_TEST * 0.47f, CompleteGame.WORLD_HEIGHT_TEST * 0.09f);
        batch.draw(homeButton, CompleteGame.WORLD_WIDTH_TEST * 0.056f, CompleteGame.WORLD_HEIGHT_TEST * 0.73f, CompleteGame.WORLD_WIDTH_TEST * 0.47f, CompleteGame.WORLD_HEIGHT_TEST * 0.09f);
        batch.end();
    }

    @Override
    public void dispose() {
    }
}
