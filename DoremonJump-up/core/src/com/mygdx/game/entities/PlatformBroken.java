package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.gfx.Assets;
import com.mygdx.game.main.Handler;


public class PlatformBroken extends Entity {
    public static int DEFAULT_WIDTH = 160;
    public static int DEFAULT_HEIGHT = 90;
    private boolean appear = false;

    public static PlatformBroken generate(Handler handler) {
        return new PlatformBroken(handler, 0, 0);
    }

    public PlatformBroken(Handler handler, float x, float y) {
        super(handler, x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);

    }

    @Override
    public void tick() {
        moveDown();
    }

    @Override
    public void render(SpriteBatch batch) {
        if (appear) {
//            batch.draw(Assets.platform_broken,
//                    x * handler.getWorld_to_scene_width(),
//                    y * handler.getWorld_to_scene_height(),
//                    width * handler.getWorld_to_scene_width(),
//                    height * handler.getWorld_to_scene_height());


            batch.draw(Assets.platform_broken,
                     this.x,  this.y,
                    this.width, this.height);
//                    getSceneWidth(), getSceneHeight());
        }
    }

    private void moveDown() {
        if (appear) {
            this.y += 20;
            if (this.y > handler.getHeight() / 1.2) {
                appear = false;
            }
        }
    }

    public boolean isAppear() {
        return appear;
    }

    public void setAppear(boolean appear) {
        this.appear = appear;
    }

    public void dispose() {

    }

}
