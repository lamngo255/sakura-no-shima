package main;

import javax.swing.*;

/**
 * Created by Ha San~ on 10/25/2016.
 */
public class GameWindow  {
    public static void main(String[] args) {
        Game game = new Game();
        JFrame window = new JFrame("2048");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.add(game);
        window.pack();
        System.out.println("Helloi");
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        game.start();
    }
}
