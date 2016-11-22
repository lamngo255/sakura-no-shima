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
    private BitmapFont gameTitle, gameTitle1, gameTitle2;

    public MainGameModule(GameHandler gameHandler, GameModuleManager cpanel) {
        super(gameHandler, cpanel);
        background = new Texture("background/mainmenugametest.png");
        cloud1 = new Texture("background/may1.png");
        cloud2 = new Texture("background/may2.png");
        xCloud1 = 0 - GameHandler.GAME_WIDTH * 0.25f;
        xCloud2 = GameHandler.GAME_WIDTH;

        gameHandler.changeBackgroundMusic(GameHandler.BACKGROUND_MUSIC_MAIN_MENU);
        gameHandler.playBackgroundMusic();

        generateFont();
    }

    public void generateFont() {
        FreeTypeFontGenerator generator =
                new FreeTypeFontGenerator(Gdx.files.internal("fonts/SF_Shai_Fontai.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter =
                new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = GameHandler.GAME_WIDTH / 6;
        parameter.characters = "SakuranoShima";

        // Sakura
        gameTitle = generator.generateFont(parameter);
        gameTitle.setColor(Color.valueOf("#c04331"));

        // No
        gameTitle1 = generator.generateFont(parameter);
        gameTitle1.setColor(Color.valueOf("#c04331"));

        //Shima
        gameTitle2 = generator.generateFont(parameter);
        gameTitle2.setColor(Color.valueOf("#c04331"));

        generator.dispose();
    }

    private void handleInput() {
        if (Gdx.input.justTouched()) {
            if (Gdx.input.getX() >= GameHandler.GAME_WIDTH * 0.26f
                    && Gdx.input.getX() <= GameHandler.GAME_WIDTH * 0.72f
                    && Gdx.input.getY() >= GameHandler.GAME_HEIGHT * 0.43f
                    && Gdx.input.getY() <= GameHandler.GAME_HEIGHT * 0.52f) {
                cpanel.set(new ModuleStart(gameHandler, cpanel));
            }
        }
    }

    @Override
    public void tick() {
        xCloud1 += GameHandler.GAME_WIDTH / 1800f;
        xCloud2 -= GameHandler.GAME_WIDTH / 1200f;
        if (xCloud1 >= GameHandler.GAME_WIDTH) {
            xCloud1 = 0 - GameHandler.GAME_WIDTH * 0.25f;
        }
        if (xCloud2 <= 0 - GameHandler.GAME_WIDTH * 0.25f) {
            xCloud2 = GameHandler.GAME_WIDTH;
        }
        handleInput();
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
        gameTitle.draw(batch, "Sakura no",
                GameHandler.GAME_WIDTH * 0.2f,
                GameHandler.GAME_HEIGHT * 0.89f);
        gameTitle.draw(batch, "Shima",
                GameHandler.GAME_WIDTH * 0.33f,
                GameHandler.GAME_HEIGHT * 0.80f);
        batch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        cloud1.dispose();
        cloud2.dispose();
        gameTitle.dispose();
        gameTitle1.dispose();
        gameTitle2.dispose();
    }
}
