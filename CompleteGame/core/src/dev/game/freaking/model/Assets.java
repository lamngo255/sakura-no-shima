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
    public static TextureRegion bgMenu, bgGameOver,bgHardLevel;
    public static TextureRegion[] buttonCases;
    public static TextureRegion[] homeButton, replayButton;
    public static Preferences preferences;
    public static int highScore;

    public static TextureRegion getTexture(String path) {
        TextureRegion sample = new TextureRegion(new Texture(path));
        return sample;
    }

    public static void init() {
        Texture sheet = new Texture("button/freaking_button.png");
        Texture sheet1 = new Texture("button/btn_freaking_home.png");
        Texture sheet2 = new Texture("button/btn_freaking_replay.png");

        buttonCases = new TextureRegion[2];
        replayButton = new TextureRegion[2];
        homeButton = new TextureRegion[2];
        bgMenu = getTexture("bgMenu.png");
        bgHardLevel=getTexture("bgHardLevel.png");
        bgGameOver = getTexture("gameover_freakingnO1.png");
        buttonCases[0] = new TextureRegion(sheet, 0, 0, 512, 512);
        buttonCases[1] = new TextureRegion(sheet, 512, 0, 512, 512);
        homeButton[0] = new TextureRegion(sheet1, 0, 0, 418, 163);
        homeButton[1] = new TextureRegion(sheet1, 418, 0, 418, 163);
        replayButton[0] = new TextureRegion(sheet2, 0, 0, 418, 163);
        replayButton[1] = new TextureRegion(sheet2, 418, 0, 418, 163);

        preferences = CompleteGame.preferences;
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
        bgHardLevel.getTexture().dispose();
        bgGameOver.getTexture().dispose();

        for (TextureRegion button : buttonCases) {
            if (button != null)
                button.getTexture().dispose();
        }
        for (TextureRegion button : homeButton) {
            if (button != null)
                button.getTexture().dispose();
        }
        for (TextureRegion button : replayButton) {
            if (button != null)
                button.getTexture().dispose();
        }
    }

}
