package dev.game.worlds;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dev.game.entities.GameBoard;
import dev.game.main.Game2048;
import dev.game.main.Handler;

import java.awt.image.BufferedImage;

/**
 * Created by Lam Ngo on 11/19/2016.
 */
public class World {
//    public static final Font main = new Font("Bebas Neue Regular", Font.PLAIN, 28);

    private ShapeRenderer shape;
    private GameBoard gameBoard;
    private Handler handler;

    public World(Handler handler) {
        this.handler = handler;
        this.gameBoard =
                new GameBoard((handler.getWidth() / 2 - GameBoard.BOARD_WIDTH / 2),
                              (handler.getHeight() - GameBoard.BOARD_HEIGHT - 10));
        this.shape = new ShapeRenderer();
    }

    public void render(SpriteBatch batch) {
        shape.setProjectionMatrix(Game2048.camera.combined);
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(Color.LIGHT_GRAY);
        shape.rect(0, 0, handler.getWidth(), handler.getHeight());
        shape.end();

        gameBoard.render(batch);
    }

    public void update() {
        gameBoard.update();
    }
}
