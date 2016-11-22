package dev.game.modules;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import dev.game.main.GameHandler;

/**
 * Created by Lam Ngo on 11/21/2016.
 */
public class MainGameModule extends Module {
    private Texture background;
    private Texture cloud1, cloud2;
    private float xCloud1, xCloud2;
    private BitmapFont gameTitle;

    public MainGameModule(GameHandler gameHandler, GameModuleManager cpanel) {
        super(gameHandler, cpanel);
        background = new Texture("background/mainmenugametest.png");
        cloud1 = new Texture("background/may1.png");
        cloud2 = new Texture("background/may2.png");
        xCloud1 = 0 - GameHandler.GAME_WIDTH * 0.25f;
        xCloud2 = GameHandler.GAME_WIDTH;

        generateFont();
    }

    public void generateFont() {
        FreeTypeFontGenerator generator =
                new FreeTypeFontGenerator(Gdx.files.internal("fonts/SF_Shai_Fontai.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter =
                new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = GameHandler.GAME_WIDTH / 6;
        parameter.characters = "GameTitle";
        gameTitle = generator.generateFont(parameter);
        gameTitle.setColor(Color.valueOf("#c04331"));
        generator.dispose();
    }

    @Override
    public void tick() {
        if (Gdx.input.justTouched()) {
            cpanel.set(new ModuleStart(gameHandler, cpanel));
        }
        xCloud1 += 0.25;
        xCloud2 -= 0.25;
        if (xCloud1 >= GameHandler.GAME_WIDTH) {
            xCloud1 = 0 - GameHandler.GAME_WIDTH * 0.25f;
        }
        if (xCloud2 <= 0 - GameHandler.GAME_WIDTH * 0.25f) {
            xCloud2 = GameHandler.GAME_WIDTH;
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(background, 0, 0, GameHandler.GAME_WIDTH, GameHandler.GAME_HEIGHT);
        batch.draw(cloud1, xCloud1, GameHandler.GAME_HEIGHT * 0.65f,
                    GameHandler.GAME_WIDTH * 0.25f, GameHandler.GAME_HEIGHT * 0.07f);
        batch.draw(cloud2, xCloud2, GameHandler.GAME_HEIGHT * 0.83f,
                GameHandler.GAME_WIDTH * 0.25f, GameHandler.GAME_HEIGHT * 0.07f);
        batch.draw(cloud2, xCloud1, GameHandler.GAME_HEIGHT * 0.87f,
                GameHandler.GAME_WIDTH * 0.15f, GameHandler.GAME_HEIGHT * 0.05f);
        gameTitle.draw(batch, "Game Title",
                GameHandler.GAME_WIDTH * 0.2f,
                GameHandler.GAME_HEIGHT * 0.8f);
        batch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        cloud1.dispose();
        cloud2.dispose();
        gameTitle.dispose();
    }
}
