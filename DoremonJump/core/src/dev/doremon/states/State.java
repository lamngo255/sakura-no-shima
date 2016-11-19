package dev.doremon.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dev.doremon.main.Handler;


public abstract class State {
//    private static State currentState = null;
//    public static void setState(State state) {
//        currentState = state;
//    }
//    public static State getState() {
//        return currentState;
//    }

    // CLASS
    protected Handler handler;
    protected GameStateManager gsm;

    public State(Handler handler, GameStateManager gsm) {
        this.handler = handler;
        this.gsm = gsm;
    }

    public abstract void tick();
    public abstract void render(SpriteBatch batch);
    public abstract void dispose();

}
