package dev.game.worlds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dev.game.entities.GameBoard;
import dev.game.main.Game2048;
import dev.game.main.Handler;

/**
 * Created by Lam Ngo on 11/19/2016.
 */
public class World {

    private ShapeRenderer shape;
    private GameBoard gameBoard;
    private Handler handler;
    private BitmapFont gameOverFont;
    private BitmapFont scoreLabelFont, scoreFont;
    private BitmapFont bestLabelFont, bestScoreFont;
    private BitmapFont titleFont, titleFont2, newGameFont;
    private BitmapFont statementFont, saFont;

    public World(Handler handler) {
        this.handler = handler;
        this.gameBoard =
                new GameBoard((handler.getWidth() / 2 - GameBoard.BOARD_WIDTH / 2),
                        (handler.getHeight() - GameBoard.BOARD_HEIGHT - 10));
        this.shape = new ShapeRenderer();
        generateFont();
    }

    public void generateFont() {
        FreeTypeFontGenerator generator =
                new FreeTypeFontGenerator(Gdx.files.internal("OpenSans.ttf"));
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


        // Statement Font
        parameter.size = handler.getWidth() / 21;
        parameter.characters = "Gettothetile!";
        statementFont = generator.generateFont(parameter);
        generator.dispose();


        // Remain Statement Font
        FreeTypeFontGenerator generator2 =
                new FreeTypeFontGenerator(Gdx.files.internal("KaoriGelBold.ttf"));
        parameter.size = handler.getWidth() / 20;
        parameter.characters = "さ";
        saFont = generator2.generateFont(parameter);
        generator2.dispose();
    }

    public void render(SpriteBatch batch) {
        //-------------SHAPE RENDERER---------------
        // render background (shape)
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(Color.valueOf("#faf8ef"));
        shape.rect(0, 0, handler.getWidth(), handler.getHeight());


        // render score box (shape)
        shape.setColor(Color.valueOf("#bbada0"));
        shape.rect(handler.getWidth() * 0.52f, handler.getHeight() * 0.81f,
                    handler.getWidth() * 0.2f, handler.getHeight() * 0.11f);

        // render best score box (shape)
        shape.setColor(Color.valueOf("#bbada0"));
        shape.rect(handler.getWidth() * 0.76f, handler.getHeight() * 0.81f,
                handler.getWidth() * 0.2f, handler.getHeight() * 0.11f);

        // render New Game box (shape)
        shape.setColor(Color.valueOf("#8f7a66"));
        shape.rect(handler.getWidth() * 0.66f, handler.getHeight() * 0.71f,
                    handler.getWidth() * 0.30f, handler.getHeight() * 0.07f);
        shape.end();

        // render Game Board
        gameBoard.render(batch);


        // Transparent white when game over
        Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND);
        shape.setProjectionMatrix(Game2048.camera.combined);
        if (gameBoard.isDead()) {
            shape.begin(ShapeRenderer.ShapeType.Filled);
            shape.setColor(33, 150, 243, 0.7f);
            shape.rect(0, 0, GameBoard.BOARD_WIDTH, GameBoard.BOARD_HEIGHT);
            shape.end();

            // Game Over font (text)
            batch.begin();
            gameOverFont.setColor(Color.valueOf("#424242"));
            gameOverFont.draw(batch, "Game over!", handler.getWidth() * 0.1f,
                                                    handler.getHeight() * 0.32f);
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

        scoreLabelFont.draw(batch, "SCORE", handler.getWidth() * 0.55f,
                                            handler.getHeight() * 0.9f);
        scoreFont.draw(batch, "123", handler.getWidth() * 0.56f,
                                     handler.getHeight() * 0.86f);
        bestLabelFont.draw(batch, "BEST", handler.getWidth() * 0.81f,
                                        handler.getHeight() * 0.9f);
        bestScoreFont.draw(batch, "456", handler.getWidth() * 0.8f,
                                    handler.getHeight() * 0.86f);
        titleFont.draw(batch, "2048", handler.getWidth() * 0.05f,
                                      handler.getHeight() * 0.92f);
        titleFont2.draw(batch, "Hiragana", handler.getWidth() * 0.07f,
                                        handler.getHeight() * 0.84f);
        newGameFont.draw(batch, "New Game", handler.getWidth() * 0.675f,
                                        handler.getHeight() * 0.756f);
        statementFont.draw(batch, "Get to the     tile!", handler.getWidth() * 0.05f,
                                        handler.getHeight() * 0.75f);
        saFont.draw(batch, "さ", handler.getWidth() * 0.3f,
                                    handler.getHeight() * 0.752f);
        batch.end();

    }

    public void update() {
        gameBoard.update();
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
    }
}
