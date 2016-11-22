package dev.game.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import dev.game.main.CompleteGame;

/**
 * Created by Lam Ngo on 11/21/2016.
 */
public class GameHandler {

    public static final String BACKGROUND_MUSIC_MAIN_MENU = "ByeByeAccelWorldOST-V.A.mp3";
    public static final String BACKGROUND_MUSIC_DOREMON_JUMP = "Kevin MacLeod ~ Merry Go.wav";
    public static final String BACKGROUND_MUSIC_KANJI = "Mitsuha_No_Theme.mp3";
    public static final String BACKGROUND_MUSIC_FREAKING = "Mitsuha_No_Tsugaku.mp3";


    public static int GAME_WIDTH = Gdx.graphics.getWidth();
    public static int GAME_HEIGHT = Gdx.graphics.getHeight();
//public static int GAME_WIDTH = CompleteGame.WORLD_WIDTH_TEST;
//    public static int GAME_HEIGHT = CompleteGame.WORLD_HEIGHT_TEST;


    private Music backgroundMusic;
    private CompleteGame completeGame;
    private String playingMusic;

    public GameHandler(CompleteGame completeGame) {
        this.completeGame = completeGame;
        playingMusic = "";
//        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal(""));

    }

    public void playBackgroundMusic() {
        if (backgroundMusic != null) {
            backgroundMusic.play();
        }
    }

    public void pauseBackgroundMusic() {
        backgroundMusic.pause();
    }

    public void stopBackgroundMusic() {
        backgroundMusic.stop();
    }

    public void changeBackgroundMusic(String musicFilePath) {
        if (backgroundMusic != null) {
            backgroundMusic.dispose();
        }
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal(musicFilePath));
        backgroundMusic.setLooping(true);
        playingMusic = musicFilePath;

    }

    public String getPlayingMusic() {
        return playingMusic;
    }

    public void dispose() {
        if (backgroundMusic != null) {
            backgroundMusic.dispose();
        }
    }


}
