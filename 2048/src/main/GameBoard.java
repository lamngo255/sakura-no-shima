package main;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

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
        start();
    }

    private void createBoardImage() {
        Graphics2D g = (Graphics2D) gameBoard.getGraphics();
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
        g.setColor(Color.lightGray);
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COL; col++) {
                int x = (SPACING + SPACING * col + Title.WIDTH * col);
                int y = (SPACING + SPACING * row + Title.WIDTH * row);
                g.fillRoundRect(x, y, Title.WIDTH, Title.HEIGHT, Title.ARC_WIDTH, Title.ARC_HEIGHT);
            }
        }
    }

    public void render(Graphics2D g) {
        Graphics2D g2d = (Graphics2D) finalBoard.getGraphics();
        g2d.drawImage(gameBoard, 0, 0, null);
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COL; col++) {
                Title current = board[row][col];

                if (current == null) continue;
                current.render(g2d);
            }
        }
        g.drawImage(finalBoard, x, y, null);
        g2d.dispose();
    }

    public void start() {
        for (int i = 0; i < startingTilres; i++) {
            spawnRandom();
        }
    }

    private void spawnRandom() {
        Random random = new Random();
        boolean notvalid = true;
        while (notvalid) {
            int location = random.nextInt(ROWS * COL);
            int row = location / ROWS;
            int col = location % COL;
            Title current = board[row][col];
            if (current == null) {
                int value = random.nextInt(10) < 9 ? 2 : 4;
                Title title = new Title(value, getTitleX(col), getTitleY(row));
                board[row][col] = title;
                notvalid = false;
            }
        }
    }

    public int getTitleX(int col) {
        return SPACING + col * Title.WIDTH + col * SPACING;
    }

    public int getTitleY(int row) {
        return SPACING + row * Title.HEIGHT + row * SPACING;
    }

    public void update() {
        checkKey();
        for (int row=0;row<ROWS;row++){
            for(int col=0;col<COL;col++){
                Title current = board[row][col];
                if(current==null) continue;
                if(current.getValue()==2048){
                    won=true;
                }
            }
        }
    }
private void moveTitles(Direction dir){
    boolean canMove = false;
    int horizontalDirection =0;
    int verticalDirection =0;
    if(dir==Direction.LEFT){
        horizontalDirection=-1;
        for (int row=0; row <ROWS;row++){
            for (int col=0;col<COL;col++){
                if(!canMove){
                    canMove = move(row,col,horizontalDirection,verticalDirection,dir);
                }else{
                    move(row,col,horizontalDirection,verticalDirection,dir);
                }
            }
        }
    }
  else  if(dir==Direction.RIGHT){
        horizontalDirection =1;
        for (int row=0; row <ROWS;row++){
            for (int col=COL-1;col>=0;col--){
                if(!canMove){
                    canMove = move(row,col,horizontalDirection,verticalDirection,dir);
                }else{
                    move(row,col,horizontalDirection,verticalDirection,dir);
                }
            }
        }
    }
   else if(dir==Direction.UP){
        verticalDirection=-1;
        for (int row=0; row <ROWS;row++){
            for (int col=0;col<COL;col++){
                if(!canMove){
                    canMove = move(row,col,horizontalDirection,verticalDirection,dir);
                }else{
                    move(row,col,horizontalDirection,verticalDirection,dir);
                }
            }
        }
    }
   else if(dir==Direction.DOWN){
        verticalDirection=1;
        for (int row=ROWS-1; row >=ROWS;row--){
            for (int col=0;col<COL;col++){
                if(!canMove){
                    canMove = move(row,col,horizontalDirection,verticalDirection,dir);
                }else{
                    move(row,col,horizontalDirection,verticalDirection,dir);
                }
            }
        }

    }else {
        System.out.println();
    }
    for (int row=0;row<ROWS;row++){
        for (int col=0;col<COL;col++){
            Title current = board[row][col];
            if(current==null) continue;;
            current.setCanCombine(true);

        }
    }
    if(canMove){
        spawnRandom();
    }

}
private boolean move(int row, int col, int hori,int verti,Direction dir){
    boolean canMove = false;
    Title current = board[row][col];
    if(current==null)return false;
    boolean move = true;
    int newCol =col;
    int newRow =row;
    while (move){
        newCol+=hori;
        newRow+=verti;
        if(checkoutofbound(dir,newRow,newCol)) break;
        if(board[newRow][newCol]==null){
            board[newRow][newCol]=current;
            board[newRow-verti][newCol-hori]=null;
            board[newRow][newCol].setSlideTo(new Point(newRow,newCol));
        }else if(board[newRow][newCol].getValue()==current.getValue() &&board[newRow][newCol].setCanCombine()){

        }
    }
    return canMove;

}

    private boolean checkoutofbound(Direction dir, int newRow, int newCol) {
        if(dir==Direction.LEFT){
            return newCol<0;

        }else if(dir==Direction.RIGHT){
            return newCol>COL-1;
        }else if(dir==Direction.DOWN){
            return newRow>ROWS-1;
        }else if(dir==Direction.UP){
            return newRow<0;
        }
        return false;
    }

    private void checkKey() {
        if (KeyBoard.Typed(KeyEvent.VK_LEFT)) {
            moveTitles(Direction.LEFT);
            if (!hasStarted) hasStarted = true;
        }
        if (KeyBoard.Typed(KeyEvent.VK_RIGHT)) {
            moveTitles(Direction.RIGHT);
            if (!hasStarted) hasStarted = true;
        }
        if (KeyBoard.Typed(KeyEvent.VK_UP)) {
            moveTitles(Direction.UP);
            if (!hasStarted) hasStarted = true;
        }
        if (KeyBoard.Typed(KeyEvent.VK_DOWN)) {
            moveTitles(Direction.DOWN);
            if (!hasStarted) hasStarted = true;
        }
    }
}
