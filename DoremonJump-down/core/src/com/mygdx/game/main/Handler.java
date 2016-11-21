package com.mygdx.game.main;


import com.badlogic.gdx.Gdx;
import com.mygdx.game.worlds.World;

public class Handler {

    public static int GAME_WIDTH = Gdx.graphics.getWidth();
    public static int GAME_HEIGHT = Gdx.graphics.getHeight();
    private World world;
    private DoremonJump doremonJump;

    public Handler(DoremonJump doremonJump) {
        this.doremonJump = doremonJump;
    }

    public void setWorld(World world) {
        this.world = world;
    }
    public World getWorld() {
        return this.world;
    }
    public DoremonJump getGame() {
        return this.doremonJump;
    }
}
