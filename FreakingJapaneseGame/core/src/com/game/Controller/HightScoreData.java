package com.game.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class HightScoreData {

	public static Preferences HightScore;
	public static int Score;

	public static void load() {
		HightScore = Gdx.app.getPreferences("MathScore");
	}

	public static int getScoreData() {
		Score = HightScore.getInteger("Score");
		HightScore.flush();
		return Score;
	}
	
	public static void addScore(int Score) {
		HightScore.putInteger("Score", Score);
		HightScore.flush();
	}
}
