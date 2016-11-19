package dev.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import dev.game.input.DirectionGestureDetector;
import dev.game.main.Game2048;
import dev.game.utils.Direction;

import java.util.Random;

/**
 * Created by Lam Ngo on 11/19/2016.
 */
public class GameBoard {
    public static final int ROWS = 4;
    public static final int COLS = 4;

    private final int startingTiles = 2;
    private Tile[][] board;
    private boolean dead;
    private boolean won;
    private ShapeRenderer gameBoard;
    private ShapeRenderer finalBoard;
    private int x;
    private int y;
    private static int SPACING = 20;
    public static int BOARD_WIDTH = (COLS + 1) * SPACING + COLS * Tile.TILE_WIDTH;
    public static int BOARD_HEIGHT = (ROWS + 1) * SPACING + ROWS * Tile.TILE_HEIGHT;
    public static boolean hasStarted;

    public GameBoard(int x, int y) {
        this.x = x;
        this.y = y;
        board = new Tile[ROWS][COLS];
        gameBoard = new ShapeRenderer();
        finalBoard = new ShapeRenderer();
        start();
        checkSwipe();
    }

    public void checkSwipe() {
        Gdx.input.setInputProcessor(new DirectionGestureDetector(new DirectionGestureDetector.DirectionListener() {
            @Override
            public void onUp() {
                Gdx.app.log("Dir", "Up");
                moveTiles(Direction.UP);
                if (!hasStarted)
                    hasStarted = true;
            }

            @Override
            public void onRight() {
                Gdx.app.log("Dir", "Right");
                moveTiles(Direction.RIGHT);
                if (!hasStarted)
                    hasStarted = true;
            }

            @Override
            public void onLeft() {
                Gdx.app.log("Dir", "Left");
                moveTiles(Direction.LEFT);
                if (!hasStarted)
                    hasStarted = true;
            }

            @Override
            public void onDown() {
                Gdx.app.log("Dir", "Down");
                moveTiles(Direction.DOWN);
                if (!hasStarted)
                    hasStarted = true;
            }
        }));
    }

    public void render(SpriteBatch batch) {
        batch.begin();
        batch.end();

        gameBoard.setProjectionMatrix(Game2048.camera.combined);
        gameBoard.begin(ShapeRenderer.ShapeType.Filled);
        gameBoard.setColor(Color.DARK_GRAY);
        gameBoard.rect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                int x = (SPACING + SPACING * col + Tile.TILE_WIDTH * col);
                int y = (SPACING + SPACING * row + Tile.TILE_WIDTH * row);
                gameBoard.setColor(Color.LIGHT_GRAY);
                gameBoard.rect(x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
            }
        }
        gameBoard.end();

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                Tile current = board[row][col];
                if (current == null) continue;
                current.render(batch);
            }
        }
    }

    public void start() {
        for (int i = 0; i < startingTiles; i++) {
            spawnRandom();
        }
    }

    private void spawnRandom() {
        Random random = new Random();
        boolean isValid = false;
        while (!isValid) {
            int location = random.nextInt(ROWS * COLS);
            int row = location / ROWS;
            int col = location % COLS;
            Tile current = board[row][col];
            if (current == null) {
                int value = random.nextInt(10) < 9 ? 2 : 4;
                Tile tile = new Tile(value, getTileX(col), getTileY(row));
                board[row][col] = tile;
                isValid = true;
            }
        }
    }

    public int getTileX(int col) {
        return SPACING + col * Tile.TILE_WIDTH + col * SPACING;
    }

    public int getTileY(int row) {
        return SPACING + row * Tile.TILE_HEIGHT + row * SPACING;
    }

    public void update() {
        checkKey();
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                Tile current = board[row][col];
                if (current == null) continue;
                current.update();
                resetPosition(current, row, col);
                if (current.getValue() == 2048) {
                    won = true;
                }
            }
        }
    }

    private void resetPosition(Tile current, int row, int col) {
        if (current == null) return;

        int x = getTileX(col);
        int y = getTileY(row);


        int disX = current.getX() - x;
        int disY = current.getY() - y;

        if (Math.abs(disX) < Tile.SLIDE_SPEED) {
            current.setX(current.getX() - disX);
        }

        if (Math.abs(disY) < Tile.SLIDE_SPEED) {
            current.setY(current.getY() - disY);
        }

        if (disX < 0) {
            current.setX(current.getX() + Tile.SLIDE_SPEED);
        }

        if (disY < 0) {
            current.setY(current.getY() + Tile.SLIDE_SPEED);
        }

        if (disX > 0) {
            current.setX(current.getX() - Tile.SLIDE_SPEED);
        }

        if (disY > 0) {
            current.setY(current.getY() - Tile.SLIDE_SPEED);
        }
    }

    private boolean move(int row, int col, int horizontalDirection, int verticalDirection, Direction dir) {
        boolean canMove = false;

        Tile current = board[row][col];
        if (current == null) return false;
        boolean move = true;
        int newCol = col;
        int newRow = row;
        while (move) {
            newCol += horizontalDirection;
            newRow += verticalDirection;
            if (checkOutOfBound(dir, newRow, newCol)) break;
            if (board[newRow][newCol] == null) {
                board[newRow][newCol] = current;
                board[newRow - verticalDirection][newCol - horizontalDirection] = null;
                board[newRow][newCol].setSlideTo(new Point(newRow, newCol));
                canMove = true;
            } else if (board[newRow][newCol].getValue() == current.getValue()
                    && board[newRow][newCol].canCombine()) {
                board[newRow][newCol].setCanCombine(false);
                board[newRow][newCol].setValue(board[newRow][newCol].getValue() * 2);
                canMove = true;
                board[newRow - verticalDirection][newCol - horizontalDirection] = null;
                board[newRow][newCol].setSlideTo(new Point(newRow, newCol));
                //add to score
            } else {
                move = false;
            }
        }
        return canMove;
    }

    private void moveTiles(Direction dir) {
        boolean canMove = false;
        int horizontalDirection = 0;
        int verticalDirection = 0;
        if (dir == Direction.LEFT) {
            horizontalDirection = -1;
            for (int row = 0; row < ROWS; row++) {
                for (int col = 0; col < COLS; col++) {
                    if (!canMove) {
                        canMove = move(row, col, horizontalDirection, verticalDirection, dir);
                    } else {
                        move(row, col, horizontalDirection, verticalDirection, dir);
                    }
                }
            }
            Gdx.app.log("Message", "Move left");
        } else if (dir == Direction.RIGHT) {
            horizontalDirection = 1;
            for (int row = 0; row < ROWS; row++) {
                for (int col = COLS - 1; col >= 0; col--) {
                    if (!canMove) {
                        canMove = move(row, col, horizontalDirection, verticalDirection, dir);
                    } else {
                        move(row, col, horizontalDirection, verticalDirection, dir);
                    }
                }
            }
        } else if (dir == Direction.UP) {
            verticalDirection = -1;
            for (int row = 0; row < ROWS; row++) {
                for (int col = 0; col < COLS; col++) {
                    if (!canMove) {
                        canMove = move(row, col, horizontalDirection, verticalDirection, dir);
                    } else {
                        move(row, col, horizontalDirection, verticalDirection, dir);
                    }
                }
            }
        } else if (dir == Direction.DOWN) {
            verticalDirection = 1;
            for (int row = ROWS - 1; row >= 0; row--) {
                for (int col = 0; col < COLS; col++) {
                    if (!canMove) {
                        canMove = move(row, col, horizontalDirection, verticalDirection, dir);
                    } else {
                        move(row, col, horizontalDirection, verticalDirection, dir);
                    }
                }
            }

        } else {
            System.out.println(dir + "is not a valid direction");
        }
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                Tile current = board[row][col];
                if (current == null) continue;
                current.setCanCombine(true);
            }
        }
        if (canMove) {
            spawnRandom();
            checkDead();
        }
    }

    private void checkDead() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (board[row][col] == null) {
                    return;
                }
                if (checkSurroundingTile(row, col, board[row][col])) {
                    return;
                }
            }
        }

        dead = true;
        // setHighScore(score);

    }

    private boolean checkSurroundingTile(int row, int col, Tile current) {
        if (row > 0) {
            Tile check = board[row - 1][col];
            if (check == null) {
                return true;
            }
            if (current.getValue() == check.getValue())
                return true;
        }
        if (row < ROWS - 1) {
            Tile check = board[row + 1][col];
            if (check == null) return true;
            if (current.getValue() == check.getValue()) return true;
        }

        if (col > 0) {
            Tile check = board[row][col - 1];
            if (check == null) {
                return true;
            }
            if (current.getValue() == check.getValue())
                return true;
        }
        if (col < COLS - 1) {
            Tile check = board[row][col + 1];
            if (check == null) return true;
            if (current.getValue() == check.getValue()) return true;
        }
        return false;
    }

    private boolean checkOutOfBound(Direction dir, int newRow, int newCol) {
        if (dir == Direction.LEFT) {
            return newCol < 0;

        } else if (dir == Direction.RIGHT) {
            return newCol > COLS - 1;
        } else if (dir == Direction.DOWN) {
            return newRow > ROWS - 1;
        } else if (dir == Direction.UP) {
            return newRow < 0;
        }
        return false;
    }

    private void checkKey() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            Gdx.app.log("Dir", "Left");
            moveTiles(Direction.LEFT);
            if (!hasStarted)
                hasStarted = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            Gdx.app.log("Dir", "Right");
            moveTiles(Direction.RIGHT);
            if (!hasStarted)
                hasStarted = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            Gdx.app.log("Dir", "Down");
            moveTiles(Direction.DOWN);
            if (!hasStarted)
                hasStarted = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            Gdx.app.log("Dir", "Up");
            moveTiles(Direction.UP);
            if (!hasStarted)
                hasStarted = true;
        }
    }
}
