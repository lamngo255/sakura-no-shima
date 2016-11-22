package dev.game.freaking.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dev.game.freaking.main.FreakingHandler;
import dev.game.freaking.model.Assets;
import dev.game.main.GameHandler;
import dev.game.modules.GameModuleManager;
import dev.game.modules.ModuleStart;

public class HardLevelState extends State {

    private int SCREEN_WIDTH;
    private int SCREEN_HEIGHT;
    private ShapeRenderer shape;
    private TextureRegion bgHardLevel;


    public HardLevelState(FreakingHandler freakingHandler, GameStateManager gsm,
                          GameModuleManager cpanel, GameHandler gameHandler) {
        super(freakingHandler, gsm);

        this.cpanel = cpanel;
        this.gameHandler = gameHandler;
        SCREEN_WIDTH = GameHandler.GAME_WIDTH;
        SCREEN_HEIGHT =  GameHandler.GAME_HEIGHT;
        shape = new ShapeRenderer();
        bgHardLevel = Assets.bgHardLevel;
    }


    private void handleInput() {
        if (Gdx.input.justTouched()) {

            // Choose Easy
            if (Gdx.input.getX() >= GameHandler.GAME_WIDTH * 0.2f
                    && Gdx.input.getX() <= GameHandler.GAME_WIDTH * (0.2f + 0.6f)
                    && Gdx.input.getY() >=  GameHandler.GAME_HEIGHT * (1 - 0.385f - 0.1f)
                    && Gdx.input.getY() <=  GameHandler.GAME_HEIGHT * (1 - 0.385f)) {
                PlayState.setHardLevel(3);
                gsm.set(new PlayState(freakingHandler, gsm, cpanel, gameHandler));
            }

            // Choose Medium
            if (Gdx.input.getX() >= GameHandler.GAME_WIDTH * 0.2f
                    && Gdx.input.getX() <= GameHandler.GAME_WIDTH * (0.2f + 0.6f)
                    && Gdx.input.getY() >=  GameHandler.GAME_HEIGHT * (1 - 0.27f - 0.1f)
                    && Gdx.input.getY() <=  GameHandler.GAME_HEIGHT * (1 - 0.27f)) {
                PlayState.setHardLevel(2);
                gsm.set(new PlayState(freakingHandler, gsm, cpanel, gameHandler));
            }

            // Choose hard
            if (Gdx.input.getX() >= GameHandler.GAME_WIDTH * 0.2f
                    && Gdx.input.getX() <= GameHandler.GAME_WIDTH * (0.2f + 0.6f)
                    && Gdx.input.getY() >=  GameHandler.GAME_HEIGHT * (1 - 0.155f - 0.1f)
                    && Gdx.input.getY() <=  GameHandler.GAME_HEIGHT * (1 - 0.155f)) {
                PlayState.setHardLevel(1);
                gsm.set(new PlayState(freakingHandler, gsm, cpanel, gameHandler));
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
        batch.draw(bgHardLevel, 0, 0, GameHandler.GAME_WIDTH,  GameHandler.GAME_HEIGHT);
        batch.end();
    }

    @Override
    public void dispose() {
        shape.dispose();
    }

}
