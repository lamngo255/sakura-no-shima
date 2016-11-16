package dev.freaking.states;

import com.badlogic.gdx.scenes.scene2d.Stage;
import dev.freaking.view.Main;

public abstract class GameState {

	protected GameStateManager gsm;
	protected Main math;
	protected Stage stage;

	public GameState(GameStateManager gsm) {
		super();
		this.gsm = gsm;
		math = gsm.getMain();
		stage = math.getStage();
	}

	public abstract void update(float dt);

	public abstract void draw();

}
