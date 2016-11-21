package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.gfx.Assets;
import com.mygdx.game.main.Handler;


public class PlatformBroken extends Entity {
    public static int DEFAULT_WIDTH = (int) (Handler.GAME_WIDTH / 4.5);
    public static int DEFAULT_HEIGHT = Handler.GAME_HEIGHT / 12;
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
            batch.draw(Assets.platform_broken,
                    this.x, this.y,
                    this.width, this.height);
        }
    }

    private void moveDown() {
        if (appear) {
            this.y += 20;
            if (this.y > Handler.GAME_HEIGHT / 1.2) {
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
