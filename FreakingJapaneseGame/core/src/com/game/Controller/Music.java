package com.game.Controller;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Music {

	private static HashMap<String, Sound> audio = new HashMap<String, Sound>();

	public static void load(String name, String path) {
		audio.put(name, Gdx.audio.newSound(Gdx.files.internal(path)));
	}
	
	public static void play(String name) {
		audio.get(name).play();
	}
}
