package dev.game.doremon.main;


import com.badlogic.gdx.Gdx;
import dev.game.doremon.worlds.World;
import dev.game.modules.ModuleDoremon;

public class DoremonHandler {

    private World world;
    private ModuleDoremon moduleDoremon;

    public DoremonHandler(ModuleDoremon moduleDoremon) {
        this.moduleDoremon = moduleDoremon;
    }

    public void setWorld(World world) {
        this.world = world;
    }
    public World getWorld() {
        return this.world;
    }
    public ModuleDoremon getGame() {
        return this.moduleDoremon;
    }
}
