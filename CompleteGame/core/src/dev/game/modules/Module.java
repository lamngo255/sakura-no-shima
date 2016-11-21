package dev.game.modules;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dev.game.main.GameHandler;

/**
 * Created by Lam Ngo on 11/21/2016.
 */
public abstract class Module {
    // CLASS
    protected GameHandler gameHandler;
    protected GameModuleManager cpanel;

    public Module(GameHandler gameHandler, GameModuleManager cpanel) {
        this.gameHandler = gameHandler;
        this.cpanel = cpanel;
    }

    public abstract void tick();
    public abstract void render(SpriteBatch batch);
    public abstract void dispose();
}
