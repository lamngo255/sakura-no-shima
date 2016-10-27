package other;

import controllers.CollisionPool;
import controllers.ControllerManager;
import controllers.FoodController;
import controllers.SnakeController;
import utils.Utils;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.*;

/**
 * Created by apple on 10/2/16.
 */
public class GameWindow extends Frame implements Runnable {

    Image backgroundImage = null;
    Image backBufferImage;
    Canvas canvas;
    Vector<KeyEvent> keysListened;
    private static boolean isRunning;


    int backgroundWidth;
    int backgroundHeight;

    private ControllerManager controllerManager;

    public GameWindow() {
        isRunning=true;
        backgroundWidth = GameConfig.instance.getScreenWidth();
        backgroundHeight = GameConfig.instance.getScreenHeight();

        keysListened = new Stack<>();



        controllerManager = new ControllerManager();
        controllerManager.add(SnakeController.instance);
        controllerManager.add(FoodController.instance);
        controllerManager.add(CollisionPool.instance);


        backBufferImage = new BufferedImage(backgroundWidth,
                backgroundHeight, BufferedImage.TYPE_INT_ARGB);




        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(backgroundWidth, backgroundHeight));
        canvas.setMaximumSize(new Dimension(backgroundWidth, backgroundHeight));
        canvas.setMinimumSize(new Dimension(backgroundWidth, backgroundHeight));
        this.add(canvas);


        this.setVisible(true);
        this.setSize(GameConfig.instance.getScreenWidth(),
                GameConfig.instance.getScreenHeight());
//        this.setMinimumSize(new Dimension(GameConfig.instance.getScreenWidth(),
//                GameConfig.instance.getScreenHeight()));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
//        this.add(new Canvas(backBufferImage.getGraphics()));
        this.pack();


        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("windowOpened");
            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("windowClosing");
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("windowClosed");
            }

            @Override
            public void windowIconified(WindowEvent e) {
                System.out.println("windowIconified");
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                System.out.println("windowDeiconified");
            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });


        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println("keyTyped");
            }

            @Override
            public void keyPressed(KeyEvent e) {
//                System.out.println("keyPressed");
                keysListened.add(e);
//                SnakeController.instance.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
//                System.out.println("keyReleased");
            }
        });

        backgroundImage = Utils.loadImageFromRes("pixel.png");

    }

    public static boolean isRunning() {
        return isRunning;
    }

    public static void setRunning(boolean running) {
        isRunning = running;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        System.out.println("draw background image");
    }

    @Override
    public void update(Graphics g) {

        Graphics backBufferGraphics = backBufferImage.getGraphics();

        backBufferGraphics.drawImage(backgroundImage,
                0, 0,
                backgroundWidth, backgroundHeight, null);

        controllerManager.draw(backBufferGraphics);
        backBufferGraphics.setColor(Color.black);
        backBufferGraphics.drawRect( 0, 0,
                backgroundWidth-1, backgroundHeight-1);

//        g.drawImage(backBufferImage,
//                0, 0,
//                backgroundWidth, backgroundHeight, null);
        Graphics graphics = canvas.getGraphics();
        graphics.drawImage(backBufferImage,
                0, 0,
                backgroundWidth, backgroundHeight, null);
    }

    @Override
    public void run() {
        while(isRunning) {
            try {

                Thread.sleep(GameConfig
                        .instance
                        .getThreadDelayInMiliseconds());
                if (!keysListened.isEmpty()){
                    SnakeController.instance.keyPressed(keysListened.get(0));
                    keysListened.remove(0);
                }
                controllerManager.run();
                repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
