package other;

import controllers.Direction;
import modules.GameObject;

/**
 * Created by Nghia on 10/27/2016.
 */
public class GameConfig {
    private final static int DEFAULT_DELAY = 17*100;
    private final static int GAME_SPEED = 20;

    private static final int PIXEL = 10;

    private static final int DEFAULT_WIDTH = 40 * PIXEL;
    private static final int DEFAULT_HEIGHT = 40 * PIXEL;

    private int threadDelayInMiliseconds;
    private int screenWidth;
    private int screenHeight;

    public double getSeconds(int count) {
        return (threadDelayInMiliseconds * count) / 1000;
    }

    public double getMiliseconds(int count) {
        return threadDelayInMiliseconds * count;
    }

    private GameConfig(int threadDelayInMiliseconds, int screenWidth, int screenHeight) {
        this.threadDelayInMiliseconds = threadDelayInMiliseconds;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public int getThreadDelayInMiliseconds() {
        return threadDelayInMiliseconds;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setThreadDelayInMiliseconds(int threadDelayInMiliseconds) {
        this.threadDelayInMiliseconds = threadDelayInMiliseconds;
    }
    public int getPixel(){
        return PIXEL;
    }

    public int getRandomPixelX(){
        return (int)(Math.random()*getScreenWidth()/getPixel()-1)*getPixel();
    }
    public int getRandomPixelY(){
        return (int)(Math.random()*getScreenHeight()/getPixel()-1)*getPixel();
    }

    //ATTENSION : always y<=SCREEN_HEIGHT-1
    public boolean yOutsideScreen(int y) {
        return y < 0 || y > screenHeight-1;
    }

    public boolean yOutsideScreen(GameObject gameObject) {
        return yOutsideScreen(gameObject.getY());
    }
    public boolean xOutsideScreen(int x) {
        return x < 0 || x > screenWidth-1;
    }


    public boolean xOutsideScreen(GameObject gameObject) {
        return xOutsideScreen(gameObject.getX());
    }

    public Direction getReverseDirection(Direction direction){
        switch (direction){
            case Up:
                return Direction.Down;
            case Down:
                return Direction.Up;
            case Left:
                return Direction.Right;
            case Right:
                return Direction.Left;
        }
        return null;
    }


    public static final GameConfig instance = new GameConfig(
            DEFAULT_DELAY/GAME_SPEED,
            DEFAULT_WIDTH,
            DEFAULT_HEIGHT
    );
}
