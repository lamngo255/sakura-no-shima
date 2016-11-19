package dev.doremon.gfx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
    public static TextureRegion background, base, platform_broken,gameplaybackground,gameoverbackground;
    public static TextureRegion[] platform, spring;
    public static TextureRegion[] player_idle, player_jump, player_high, player_fall;

    public static TextureRegion[] playButton;


    public static TextureRegion getFlipped(String path) {
        TextureRegion sample = new TextureRegion(new Texture(path));
        sample.flip(false, true);
        return sample;
    }

    public static void init() {
        Texture sheet = new Texture("sheet.png");

        player_idle = new TextureRegion[2];
        player_jump = new TextureRegion[2];
        player_high = new TextureRegion[2];
        player_fall = new TextureRegion[2];

        platform = new TextureRegion[5];
        spring = new TextureRegion[2];
        playButton = new TextureRegion[2];

        gameplaybackground = getFlipped("capture.png");
        background = getFlipped("menu.png");
        gameoverbackground = getFlipped("gameover1.png");
        player_idle[0] = getFlipped("mon_idle1.png");
        player_idle[1] = getFlipped("mon_idle2.png");
        player_jump[0] = getFlipped("mon_jump1.png");
        player_jump[1] = getFlipped("mon_jump2.png");
        player_high[0] = getFlipped("mon_high1.png");
        player_high[1] = getFlipped("mon_high2.png");
        player_fall[0] = getFlipped("mon_fall1.png");
        player_fall[1] = getFlipped("mon_fall2.png");

        base = new TextureRegion(sheet, 0, 614, 100, 5);
        platform[0] = new TextureRegion(sheet, 0, 0, 105, 31); // white platform
        platform[1] = new TextureRegion(sheet, 0, 61, 105, 31); // blue platform
        platform[2] = new TextureRegion(sheet, 0, 31, 105, 31); // red platform
        platform[3] = new TextureRegion(sheet, 0, 90, 105, 31);
        platform[4] = new TextureRegion(sheet, 0, 645, 105, 31);

        spring[0] = getFlipped("propeller.png");
        spring[1] = getFlipped("propeller.png");

        platform_broken = new TextureRegion(sheet, 0, 554, 105, 60);

        playButton[0] = getFlipped("button/play.png");
        playButton[1] = getFlipped("button/play.png");
    }

    public static void dispose() {
        background.getTexture().dispose();
        player_idle[0].getTexture().dispose();
        player_idle[1].getTexture().dispose();
        player_jump[0].getTexture().dispose();
        player_jump[1].getTexture().dispose();
        player_high[0].getTexture().dispose();
        player_high[1].getTexture().dispose();
        player_fall[0].getTexture().dispose();
        player_fall[1].getTexture().dispose();

        base.getTexture().dispose();
        platform[0].getTexture().dispose();
        platform[1].getTexture().dispose();
        platform[2].getTexture().dispose();
        platform[3].getTexture().dispose();
        platform[4].getTexture().dispose();

        spring[0].getTexture().dispose();
        spring[1].getTexture().dispose();

        platform_broken.getTexture().dispose();

        playButton[0].getTexture().dispose();
        playButton[1].getTexture().dispose();
    }
}
