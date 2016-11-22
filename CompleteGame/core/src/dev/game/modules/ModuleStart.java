package dev.game.modules;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dev.game.main.GameHandler;

/**
 * Created by Lam Ngo on 11/22/2016.
 */
public class ModuleStart extends Module {

    private Texture background;

    public ModuleStart(GameHandler gameHandler, GameModuleManager cpanel) {
        super(gameHandler, cpanel);
        if (!gameHandler.getPlayingMusic().equals(GameHandler.BACKGROUND_MUSIC_MAIN_MENU)) {

            gameHandler.changeBackgroundMusic(GameHandler.BACKGROUND_MUSIC_MAIN_MENU);
        }
        gameHandler.playBackgroundMusic();
        background = new Texture("background/MapMenu.png");
    }

    @Override
    public void tick() {
        if (Gdx.input.justTouched()) {
            // Game Kanji
            if (Gdx.input.getX() >= GameHandler.GAME_WIDTH * 0.03f
                    && Gdx.input.getX() <= GameHandler.GAME_WIDTH * (0.03f + 0.44f)
                    && Gdx.input.getY() >= GameHandler.GAME_HEIGHT * 0.485f
                    && Gdx.input.getY() <= GameHandler.GAME_HEIGHT * (0.485f + 0.08f)) {
                cpanel.set(new ModuleDoremon(gameHandler, cpanel));
            }

            // Game Hira
            if (Gdx.input.getX() >= GameHandler.GAME_WIDTH * 0.535f
                    && Gdx.input.getX() <= GameHandler.GAME_WIDTH * (0.535f + 0.44f)
                    && Gdx.input.getY() >= GameHandler.GAME_HEIGHT * 0.165f
                    && Gdx.input.getY() <= GameHandler.GAME_HEIGHT * (0.165f + 0.08f)) {
                cpanel.set(new ModuleFreaking(gameHandler, cpanel));
            }

            // Game Doremon
            if (Gdx.input.getX() >= GameHandler.GAME_WIDTH * 0.475f
                    && Gdx.input.getX() <= GameHandler.GAME_WIDTH * (0.475f + 0.44f)
                    && Gdx.input.getY() >= GameHandler.GAME_HEIGHT * 0.847f
                    && Gdx.input.getY() <= GameHandler.GAME_HEIGHT * (0.847f + 0.08f)) {
                cpanel.set(new ModuleKanji(gameHandler, cpanel));
            }

            // Back to main screen
            if (Gdx.input.getX() >= GameHandler.GAME_WIDTH * 0.028f
                    && Gdx.input.getX() <= GameHandler.GAME_WIDTH * (0.028f + 0.1f)
                    && Gdx.input.getY() >= GameHandler.GAME_HEIGHT * 0.867f
                    && Gdx.input.getY() <= GameHandler.GAME_HEIGHT * (0.867f + 0.12f)) {
                cpanel.set(new MainGameModule(gameHandler, cpanel));
            }
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(background, 0, 0, GameHandler.GAME_WIDTH, GameHandler.GAME_HEIGHT);
        batch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
