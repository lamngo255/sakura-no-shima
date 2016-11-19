package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

/**
 * Created by Ha San~ on 10/25/2016.
 */
public class Game extends JPanel implements KeyListener, Runnable {

    public static final int width = 400;
    public static final int height = 630;
    public static final Font main = new Font("Bebas Neue Regular", Font.PLAIN, 28);
    private Thread game;
    private boolean running;
    private BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private long startgame;
    private long elapesd;
    private boolean set;
   private GameBoard gameBoard;

    public Game() {
        setFocusable(true);
        setPreferredSize(new Dimension(width, height));
        addKeyListener(this);
        gameBoard = new GameBoard((width/2-GameBoard.BOARD_WIDTH/2),(height-GameBoard.BOARD_HEIGHT-10));

    }

    private void update() {
        gameBoard .update();
        KeyBoard.update();
    }

    private void render() {
        Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, width, height);
        gameBoard.render(g);
        g.dispose();
        //render board
        Graphics2D g2d = (Graphics2D) getGraphics();
        g2d.drawImage(bufferedImage,0,0,null);
        g2d.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        KeyBoard.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        KeyBoard.keyReleased(e);
    }

    @Override
    public void run() {

        int fps = 0, update = 0;
        long fpsTimer = System.currentTimeMillis();
        double nsPerUpdate = 1000000000.0 / 60;

        //last update time in nanoseconds

        double then = System.nanoTime();
        double unprocessed = 0;

        //update queue
        while (running) {
            boolean souldrender = false;
            double now = System.nanoTime();
            unprocessed += (now - then) / nsPerUpdate;
            then = now;

            while (unprocessed >= 1) {
                update++;

                update();
                unprocessed--;
                souldrender = true;
            }
            //render
            if (souldrender) {
                fps++;
                render();
                souldrender = false;
            } else {
                try {
                    Thread.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            //FPS timer
            if (System.currentTimeMillis() - fpsTimer > 1000) {
                System.out.printf("%d fps %d updates", fps, update);
                System.out.println();
                fps = 0;
                update = 0;
                fpsTimer += 1000;

            }
        }
    }
    public synchronized void start(){
        if(running) return;
        running =true;
        game = new Thread(this,"game");
        game.start();
    }
    public synchronized void stop(){
        if(!running) return;
        running =false;
        System.exit(0);
    }
}
