package dev.game.kanji.worlds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dev.game.kanji.entities.GameBoard;
import dev.game.main.GameHandler;
import dev.game.modules.ModuleKanji;

/**
 * Created by Lam Ngo on 11/19/2016.
 */
public class World {

    private ShapeRenderer shape;
    private GameBoard gameBoard;
    private BitmapFont gameOverFont;
    private BitmapFont scoreLabelFont, scoreFont;
    private BitmapFont bestLabelFont, bestScoreFont;
    private BitmapFont titleFont, titleFont2, newGameFont, tryAgainFont;
    private BitmapFont statementFont, saFont;

    public World() {
        this.gameBoard = GameBoard.generate();
        this.shape = new ShapeRenderer();
        generateFont();
    }

    public void generateFont() {
        FreeTypeFontGenerator generator =
                new FreeTypeFontGenerator(Gdx.files.internal("fonts/OpenSans.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter =
                new FreeTypeFontGenerator.FreeTypeFontParameter();

        // Game Over Font
        parameter.size = Gdx.graphics.getWidth() / 7;
        parameter.characters = "Gameover!";
        gameOverFont = generator.generateFont(parameter);

        // Score Label Font
        parameter.size = Gdx.graphics.getWidth() / 25;
        parameter.characters = "SCORE";
        scoreLabelFont = generator.generateFont(parameter);

        // Score Font
        parameter.size = Gdx.graphics.getWidth() / 18;
        parameter.characters = "0123456789";
        scoreFont = generator.generateFont(parameter);

        // Best Label Font
        parameter.size = Gdx.graphics.getWidth() / 25;
        parameter.characters = "BEST";
        bestLabelFont = generator.generateFont(parameter);

        // Best Font
        parameter.size = Gdx.graphics.getWidth() / 18;
        parameter.characters = "0123456789";
        bestScoreFont = generator.generateFont(parameter);

        // Title Font
        parameter.size = Gdx.graphics.getWidth() / 7;
        parameter.characters = "0123456789";
        titleFont = generator.generateFont(parameter);

        // Title Font 2 (Hiragana)
        parameter.size = Gdx.graphics.getWidth() / 17;
        parameter.characters = "Hiragana";
        titleFont2 = generator.generateFont(parameter);

        // NewGame font
        parameter.size = Gdx.graphics.getWidth() / 21;
        parameter.characters = "NewGame";
        newGameFont = generator.generateFont(parameter);

        // Try Again Font
        parameter.size = Gdx.graphics.getWidth() / 21;
        parameter.characters = "TryAgain";
        tryAgainFont = generator.generateFont(parameter);

        // Statement Font
        parameter.size = GameHandler.GAME_WIDTH / 21;
        parameter.characters = "Gettothetile!";
        statementFont = generator.generateFont(parameter);
        generator.dispose();


        // Remain Statement Font
        FreeTypeFontGenerator generator2 =
                new FreeTypeFontGenerator(Gdx.files.internal("fonts/KaoriGelBold.ttf"));
        parameter.size = GameHandler.GAME_WIDTH / 18;
        parameter.characters = "さ";
        saFont = generator2.generateFont(parameter);
        generator2.dispose();
    }

    public void render(SpriteBatch batch) {
        //-------------SHAPE RENDERER---------------
        // render background (shape)
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(Color.valueOf("#faf8ef"));
        shape.rect(0, 0, GameHandler.GAME_WIDTH, GameHandler.GAME_HEIGHT);


        // render score box (shape)
        shape.setColor(Color.valueOf("#bbada0"));
        shape.rect(GameHandler.GAME_WIDTH * 0.52f, GameHandler.GAME_HEIGHT * 0.81f,
                    GameHandler.GAME_WIDTH * 0.2f, GameHandler.GAME_HEIGHT * 0.11f);

        // render best score box (shape)
        shape.setColor(Color.valueOf("#bbada0"));
        shape.rect(GameHandler.GAME_WIDTH * 0.76f, GameHandler.GAME_HEIGHT * 0.81f,
                GameHandler.GAME_WIDTH * 0.2f, GameHandler.GAME_HEIGHT * 0.11f);

        // render New Game box (shape)
        shape.setColor(Color.valueOf("#8f7a66"));
        shape.rect(GameHandler.GAME_WIDTH * 0.66f, GameHandler.GAME_HEIGHT * 0.71f,
                    GameHandler.GAME_WIDTH * 0.30f, GameHandler.GAME_HEIGHT * 0.07f);
        shape.end();

        // render Game Board
        gameBoard.render(batch);


        // Transparent white when game over
        Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND);
        shape.setProjectionMatrix(ModuleKanji.camera.combined);
        if (gameBoard.isDead()) {
            shape.begin(ShapeRenderer.ShapeType.Filled);
            shape.setColor(33, 150, 243, 0.7f);
            shape.rect(0, 0, GameBoard.BOARD_WIDTH, GameBoard.BOARD_HEIGHT);
            shape.end();

            // Try Again box (shape)
            shape.begin(ShapeRenderer.ShapeType.Filled);
            shape.setColor(Color.valueOf("#8f7a66"));
            shape.rect(GameHandler.GAME_WIDTH * 0.33f, GameHandler.GAME_HEIGHT * 0.23f,
                            GameHandler.GAME_WIDTH * 0.3f, GameHandler.GAME_HEIGHT * 0.07f);
            shape.end();


            // Game Over font (text)
            batch.begin();
            gameOverFont.setColor(Color.valueOf("#424242"));
            tryAgainFont.setColor(Color.valueOf("#faf8ef"));
            gameOverFont.draw(batch, "Game over!", GameHandler.GAME_WIDTH * 0.1f,
                    GameHandler.GAME_HEIGHT * 0.39f);
            tryAgainFont.draw(batch, "Try Again", GameHandler.GAME_WIDTH * 0.37f,
                    GameHandler.GAME_HEIGHT * 0.28f);
            batch.end();
        }

        //-------------TEXT RENDERER---------------
        // Score Box font (text)
        batch.begin();
        scoreLabelFont.setColor(Color.valueOf("#f0e8e1"));
        scoreFont.setColor(Color.valueOf("#faf8ef"));
        bestLabelFont.setColor(Color.valueOf("#f0e8e1"));
        bestScoreFont.setColor(Color.valueOf("#faf8ef"));
        titleFont.setColor(Color.valueOf("#776e65"));
        titleFont2.setColor(Color.valueOf("#776e65"));
        newGameFont.setColor(Color.valueOf("#faf8ef"));
        statementFont.setColor(Color.valueOf("#776e65"));
        saFont.setColor(Color.valueOf("#776e65"));

        scoreLabelFont.draw(batch, "SCORE", GameHandler.GAME_WIDTH * 0.55f,
                                            GameHandler.GAME_HEIGHT * 0.9f);
        scoreFont.draw(batch, "123", GameHandler.GAME_WIDTH * 0.56f,
                                     GameHandler.GAME_HEIGHT * 0.86f);
        bestLabelFont.draw(batch, "BEST", GameHandler.GAME_WIDTH * 0.81f,
                                        GameHandler.GAME_HEIGHT * 0.9f);
        bestScoreFont.draw(batch, "456", GameHandler.GAME_WIDTH * 0.8f,
                                    GameHandler.GAME_HEIGHT * 0.86f);
        titleFont.draw(batch, "2048", GameHandler.GAME_WIDTH * 0.05f,
                                      GameHandler.GAME_HEIGHT * 0.92f);
        titleFont2.draw(batch, "Hiragana", GameHandler.GAME_WIDTH * 0.07f,
                                        GameHandler.GAME_HEIGHT * 0.84f);
        newGameFont.draw(batch, "New Game", GameHandler.GAME_WIDTH * 0.675f,
                                        GameHandler.GAME_HEIGHT * 0.756f);
        statementFont.draw(batch, "Get to the      tile!", GameHandler.GAME_WIDTH * 0.05f,
                                        GameHandler.GAME_HEIGHT * 0.75f);
        saFont.draw(batch, "さ", GameHandler.GAME_WIDTH * 0.3f,
                                    GameHandler.GAME_HEIGHT * 0.757f);
        batch.end();
    }

    private void handleInput() {
        // New Game listener
        if (Gdx.input.justTouched()) {
            if (Gdx.input.getX() >= GameHandler.GAME_WIDTH * 0.66f
                    && Gdx.input.getX() <= GameHandler.GAME_WIDTH * (0.66f + 0.30f)
                    && Gdx.input.getY() >= GameHandler.GAME_HEIGHT * (1 - 0.71f - 0.07)
                    && Gdx.input.getY() <= GameHandler.GAME_HEIGHT * (1 - 0.71f)) {
                gameBoard.dispose();
                gameBoard = GameBoard.generate();
            }

            if (gameBoard.isDead()) {
                if (Gdx.input.getX() >= GameHandler.GAME_WIDTH * 0.33f
                        && Gdx.input.getX() <= GameHandler.GAME_WIDTH * (0.33f + 0.3f)
                        && Gdx.input.getY() >= GameHandler.GAME_HEIGHT * (1 - 0.23f - 0.07f)
                        && Gdx.input.getY() <= GameHandler.GAME_HEIGHT * (1 - 0.23f)) {
                    gameBoard.dispose();
                    gameBoard = GameBoard.generate();
                }
            }
        }
    }

    public void tick() {
        gameBoard.update();
        handleInput();
    }

    public void dispose() {
        gameBoard.dispose();
        shape.dispose();
        gameOverFont.dispose();
        scoreLabelFont.dispose();
        scoreFont.dispose();
        titleFont.dispose();
        titleFont2.dispose();
        newGameFont.dispose();
        bestLabelFont.dispose();
        bestScoreFont.dispose();
        statementFont.dispose();
        saFont.dispose();
        tryAgainFont.dispose();
    }
}
