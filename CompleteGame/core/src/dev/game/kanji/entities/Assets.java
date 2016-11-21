package dev.game.kanji.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Ha San~ on 11/22/2016.
 */
public class Assets {
    public static TextureRegion bgMenu;

    public static TextureRegion getTexture(String path) {
        TextureRegion sample = new TextureRegion(new Texture(path));
        return sample;
    }

    public static void init() {
        bgMenu = getTexture("kanjinumberback.png");
    }

    public static void dispose() {
        bgMenu.getTexture().dispose();
    }
}
