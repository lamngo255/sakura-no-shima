package dev.game.modules;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import dev.game.doremon.gfx.Assets;
import dev.game.doremon.main.DoremonHandler;
import dev.game.doremon.states.GameState;
import dev.game.doremon.states.GameStateManager;
import dev.game.doremon.states.MenuState;
import dev.game.doremon.states.PlayingGuide;
import dev.game.main.CompleteGame;
import dev.game.main.GameHandler;

import java.util.Vector;

/**
 * Created by Lam Ngo on 11/21/2016.
 */
public class ModuleDoremon extends Module {
    private static final java.lang.String BEST_SCORE_KEY = "Doremon_best_score";

    public static OrthographicCamera camera;
    public static int bestScore = 0;
    private DoremonHandler doremonHandler;
    private GameStateManager gsm;

    public static Viewport viewport;


    public ModuleDoremon(GameHandler gameHandler, GameModuleManager cpanel) {
        super(gameHandler, cpanel);
        Assets.init();
        init();
    }

    private void init() {
        CompleteGame.preferences = Gdx.app.getPreferences(CompleteGame.class.getName());
        bestScore = CompleteGame.preferences.getInteger(BEST_SCORE_KEY, 0);
        viewport.apply();

        doremonHandler = new DoremonHandler(this);
        gsm = new GameStateManager();
        gsm.push(new MenuState(doremonHandler, gsm, cpanel, gameHandler));
    }

    @Override
    public void tick() {
        gsm.tick();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gsm.render(batch);
    }

    public static void updateBestScore(int score) {
        if (score > bestScore) {
            bestScore = score;
            CompleteGame.preferences.putInteger(BEST_SCORE_KEY, bestScore);
            CompleteGame.preferences.flush();
        }
    }

    @Override
    public void dispose() {
        gsm.dispose();
        Assets.dispose();
    }

    public static boolean checkIfButtonPressed(float xLeft, float xRight, float yTop, float yBot) {
        Vector3 worldInputCoordinates = ModuleDoremon.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0));
        return  (worldInputCoordinates.x >= xLeft &&
                worldInputCoordinates.x <= xRight &&
                worldInputCoordinates.y >= yTop &&
                worldInputCoordinates.y<= yBot);
    }
}
