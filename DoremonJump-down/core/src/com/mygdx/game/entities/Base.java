package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.gfx.Assets;
import com.mygdx.game.main.Handler;


public class Base extends Entity {
    public static final int BASE_WIDTH = Handler.GAME_WIDTH;
    public static final int BASE_HEIGHT = Handler.GAME_HEIGHT / 108;

    private boolean moved = false;

    public static Base generate(Handler handler) {
        return new Base(handler,
                0, Handler.GAME_HEIGHT - BASE_HEIGHT);
    }

    public Base(Handler handler, float x, float y) {
        super(handler, x, y, BASE_WIDTH, BASE_HEIGHT);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(Assets.base, x, y, width, height);
    }

    public void dispose() {

    }
}
