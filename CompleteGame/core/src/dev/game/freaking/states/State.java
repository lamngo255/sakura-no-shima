package dev.game.freaking.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dev.game.freaking.main.FreakingHandler;
import dev.game.main.GameHandler;
import dev.game.modules.GameModuleManager;

public abstract class State {

	protected GameStateManager gsm;
	protected FreakingHandler freakingHandler;
	protected GameModuleManager cpanel;
	protected GameHandler gameHandler;

	public State(FreakingHandler freakingHandler, GameStateManager gsm) {
        this.freakingHandler = freakingHandler;
		this.gsm = gsm;
	}

	public abstract void tick();
	public abstract void render(SpriteBatch batch);
    public abstract void dispose();
}
