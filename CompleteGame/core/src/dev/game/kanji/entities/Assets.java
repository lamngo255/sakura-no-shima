package dev.game.kanji.entities;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import dev.game.main.CompleteGame;

/**
 * Created by Ha San~ on 11/22/2016.
 */
public class Assets {

    private static final java.lang.String BEST_SCORE_KEY = "kanji_best_score";
    public static int bestScore;
    public static Preferences preferences;
    public static TextureRegion bgMenu;
    public static TextureRegion homeButton, replayButton;

    public static TextureRegion getTexture(String path) {
        TextureRegion sample = new TextureRegion(new Texture(path));
        return sample;
    }

    public static void init() {
        preferences = CompleteGame.preferences;
        bestScore = preferences.getInteger(BEST_SCORE_KEY, 0);
        bgMenu = getTexture("kanjinumberback.png");

        homeButton = getTexture("button/home_kanji.png");
        replayButton = getTexture("button/replay_kanji.png");
    }

    public static void dispose() {
        bgMenu.getTexture().dispose();
        homeButton.getTexture().dispose();
        replayButton.getTexture().dispose();
    }

    public static void updateHighScore(int score) {
        if (score > bestScore) {
            bestScore = score;
            preferences.putInteger(BEST_SCORE_KEY, bestScore);
            preferences.flush();
        }
    }

    public static float getCenterAlignmentPositionX(float parentWidth, float parentX, int textLength, float spaceLength) {
        float middleX = parentX + parentWidth / 2;
        float offset = (float)(textLength / 2) * spaceLength;
        return (middleX - offset);
    }

}
