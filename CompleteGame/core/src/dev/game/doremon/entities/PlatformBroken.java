package dev.game.doremon.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dev.game.doremon.gfx.Assets;
import dev.game.doremon.main.DoremonHandler;


public class PlatformBroken extends Entity {
    public static int DEFAULT_WIDTH = (int) (DoremonHandler.GAME_WIDTH / 4.5);
    public static int DEFAULT_HEIGHT = DoremonHandler.GAME_HEIGHT / 12;
    private boolean appear = false;

    public static PlatformBroken generate(DoremonHandler doremonHandler) {
        return new PlatformBroken(doremonHandler, 0, 0);
    }

    public PlatformBroken(DoremonHandler doremonHandler, float x, float y) {
        super(doremonHandler, x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
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
            if (this.y > DoremonHandler.GAME_HEIGHT / 1.2) {
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
