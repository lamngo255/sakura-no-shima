package dev.game.kanji.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by Ha San~ on 11/22/2016.
 */
public class GameStateManager {

    private Stack<dev.game.kanji.states.State> states;

    public GameStateManager() {
        states = new Stack<dev.game.kanji.states.State>();
    }

    public void set(dev.game.kanji.states.State state) {
        states.pop().dispose();
        states.push(state);
    }

    public void pop() {
        states.pop().dispose();
    }

    public void push(dev.game.kanji.states.State state) {
        states.push(state);
    }


    public void tick() {
        states.peek().tick();
    }

    public void render(SpriteBatch batch) {
        states.peek().render(batch);
    }

    public void dispose() {
        for (dev.game.kanji.states.State state : states) {
            state.dispose();
        }
    }
}