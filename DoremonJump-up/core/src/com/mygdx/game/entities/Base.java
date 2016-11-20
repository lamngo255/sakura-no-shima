package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.gfx.Assets;
import com.mygdx.game.main.Handler;


public class Base extends Entity {
    public static final int BASE_WIDTH = 1000;
    public static final int BASE_HEIGHT = 10;

    private boolean moved = false;

    public static Base generate(Handler handler) {
        return new Base(handler,
                0, handler.getHeight() - BASE_HEIGHT);
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
//        batch.draw(Assets.base, x * handler.getWorld_to_scene_width(),
//                y * handler.getWorld_to_scene_height(),
//                width * handler.getWorld_to_scene_width(),
//                height * handler.getWorld_to_scene_height());

//        batch.draw(Assets.base, x, y, getSceneWidth(), getSceneHeight());
    }

    public void dispose() {

    }
}
