package dev.breakout.worlds;

import dev.breakout.Handler;
import dev.breakout.entities.Ball;
import dev.breakout.entities.Brick;
import dev.breakout.entities.Paddle;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JOptionPane;

public class World {

    public static final int BRICK_ROW_COUNT = 5;
    public static final int BRICK_COLUMN_COUNT = 4;
    public static final int DEFAULT_BONUS = 100;

    private final Handler handler;
    private Brick[][] bricks;
    private Paddle paddle;
    private Ball ball;
    private int score = 0;
    private int lives = 3;
    private boolean gameOver = false;

    public World(Handler handler) {
        this.handler = handler;
        loadWorld();
    }

    public void tick() {
        if (lives <= 0) {
            JOptionPane.showMessageDialog(null, "Game Over! Your final score is: " + score);
            resetGame();
        }
        for (int c = 0; c < BRICK_COLUMN_COUNT; ++c) {
            for (int r = 0; r < BRICK_ROW_COUNT; ++r) {
                bricks[c][r].tick();
            }
        }
        paddle.tick();
        ball.tick();
    }

    public void render(Graphics g) {
        for (int c = 0; c < BRICK_COLUMN_COUNT; ++c) {
            for (int r = 0; r < BRICK_ROW_COUNT; ++r) {
                if (bricks[c][r].isActive()) {
                    bricks[c][r].render(g);
                }
            }
        }

        paddle.render(g);
        ball.render(g);
        drawScore(g);
        drawLives(g);
    }

    public void createBricks() {
        bricks = new Brick[BRICK_COLUMN_COUNT][BRICK_ROW_COUNT];

        for (int c = 0; c < BRICK_COLUMN_COUNT; ++c) {
            for (int r = 0; r < BRICK_ROW_COUNT; ++r) {
                bricks[c][r] = new Brick(handler, 0, 0);
            }
        }
    }

    public void createPaddle() {
        int paddleX = (handler.getWidth() - Paddle.PADDLE_WIDTH) / 2;
        int paddleY = handler.getHeight() - Paddle.PADDLE_HEIGHT - 10;
        paddle = new Paddle(handler, paddleX, paddleY);
        handler.getMouseManager().setPaddle(paddle);
    }

    public void createBall() {
        int ballX = handler.getWidth() / 2;
        int ballY = handler.getHeight() - 40;
        ball = new Ball(handler, ballX, ballY);
    }

    public void drawScore(Graphics g) {
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Score: " + score, 10, 30);
    }

    public void drawLives(Graphics g) {
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Lives: " + lives, handler.getWidth() - 90, 30);
    }

    private void resetGame() {
        score = 0;
        lives = 3;

        for (int c = 0; c < BRICK_COLUMN_COUNT; ++c) {
            for (int r = 0; r < BRICK_ROW_COUNT; ++r) {
                bricks[c][r].setActive(true);
            }
        }
        paddle.setX((handler.getWidth() - Paddle.PADDLE_WIDTH) / 2);
        paddle.setY(handler.getHeight() - Paddle.PADDLE_HEIGHT - 10);
        ball.setX(handler.getWidth() / 2);
        ball.setY(handler.getHeight() - 40);
    }

    public final void loadWorld() {
        createBricks();
        createPaddle();
        createBall();
        for (int c = 0; c < BRICK_COLUMN_COUNT; ++c) {
            for (int r = 0; r < BRICK_ROW_COUNT; ++r) {
                if (bricks[c][r].isActive()) {
                    int brickX = (r * (Brick.BRICK_WIDTH + Brick.BRICK_PADDING))
                            + Brick.BRICK_OFFSET_LEFT;
                    int brickY = (c * (Brick.BRICK_HEIGHT + Brick.BRICK_PADDING))
                            + Brick.BRICK_OFFSET_TOP;
                    bricks[c][r].setX(brickX);
                    bricks[c][r].setY(brickY);
                }
            }
        }
    }

    public Brick[][] getBricks() {
        return bricks;
    }

    public void setBricks(Brick[][] bricks) {
        this.bricks = bricks;
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public void setPaddle(Paddle paddle) {
        this.paddle = paddle;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public void addScore() {
        this.score += DEFAULT_BONUS;
    }

    public void loseALife() {
        this.lives--;
    }

}
