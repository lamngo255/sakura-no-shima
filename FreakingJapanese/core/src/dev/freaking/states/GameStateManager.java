package dev.freaking.states;

import java.util.Stack;

public class GameStateManager {

	private Stack<State> states;

	public GameStateManager() {
		states = new Stack<State>();
	}

	public void set(State state) {
        states.pop().dispose();
		states.push(state);
	}

	public void pop() {
		states.pop().dispose();
	}

	public void push(State state) {
		states.push(state);
	}


	public void update(float dt) {
		states.peek().update(dt);
	}

	public void draw() {
		states.peek().draw();
	}

	public void dispose() {
		for (State state : states) {
			state.dispose();
		}
	}
}

