package dev.game.kanji.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import dev.game.kanji.entities.Assets;
import dev.game.main.GameHandler;
import dev.game.modules.GameModuleManager;
import dev.game.modules.MainGameModule;

/**
 * Created by Ha San~ on 11/22/2016.
 */
public class MenuState extends State {
    private TextureRegion bgMenu;


    public MenuState(GameStateManager gsm, GameModuleManager cpanel, GameHandler gameHandler) {
        super(gsm);

        this.cpanel = cpanel;
        this.gameHandler = gameHandler;
        Assets.init();
        bgMenu = Assets.bgMenu;
    }

    @Override
    public void tick() {
        if (Gdx.input.justTouched()) {
            if (Gdx.input.getX() >= GameHandler.GAME_WIDTH * 0.53f
                    && Gdx.input.getX() <= GameHandler.GAME_WIDTH * 0.93f
                    && Gdx.input.getY() >= GameHandler.GAME_HEIGHT * 0.705f
                    && Gdx.input.getY() <= GameHandler.GAME_HEIGHT * (0.705 + 0.08f)) {
                gsm.set(new PlayState(gsm, cpanel, gameHandler));
            }

            if (Gdx.input.getX() >= GameHandler.GAME_WIDTH * 0.53f
                    && Gdx.input.getX() <= GameHandler.GAME_WIDTH * 0.93f
                    && Gdx.input.getY() >= GameHandler.GAME_HEIGHT * 0.825f
                    && Gdx.input.getY() <= GameHandler.GAME_HEIGHT * (0.825 + 0.08f)) {
                cpanel.set(new MainGameModule(gameHandler, cpanel));
            }
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(bgMenu, 0, 0, GameHandler.GAME_WIDTH, GameHandler.GAME_HEIGHT);
        batch.end();
    }

    @Override
    public void dispose() {
        bgMenu.getTexture().dispose();
        Assets.dispose();
    }
}
