package dev.game.freaking.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import dev.game.freaking.main.FreakingHandler;
import dev.game.freaking.model.Assets;
import dev.game.main.GameHandler;
import dev.game.modules.GameModuleManager;

public class GameOverState extends State {

    private int SCREEN_WIDTH = Gdx.graphics.getWidth();
    private int SCREEN_HEIGHT = Gdx.graphics.getHeight();
    private int score;
    private BitmapFont gameOverFont, scoreFont, bestScoreFont;
    private TextureRegion background;
    private TextureRegion[] homeButton, replayButton;
    private int homePressed, replayPressed;

    public GameOverState(FreakingHandler freakingHandler, GameStateManager gsm, int score,
                         GameModuleManager cpanel, GameHandler gameHandler) {
        super(freakingHandler, gsm);

        this.cpanel = cpanel;
        this.gameHandler = gameHandler;
        this.background = Assets.bgGameOver;
        this.score = score;
        generateFont();

        homeButton = Assets.homeButton;
        replayButton = Assets.replayButton;
        homePressed = 0;
        replayPressed = 0;
    }

    private void generateFont() {
        FreeTypeFontGenerator generator =
                new FreeTypeFontGenerator(Gdx.files.internal("fonts/SFPixelate.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter =
                new FreeTypeFontGenerator.FreeTypeFontParameter();

        // Game Over Font
        parameter.size = SCREEN_WIDTH / 10;
        parameter.characters = "GameOver";
        gameOverFont = generator.generateFont(parameter);
        // ScoreFont

        parameter.size = (int) SCREEN_WIDTH / 16;
        parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:";
        scoreFont = generator.generateFont(parameter);
        bestScoreFont = generator.generateFont(parameter);

        scoreFont.setColor(Color.valueOf("#2c3e50"));
        bestScoreFont.setColor(Color.valueOf("#2c3e50"));
        generator.dispose();
    }

    private void handleInput() {
        if (Gdx.input.justTouched()) {
            if (Gdx.input.getX() >= SCREEN_WIDTH * 0.09f
                    && Gdx.input.getX() <= SCREEN_WIDTH * 0.48f
                    && Gdx.input.getY() >= SCREEN_HEIGHT * 0.7f
                    && Gdx.input.getY() <= SCREEN_HEIGHT * 0.815f) {
                homePressed = 1;
                gsm.set(new MenuState(freakingHandler, gsm, cpanel, gameHandler));
            }
            if (Gdx.input.getX() >= SCREEN_WIDTH * 0.52f
                    && Gdx.input.getX() <= SCREEN_WIDTH * 0.91f
                    && Gdx.input.getY() >= SCREEN_HEIGHT * 0.7f
                    && Gdx.input.getY() <= SCREEN_HEIGHT * 0.815f) {
                replayPressed = 1;
                gsm.set(new PlayState(freakingHandler, gsm, cpanel, gameHandler));
            }
        }
    }

    @Override
    public void tick() {
        Gdx.gl.glClearColor(64 / 255f, 64 / 255f, 64 / 255f, 64 / 255f);
        handleInput();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(background, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        scoreFont.draw(batch, "Score: " + score, SCREEN_WIDTH * 0.2f, SCREEN_HEIGHT * 0.525f);
        bestScoreFont.draw(batch, "High Score: " + Assets.highScore, SCREEN_WIDTH * 0.2f, SCREEN_HEIGHT * 0.47f);

        // ----------- BUTTON RENDERER--------------
        batch.draw(homeButton[homePressed], SCREEN_WIDTH * 0.09f, SCREEN_HEIGHT * (1 - 0.815f),
                SCREEN_WIDTH * 0.39f, SCREEN_HEIGHT * 0.115f);
        batch.draw(replayButton[replayPressed], SCREEN_WIDTH * 0.52f, SCREEN_HEIGHT * (1 - 0.815f),
                SCREEN_WIDTH * 0.39f, SCREEN_HEIGHT * 0.115f);
        batch.end();
    }

    @Override
    public void dispose() {
        gameOverFont.dispose();
        scoreFont.dispose();
        bestScoreFont.dispose();
    }

}
