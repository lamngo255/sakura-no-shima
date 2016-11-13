package com.game.Controller;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.game.View.Main;

public abstract class GameState {

	protected GameStateManager gsm;
	protected Main math;
	protected Stage stage;

	public GameState(GameStateManager gsm) {
		super();
		this.gsm = gsm;
		math = gsm.getMath();
		stage = math.getStage();
	}

	public abstract void update(float dt);

	public abstract void draw();

}
