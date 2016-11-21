package dev.game.doremon.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import dev.game.doremon.gfx.Assets;
import dev.game.doremon.main.DoremonHandler;
import dev.game.main.GameHandler;
import dev.game.modules.GameModuleManager;
import dev.game.modules.MainGameModule;


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
        if(Gdx.input.justTouched()){
            if(Gdx.input.getX()>= DoremonHandler.GAME_WIDTH * 0.056f&&
                    Gdx.input.getX()<= DoremonHandler.GAME_WIDTH*0.53f&&
                    Gdx.input.getY()>= DoremonHandler.GAME_HEIGHT *0.6f&&
                    Gdx.input.getY()<= DoremonHandler.GAME_HEIGHT*0.7f){
                gsm.set(new PlayingGuide(doremonHandler, gsm, cpanel, gameHandler));
            }
            else if(Gdx.input.getX()>= DoremonHandler.GAME_WIDTH * 0.056f&&
                    Gdx.input.getX()<= DoremonHandler.GAME_WIDTH*0.53f&&
                    Gdx.input.getY()>= DoremonHandler.GAME_HEIGHT *0.73f&&
                    Gdx.input.getY()<= DoremonHandler.GAME_HEIGHT*0.83f){
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
        batch.draw(background, 0, 0, DoremonHandler.GAME_WIDTH, DoremonHandler.GAME_HEIGHT);
        batch.draw(playButton, DoremonHandler.GAME_WIDTH * 0.056f, DoremonHandler.GAME_HEIGHT *0.6f, DoremonHandler.GAME_WIDTH * 0.47f, DoremonHandler.GAME_HEIGHT * 0.09f);
        batch.draw(homeButton, DoremonHandler.GAME_WIDTH * 0.056f, DoremonHandler.GAME_HEIGHT * 0.73f, DoremonHandler.GAME_WIDTH * 0.47f, DoremonHandler.GAME_HEIGHT * 0.09f);
        batch.end();
    }

    @Override
    public void dispose() {
    }
}
