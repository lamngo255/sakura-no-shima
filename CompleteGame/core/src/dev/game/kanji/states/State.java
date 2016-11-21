package dev.game.kanji.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dev.game.kanji.states.GameStateManager;
import dev.game.main.GameHandler;
import dev.game.modules.GameModuleManager;

/**
 * Created by Ha San~ on 11/22/2016.
 */
public abstract class State {

    protected GameStateManager gsm;
    protected GameModuleManager cpanel;
    protected GameHandler gameHandler;

    public State(GameStateManager gsm) {
        this.gsm = gsm;
    }

    public abstract void tick();
    public abstract void render(SpriteBatch batch);
    public abstract void dispose();
}