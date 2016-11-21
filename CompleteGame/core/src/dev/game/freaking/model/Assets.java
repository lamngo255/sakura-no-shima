package dev.game.freaking.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Lam Ngo on 11/20/2016.
 */
public class Assets {
    public static TextureRegion bgMenu,bgGameOver;

    public static TextureRegion getTexture(String path) {
        TextureRegion sample = new TextureRegion(new Texture(path));
        return sample;
    }

    public static void init() {
        bgMenu = getTexture("bgMenu.png");
        bgGameOver = getTexture("bgGameOver.png");
    }

    public static void dispose() {
        bgMenu.getTexture().dispose();
        bgGameOver.getTexture().dispose();
    }

}
