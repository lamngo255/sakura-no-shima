package other;

/**
 * Created by Nghia on 10/26/2016.
 */
public class Launcher {
    public static void main(String[] args) {
        GameWindow gameWindow = new GameWindow();
        Thread thread = new Thread(gameWindow);
        thread.start();
    }
}
