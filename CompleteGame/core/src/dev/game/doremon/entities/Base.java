package dev.game.doremon.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dev.game.doremon.gfx.Assets;
import dev.game.doremon.main.DoremonHandler;
import dev.game.main.GameHandler;


public class Base extends Entity {
    public static final int BASE_WIDTH = GameHandler.GAME_WIDTH;
    public static final int BASE_HEIGHT = GameHandler.GAME_HEIGHT / 108;

    private boolean moved = false;

    public static Base generate(DoremonHandler doremonHandler) {
        return new Base(doremonHandler,
                0, GameHandler.GAME_HEIGHT - BASE_HEIGHT);
    }

    public Base(DoremonHandler doremonHandler, float x, float y) {
        super(doremonHandler, x, y, BASE_WIDTH, BASE_HEIGHT);
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
