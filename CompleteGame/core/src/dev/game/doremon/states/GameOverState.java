package dev.game.doremon.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dev.game.doremon.gfx.Assets;
import dev.game.doremon.main.DoremonHandler;
import dev.game.modules.DoremonModule;
import dev.game.main.GameHandler;
import dev.game.modules.GameModuleManager;


public class GameOverState extends State {
    TextureRegion replayButton;
    TextureRegion mainmenuButton;
    private int score;
    private BitmapFont gameOver, scoreFont, bestScoreFont;
    private TextureRegion doremon, background;
    private ShapeRenderer hub;
    String myText;

    public GameOverState(DoremonHandler doremonHandler, GameStateManager gsm, int score,
                         GameModuleManager cpanel, GameHandler gameHandler) {
        super(doremonHandler, gsm);

        this.cpanel = cpanel;
        this.gameHandler = gameHandler;
        this.doremon = Assets.player_jump[0];
        this.background = Assets.bgGameOver;
        initFont();
        replayButton = Assets.playButton[3];
        mainmenuButton = Assets.playButton[4];
        DoremonModule.bestScore = Math.max(DoremonModule.bestScore, score);
        this.score = score;
        this.hub = new ShapeRenderer();
    }

    public void initFont() {
        FreeTypeFontGenerator generator =
                new FreeTypeFontGenerator(Gdx.files.internal("gloriahallelujah.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 80;
        parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:";
        parameter.flip = true;
        gameOver = generator.generateFont(parameter);

        parameter.size = (int) (DoremonHandler.GAME_HEIGHT * 0.035f);
        scoreFont = generator.generateFont(parameter);
        bestScoreFont = generator.generateFont(parameter);


        gameOver.setColor(Color.BROWN);
        scoreFont.setColor(Color.BLACK);
        bestScoreFont.setColor(Color.BLACK);
        generator.dispose();
    }

    public void handleInput() {
        if (Gdx.input.justTouched()) {
            if (Gdx.input.getX() >= DoremonHandler.GAME_WIDTH * 0.27f &&
                    Gdx.input.getX() <= DoremonHandler.GAME_WIDTH * 0.69f &&
                    Gdx.input.getY() >= DoremonHandler.GAME_HEIGHT * 0.5f &&
                    Gdx.input.getY() <= DoremonHandler.GAME_HEIGHT * 0.58f) {
                gsm.set(new GameState(doremonHandler, gsm, cpanel, gameHandler));
            } else if (Gdx.input.getX() >= DoremonHandler.GAME_WIDTH * 0.27f &&
                    Gdx.input.getX() <= DoremonHandler.GAME_WIDTH * 0.69f &&
                    Gdx.input.getY() >= DoremonHandler.GAME_HEIGHT * 0.64f &&
                    Gdx.input.getY() <= DoremonHandler.GAME_HEIGHT * 0.69f
                    ) {
                gsm.set(new MenuState(doremonHandler, gsm, cpanel, gameHandler));
            }
        }
    }

    @Override
    public void tick() {
        handleInput();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(background, 0, 0, DoremonHandler.GAME_WIDTH, DoremonHandler.GAME_HEIGHT);

        scoreFont.draw(batch, "Score: " + score, (float) DoremonHandler.GAME_WIDTH * 0.23f, DoremonHandler.GAME_HEIGHT * 0.3125f);
        bestScoreFont.draw(batch, "Best score: " + DoremonModule.bestScore, (float) DoremonHandler.GAME_WIDTH * 0.23f, DoremonHandler.GAME_HEIGHT * 0.37f);
        batch.draw(replayButton, DoremonHandler.GAME_WIDTH * 0.27f, DoremonHandler.GAME_HEIGHT * 0.5f, DoremonHandler.GAME_WIDTH * 0.47f, DoremonHandler.GAME_HEIGHT * 0.09f);
        batch.draw(mainmenuButton, DoremonHandler.GAME_WIDTH * 0.27f, DoremonHandler.GAME_HEIGHT * 0.63f, DoremonHandler.GAME_WIDTH * 0.47f, DoremonHandler.GAME_HEIGHT * 0.09f);

        batch.end();

        Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND);
        hub.setProjectionMatrix(DoremonModule.camera.combined);
        hub.begin(ShapeRenderer.ShapeType.Filled);
        hub.setColor(33, 150, 243, 0.3f);
        hub.rect(0, 0, DoremonHandler.GAME_WIDTH, 320);
        hub.end();
    }

    @Override
    public void dispose() {
        gameOver.dispose();
        scoreFont.dispose();
        bestScoreFont.dispose();
    }
}
