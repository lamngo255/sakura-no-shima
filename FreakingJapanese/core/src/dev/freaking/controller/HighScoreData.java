package dev.freaking.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class HighScoreData {

	public static Preferences highScore;
	public static int Score;

	public static void load() {
		highScore = Gdx.app.getPreferences("MathScore");
	}

	public static int getScoreData() {
		Score = highScore.getInteger("Score");
		highScore.flush();
		return Score;
	}
	
	public static void addScore(int Score) {
		highScore.putInteger("Score", Score);
		highScore.flush();
	}
}
