package dev.game.modules;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dev.game.main.GameHandler;

/**
 * Created by Lam Ngo on 11/21/2016.
 */
public class MainGameModule extends Module {
    private Texture background;

    public MainGameModule(GameHandler gameHandler, GameModuleManager cpanel) {
        super(gameHandler, cpanel);
        background = new Texture("mainmenugame.png");
    }

    @Override
    public void tick() {
        if (Gdx.input.justTouched()) {
            if (Gdx.input.getX() <= GameHandler.GAME_WIDTH / 3) {
                cpanel.set(new ModuleDoremon(gameHandler, cpanel));
            } else if (Gdx.input.getX() <= GameHandler.GAME_WIDTH * 2/3){
                cpanel.set(new ModuleKanji(gameHandler, cpanel));
            } else {
                cpanel.set(new ModuleFreaking(gameHandler, cpanel));
            }
        }
    }

    @Override
    public void render(SpriteBatch batch) {
//
        batch.begin();
        batch.draw(background, 0, 0, GameHandler.GAME_WIDTH, GameHandler.GAME_HEIGHT);
        batch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
