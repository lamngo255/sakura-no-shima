package dev.game.freaking.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dev.game.freaking.main.FreakingHandler;

public abstract class State {

	protected GameStateManager gsm;
	protected FreakingHandler freakingHandler;

	public State(FreakingHandler freakingHandler, GameStateManager gsm) {
        this.freakingHandler = freakingHandler;
		this.gsm = gsm;
	}

	public abstract void update(float dt);
	public abstract void render(SpriteBatch batch);
    public abstract void dispose();
}
