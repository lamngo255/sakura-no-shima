package com.game.Controller;

import com.game.View.*;
import com.game.View.GameOverState;

import java.util.Stack;

public class GameStateManager {

	private Main math;
	private Stack<GameState> gameStates;
	public static final int Play = 1;
	public static final int GAMEOVER = 2;
	public static final int MAINMENU = 0;

	public GameStateManager(Main math) {
		super();
		this.math = math;
		gameStates = new Stack<GameState>();
		pusState(MAINMENU);
	}

	public Main getMath() {
		return math;
	}

	public void update(float dt) {
		gameStates.peek().update(dt);
	}

	public void draw() {
		gameStates.peek().draw();
	}

	public GameState setstate(int state) {
		if (state == Play) {
			return new com.game.View.Play(this);
		} else if (state == GAMEOVER) {
			return new GameOverState(this);
		} else if(state == MAINMENU){
			return  new com.game.View.MainMenu(this);
		}else {
			return null;
		}
	}

	public void pusState(int state) {
		gameStates.push(setstate(state));
	}
}
