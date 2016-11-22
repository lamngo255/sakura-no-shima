package dev.game.kanji.worlds;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dev.game.kanji.entities.Assets;
import dev.game.kanji.entities.GameBoard;
import dev.game.main.GameHandler;
import dev.game.modules.GameModuleManager;
import dev.game.modules.MainGameModule;
import dev.game.modules.ModuleKanji;

/**
 * Created by Lam Ngo on 11/19/2016.
 */
public class World {


    private ShapeRenderer shape;
    private GameBoard gameBoard;
    private BitmapFont gameOverFont, okWinFont;
    private BitmapFont scoreLabelFont, scoreFont;
    private BitmapFont bestLabelFont, bestScoreFont;
    private BitmapFont titleFont, titleFont2, newGameFont, tryAgainFont;
    private BitmapFont statementFont, saFont;

    private GameModuleManager cpanel;
    private GameHandler gameHandler;
    private Texture homeButton, replayButton;

    private static int score;

    public World(GameModuleManager cpanel, GameHandler gameHandler) {
        this.cpanel = cpanel;
        this.gameHandler = gameHandler;
        this.gameBoard = GameBoard.generate();
        this.shape = new ShapeRenderer();
        generateFont();
        score = 0;
        this.homeButton = new Texture("button/home_kanji.png");
        this.replayButton = new Texture("button/replay_kanji.png");
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

        // Ok Win Font
        parameter.characters = "Ok,YouWin!";
        okWinFont = generator.generateFont(parameter);

        // Score Label Font
        parameter.size = Gdx.graphics.getWidth() / 30;
        parameter.characters = "SCORE";
        scoreLabelFont = generator.generateFont(parameter);

        // Score Font
        parameter.size = Gdx.graphics.getWidth() / 25;
        parameter.characters = "0123456789";
        scoreFont = generator.generateFont(parameter);

        // Best Label Font
        parameter.size = Gdx.graphics.getWidth() / 30;
        parameter.characters = "BEST";
        bestLabelFont = generator.generateFont(parameter);

        // Best Font
        parameter.size = Gdx.graphics.getWidth() / 25;
        parameter.characters = "0123456789";
        bestScoreFont = generator.generateFont(parameter);

        // Title Font
        FreeTypeFontGenerator generator2 =
                new FreeTypeFontGenerator(Gdx.files.internal("fonts/Mamelon.otf"));
        parameter.size = Gdx.graphics.getWidth() / 5;
        parameter.characters = "日本";
        titleFont = generator2.generateFont(parameter);

        // Title Font 2 (Hiragana)
        parameter.size = Gdx.graphics.getWidth() / 17;
        parameter.characters = "KanjiNumber";
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
        parameter.size = GameHandler.GAME_WIDTH / 15;
        parameter.characters = "Getto百thetile!";
        statementFont = generator2.generateFont(parameter);
        generator.dispose();
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
            shape.rect(GameHandler.GAME_WIDTH * 0.33f, GameHandler.GAME_HEIGHT * 0.20f,
                    GameHandler.GAME_WIDTH * 0.3f, GameHandler.GAME_HEIGHT * 0.07f);
            shape.end();


            // Game Over font (text)
            batch.begin();
            gameOverFont.setColor(Color.valueOf("#424242"));
            tryAgainFont.setColor(Color.valueOf("#faf8ef"));
            gameOverFont.draw(batch, "Game over!", GameHandler.GAME_WIDTH * 0.1f,
                    GameHandler.GAME_HEIGHT * 0.39f);
            tryAgainFont.draw(batch, "Try Again", GameHandler.GAME_WIDTH * 0.37f,
                    GameHandler.GAME_HEIGHT * 0.24f);
            batch.end();
        }

        if (gameBoard.isWon()) {
            shape.begin(ShapeRenderer.ShapeType.Filled);
            shape.setColor(33, 150, 243, 0.7f);
            shape.rect(0, 0, GameBoard.BOARD_WIDTH, GameBoard.BOARD_HEIGHT);
            shape.end();

            // Try Again box (shape)
            shape.begin(ShapeRenderer.ShapeType.Filled);
            shape.setColor(Color.valueOf("#8f7a66"));
            shape.rect(GameHandler.GAME_WIDTH * 0.33f, GameHandler.GAME_HEIGHT * 0.20f,
                    GameHandler.GAME_WIDTH * 0.3f, GameHandler.GAME_HEIGHT * 0.07f);
            shape.end();


            // Ok You Win font (text)
            batch.begin();
            okWinFont.setColor(Color.valueOf("#424242"));
            tryAgainFont.setColor(Color.valueOf("#faf8ef"));
            okWinFont.draw(batch, "Ok, You Win!", GameHandler.GAME_WIDTH * 0.05f,
                    GameHandler.GAME_HEIGHT * 0.39f);
            tryAgainFont.draw(batch, "Try Again", GameHandler.GAME_WIDTH * 0.37f,
                    GameHandler.GAME_HEIGHT * 0.24f);
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
        scoreLabelFont.draw(batch, "SCORE", GameHandler.GAME_WIDTH * 0.56f,
                GameHandler.GAME_HEIGHT * 0.9f);

        //draw score
        float scoreFontX = Assets.getCenterAlignmentPositionX(GameHandler.GAME_WIDTH * 0.2f, GameHandler.GAME_WIDTH * 0.52f
                , String.format("%s", score).length(), scoreFont.getSpaceWidth() * 3);
        scoreFont.draw(batch, String.format("%s", score), scoreFontX,
                GameHandler.GAME_HEIGHT * 0.86f);

        bestLabelFont.draw(batch, "BEST", GameHandler.GAME_WIDTH * 0.81f,
                GameHandler.GAME_HEIGHT * 0.9f);

        //draw highscore

//        bestScoreFont.draw(batch, String.format("%s", Assets.bestScore), GameHandler.GAME_WIDTH * 0.78f,
//                                    GameHandler.GAME_HEIGHT * 0.86f);
        float highScoreFontX = Assets.getCenterAlignmentPositionX(GameHandler.GAME_WIDTH * 0.2f, GameHandler.GAME_WIDTH * 0.76f,
                String.format("%s", Assets.bestScore).length(), scoreFont.getSpaceWidth() * 3);
        bestScoreFont.draw(batch, String.format("%s", Assets.bestScore), highScoreFontX, GameHandler.GAME_HEIGHT * 0.86f);
        titleFont.draw(batch, "日本", GameHandler.GAME_WIDTH * 0.049f,
                GameHandler.GAME_HEIGHT * 0.94f);
        titleFont2.draw(batch, "Kanji Number", GameHandler.GAME_WIDTH * 0.05f,
                GameHandler.GAME_HEIGHT * 0.84f);
        newGameFont.draw(batch, "New Game", GameHandler.GAME_WIDTH * 0.675f,
                GameHandler.GAME_HEIGHT * 0.756f);
        statementFont.draw(batch, "Get to the 百 tile!", GameHandler.GAME_WIDTH * 0.05f,
                GameHandler.GAME_HEIGHT * 0.75f);

        batch.end();

        batch.begin();
        batch.draw(homeButton, GameHandler.GAME_WIDTH * 0.69f, GameHandler.GAME_HEIGHT * 0.71f,
                GameHandler.GAME_WIDTH * 0.12f, GameHandler.GAME_HEIGHT * 0.07f);
        batch.draw(replayButton, GameHandler.GAME_WIDTH * 0.84f, GameHandler.GAME_HEIGHT * 0.71f,
                GameHandler.GAME_WIDTH * 0.12f, GameHandler.GAME_HEIGHT * 0.07f);
        batch.end();
    }

    private void handleInput() {
        // New Game listener
        if (Gdx.input.justTouched()) {
            // new_game_button
            if (Gdx.input.getX() >= GameHandler.GAME_WIDTH * 0.84f
                    && Gdx.input.getX() <= GameHandler.GAME_WIDTH * (0.84f + 0.12f)
                    && Gdx.input.getY() >= GameHandler.GAME_HEIGHT * (1 - 0.71f - 0.07)
                    && Gdx.input.getY() <= GameHandler.GAME_HEIGHT * (1 - 0.71f)) {
                gameBoard.dispose();
                gameBoard = GameBoard.generate();
            }
            //todo home_button
            if (Gdx.input.getX() >= GameHandler.GAME_WIDTH * 0.69f
                    && Gdx.input.getX() <= GameHandler.GAME_WIDTH * (0.69f + 0.12f)
                    && Gdx.input.getY() >= GameHandler.GAME_HEIGHT * (1 - 0.71f - 0.07f)
                    && Gdx.input.getY() <= GameHandler.GAME_HEIGHT * (1 - 0.71f)) {
                cpanel.set(new MainGameModule(gameHandler, cpanel));
            }

            if (gameBoard.isDead() || gameBoard.isWon()) {
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

    public static int getScore() {
        return score;
    }

    public static void addScore(int amount) {
        score += amount;
    }

    public static void resetScore() {
        score = 0;
    }

    public void dispose() {
        gameBoard.dispose();
        shape.dispose();
        gameOverFont.dispose();
        okWinFont.dispose();
        scoreLabelFont.dispose();
        scoreFont.dispose();
        titleFont.dispose();
        titleFont2.dispose();
        newGameFont.dispose();
        bestLabelFont.dispose();
        bestScoreFont.dispose();
        statementFont.dispose();
        tryAgainFont.dispose();
        homeButton.dispose();
        replayButton.dispose();
    }
}
