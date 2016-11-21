package dev.game.doremon.main;


import com.badlogic.gdx.Gdx;
import dev.game.doremon.worlds.World;
import dev.game.modules.DoremonModule;

public class DoremonHandler {

    public static int GAME_WIDTH = Gdx.graphics.getWidth();
    public static int GAME_HEIGHT = Gdx.graphics.getHeight();
    private World world;
    private DoremonModule doremonModule;

    public DoremonHandler(DoremonModule doremonModule) {
        this.doremonModule = doremonModule;
    }

    public void setWorld(World world) {
        this.world = world;
    }
    public World getWorld() {
        return this.world;
    }
    public DoremonModule getGame() {
        return this.doremonModule;
    }
}
