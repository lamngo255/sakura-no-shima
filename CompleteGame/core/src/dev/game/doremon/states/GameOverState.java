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
import dev.game.main.CompleteGame;
import dev.game.modules.ModuleDoremon;
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

//        gameHandler.pauseBackgroundMusic();
        this.cpanel = cpanel;
        this.gameHandler = gameHandler;
        this.doremon = Assets.player_jump[0];
        this.background = Assets.bgGameOver;

        initFont();
        replayButton = Assets.playButton[3];
        mainmenuButton = Assets.playButton[4];
        ModuleDoremon.bestScore = Math.max(ModuleDoremon.bestScore, score);
        this.score = score;
        this.hub = new ShapeRenderer();
    }

    public void initFont() {
        FreeTypeFontGenerator generator =
                new FreeTypeFontGenerator(Gdx.files.internal("gloriahallelujah.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int) (CompleteGame.WORLD_WIDTH_TEST * 0.074f);
        parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:";
        parameter.flip = true;
        gameOver = generator.generateFont(parameter);

        parameter.size = (int) (CompleteGame.WORLD_WIDTH_TEST * 0.065f);
        scoreFont = generator.generateFont(parameter);
        bestScoreFont = generator.generateFont(parameter);


        gameOver.setColor(Color.BROWN);
        scoreFont.setColor(Color.BLACK);
        bestScoreFont.setColor(Color.BLACK);
        generator.dispose();
    }

    public void handleInput() {
        if (Gdx.input.justTouched()) {

            //debug
//            if (Gdx.input.getX() >= GameHandler.GAME_WIDTH * 0.27f &&
//                    Gdx.input.getX() <= GameHandler.GAME_WIDTH * 0.69f &&
//                    Gdx.input.getY() >= GameHandler.GAME_HEIGHT * 0.5f &&
//                    Gdx.input.getY() <= GameHandler.GAME_HEIGHT * 0.58f)
//
            if (ModuleDoremon.checkIfButtonPressed(CompleteGame.WORLD_WIDTH_TEST *  0.27f,
                    CompleteGame.WORLD_WIDTH_TEST* 0.69f,
                    CompleteGame.WORLD_HEIGHT_TEST * 0.5f,
                    CompleteGame.WORLD_HEIGHT_TEST *  0.58f
            ))
            {
                gsm.set(new GameState(doremonHandler, gsm, cpanel, gameHandler));
            }
            else
//                if (Gdx.input.getX() >= GameHandler.GAME_WIDTH * 0.27f &&
//                    Gdx.input.getX() <= GameHandler.GAME_WIDTH * 0.69f &&
//                    Gdx.input.getY() >= GameHandler.GAME_HEIGHT * 0.64f &&
//                    Gdx.input.getY() <= GameHandler.GAME_HEIGHT * 0.69f
//                    )
                if (ModuleDoremon.checkIfButtonPressed(CompleteGame.WORLD_WIDTH_TEST *  0.27f,
                        CompleteGame.WORLD_WIDTH_TEST* 0.69f,
                        CompleteGame.WORLD_HEIGHT_TEST * 0.64f,
                        CompleteGame.WORLD_HEIGHT_TEST *  0.69f
                ))

            {
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
        batch.draw(background, 0, 0, CompleteGame.WORLD_WIDTH_TEST,CompleteGame.WORLD_HEIGHT_TEST);

        scoreFont.draw(batch, "Score: " + score, (float) CompleteGame.WORLD_WIDTH_TEST * 0.2f, CompleteGame.WORLD_HEIGHT_TEST * 0.3125f);
        bestScoreFont.draw(batch, "Best score: " + ModuleDoremon.bestScore, (float) CompleteGame.WORLD_WIDTH_TEST * 0.2f, CompleteGame.WORLD_HEIGHT_TEST * 0.37f);
        batch.draw(replayButton, CompleteGame.WORLD_WIDTH_TEST * 0.27f, CompleteGame.WORLD_HEIGHT_TEST * 0.5f, CompleteGame.WORLD_WIDTH_TEST * 0.47f, CompleteGame.WORLD_HEIGHT_TEST * 0.09f);
        batch.draw(mainmenuButton, CompleteGame.WORLD_WIDTH_TEST * 0.27f, CompleteGame.WORLD_HEIGHT_TEST * 0.63f, CompleteGame.WORLD_WIDTH_TEST * 0.47f, CompleteGame.WORLD_HEIGHT_TEST * 0.09f);

        batch.end();

        Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND);
        hub.setProjectionMatrix(ModuleDoremon.camera.combined);
        hub.begin(ShapeRenderer.ShapeType.Filled);
        hub.setColor(33, 150, 243, 0.3f);
        hub.rect(0, 0,  CompleteGame.WORLD_WIDTH_TEST, 320);
        hub.end();
    }

    @Override
    public void dispose() {
        gameOver.dispose();
        scoreFont.dispose();
        bestScoreFont.dispose();
    }
}
