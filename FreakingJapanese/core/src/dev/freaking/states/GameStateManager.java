package dev.freaking.states;

import dev.freaking.view.*;

import java.util.Stack;

public class GameStateManager {

	private Main main;
	private Stack<GameState> gameStates;
	public static final int Play = 1;
	public static final int GAME_OVER = 2;
	public static final int MAIN_MENU = 0;

	public GameStateManager(Main main) {
		super();
		this.main = main;
		gameStates = new Stack<GameState>();
		pushState(MAIN_MENU);
	}

	public Main getMain() {
		return main;
	}

	public void update(float dt) {
		gameStates.peek().update(dt);
	}

	public void draw() {
		gameStates.peek().draw();
	}

	public GameState setState(int state) {
		if (state == Play) {
			return new Play(this);
		} else if (state == GAME_OVER) {
			return new GameOverState(this);
		} else if(state == MAIN_MENU){
			return new MainMenu(this);
		}else {
			return null;
		}
	}

	public void pushState(int state) {
		gameStates.push(setState(state));
	}
}

