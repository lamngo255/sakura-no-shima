package dev.freaking.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dev.freaking.main.Handler;

public abstract class State {

	protected GameStateManager gsm;
	protected Handler handler;

	public State(Handler handler, GameStateManager gsm) {
        this.handler = handler;
		this.gsm = gsm;
	}

	public abstract void update(float dt);
	public abstract void render(SpriteBatch batch);
    public abstract void dispose();
}
