package dev.game.modules;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Stack;

/**
 * Created by Lam Ngo on 11/21/2016.
 */
public class GameModuleManager {

    private Stack<Module> modules;

    public GameModuleManager() {
        modules = new Stack<Module>();
    }

    public void push(Module module) {
        modules.push(module);
    }

    public void pop() {
        modules.pop().dispose();
    }

    public void set(Module Module) {
        modules.pop().dispose();
        modules.push(Module);
    }

    public void tick() {
        modules.peek().tick();
    }
    public void render(SpriteBatch batch) {
        modules.peek().render(batch);
    }
    public void dispose() {
        for (Module module : modules) {
            module.dispose();
        }
    }

    public boolean isEmpty() {
        return modules.isEmpty();
    }
}
