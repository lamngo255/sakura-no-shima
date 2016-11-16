package dev.freaking.states;

import com.badlogic.gdx.scenes.scene2d.Stage;
import dev.freaking.main.FreakingGame;
import dev.freaking.main.Handler;

public abstract class State {

	protected GameStateManager gsm;
	protected Handler handler;
	protected Stage stage;

	public State(Handler handler, GameStateManager gsm) {
        this.handler = handler;
		this.gsm = gsm;
		this.stage = handler.getStage();
	}

	public abstract void update(float dt);
	public abstract void draw();
    public abstract void dispose();
}
