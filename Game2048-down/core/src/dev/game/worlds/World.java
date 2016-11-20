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
                new FreeTypeFontGenerator(Gdx.files.internal("KaoriGelBold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter =
                new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = Gdx.graphics.getWidth() / 8;
        parameter.characters = "Gameover!";
        gameOverFont = generator.generateFont(parameter);
        generator.dispose();
    }

    public void render(SpriteBatch batch) {
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(Color.LIGHT_GRAY);
        shape.rect(0, 0, handler.getWidth(), handler.getHeight());
        shape.end();
        gameBoard.render(batch);

        Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND);
        shape.setProjectionMatrix(Game2048.camera.combined);
        if (gameBoard.isDead()) {
            shape.begin(ShapeRenderer.ShapeType.Filled);
            shape.setColor(33, 150, 243, 0.4f);
            shape.rect(0, 0, handler.getWidth(), handler.getHeight());
            shape.end();

            batch.begin();
            gameOverFont.draw(batch, "Game over!", handler.getWidth() / 2, handler.getHeight() / 2);
            batch.end();
        }
    }

    public void update() {
        gameBoard.update();
    }

    public void dispose() {
        gameBoard.dispose();
        shape.dispose();
    }
}
