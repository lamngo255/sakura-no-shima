package dev.game.doremon.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dev.game.doremon.main.DoremonHandler;
import dev.game.main.GameHandler;
import dev.game.modules.GameModuleManager;


public abstract class State {
    // CLASS
    protected DoremonHandler doremonHandler;
    protected GameStateManager gsm;
    protected GameModuleManager cpanel;
    protected GameHandler gameHandler;

    public State(DoremonHandler doremonHandler, GameStateManager gsm) {
        this.doremonHandler = doremonHandler;
        this.gsm = gsm;
    }

    public abstract void tick();
    public abstract void render(SpriteBatch batch);
    public abstract void dispose();

}
