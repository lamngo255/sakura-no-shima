package main;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 * Created by Ha San~ on 11/8/2016.
 */
public class GameBoard {
    public static final int ROWS = 4;
    public static final int COL = 4;

    private final int startingTilres = 2;
    private Title[][] board;
    private boolean dead;
    private boolean won;
    private BufferedImage gameBoard;
    private BufferedImage finalBoard;
    private int x;
    private int y;
    private static int SPACING = 10;
    public static int BOARD_WIDTH = (COL + 1) * SPACING + COL * Title.WIDTH;
    public static int BOARD_HEIGHT = (ROWS + 1) * SPACING + ROWS * Title.HEIGHT;
    public static boolean hasStarted;

    public GameBoard(int x, int y) {
        this.x = x;
        this.y = y;
        board = new Title[ROWS][COL];
        gameBoard = new BufferedImage(BOARD_WIDTH, BOARD_HEIGHT, BufferedImage.TYPE_INT_RGB);
        finalBoard = new BufferedImage(BOARD_WIDTH, BOARD_HEIGHT, BufferedImage.TYPE_INT_RGB);
        createBoardImage();
    }

    private void createBoardImage() {
        Graphics2D g = (Graphics2D) gameBoard.getGraphics();
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
        g.setColor(Color.lightGray);
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COL; col++) {
                int x = SPACING + SPACING * col + Title.WIDTH * col;
                int y = SPACING + SPACING * row + Title.WIDTH * row;
                g.fillRoundRect(x, y, Title.WIDTH, Title.HEIGHT, Title.ARC_WIDTH, Title.ARC_HEIGHT);
            }
        }
    }

    public void render(Graphics2D g) {
        Graphics2D g2d = (Graphics2D) finalBoard.getGraphics();
        g2d.drawImage(gameBoard, 0, 0, null);
        g.drawImage(finalBoard,x,y,null);
        g2d.dispose();
    }

    public void update() {
        checkKey();
    }

    private void checkKey() {
        if (KeyBoard.Typed(KeyEvent.VK_LEFT)) {
            if (!hasStarted) hasStarted = true;
        }
        if (KeyBoard.Typed(KeyEvent.VK_RIGHT)) {
            if (!hasStarted) hasStarted = true;
        }
        if (KeyBoard.Typed(KeyEvent.VK_UP)) {
            if (!hasStarted) hasStarted = true;
        }
        if (KeyBoard.Typed(KeyEvent.VK_DOWN)) {
            if (!hasStarted) hasStarted = true;
        }
    }
}
