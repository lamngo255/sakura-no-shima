package dev.game.freaking.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import dev.game.main.CompleteGame;

/**
 * Created by Lam Ngo on 11/20/2016.
 */
public class Assets {
    private static final String HIGH_SCORE_KEY = "Freaking_high_score";
    public static TextureRegion bgMenu,bgGameOver;
    public static Preferences preferences;
    public static int highScore;

    public static TextureRegion getTexture(String path) {
        TextureRegion sample = new TextureRegion(new Texture(path));
        return sample;
    }

    public static void init() {
        bgMenu = getTexture("bgMenu.png");
        bgGameOver = getTexture("bgGameOver.png");

        preferences = Gdx.app.getPreferences(CompleteGame.class.getName());
        highScore = preferences.getInteger(HIGH_SCORE_KEY, 0);
    }

    public static void updateHighScore(int score) {
        if (score > highScore) {
            highScore = score;
            preferences.putInteger(HIGH_SCORE_KEY, highScore);
            preferences.flush();
        }
    }

    public static void dispose() {
        bgMenu.getTexture().dispose();
        bgGameOver.getTexture().dispose();
    }

}
