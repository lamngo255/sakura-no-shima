package dev.game.modules;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dev.game.main.GameHandler;

/**
 * Created by Lam Ngo on 11/21/2016.
 */
public class MainGameModule extends Module {

    public MainGameModule(GameHandler gameHandler, GameModuleManager cpanel) {
        super(gameHandler, cpanel);
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
            System.out.println(Gdx.input.getX());
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        Gdx.gl.glClearColor(1, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glEnable(GL20.GL_BLEND);
    }

    @Override
    public void dispose() {

    }
}
