package com.mygdx.game.main;


import com.mygdx.game.worlds.World;

public class Handler {




    private World world;
    private DoremonJump doremonJump;



    public Handler(DoremonJump doremonJump) {
        this.doremonJump = doremonJump;


    }

    public int getWidth() {
        return doremonJump.getWidth();
    }

    public int getHeight() {
        return doremonJump.getHeight();
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
