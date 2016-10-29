/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Bird;
import Controller.ChimneyGroup;
import Controller.Ground;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import Model.AFrameOnImage;
import Model.Animation;
import Model.GameScreen;

/**
 *
 * @author Minh Quan
 */
public class FlappyBirds extends GameScreen {

    private BufferedImage birds;
    private Animation bird_anim;
    public static float g = 0.15f;
    public Bird bird;
    private Ground ground;
    private BufferedImage backgroundImg;

    private ChimneyGroup chimneyGroup;

    private int point = 0;
    private int hightPoint = 0;

    private int BEGIN_SCREEN;
    private int GAMEPLAY_SCREEN = 1;
    private int GAMEOVER_SCREEN = 2;
    private int currentScreen = BEGIN_SCREEN;

    public FlappyBirds() {
        super(800, 600);

        try {
            backgroundImg = ImageIO.read(new File("Assets/fuji.png"));
            birds = ImageIO.read(new File("Assets/bird_sprite.png"));

        } catch (IOException ex) {
            Logger.getLogger(FlappyBirds.class.getName()).log(Level.SEVERE, null, ex);
        }
        bird_anim = new Animation(70);
        AFrameOnImage f;
        f = new AFrameOnImage(0, 0, 60, 60);
        bird_anim.AddFrame(f);
        f = new AFrameOnImage(60, 0, 60, 60);
        bird_anim.AddFrame(f);
        f = new AFrameOnImage(120, 0, 60, 60);
        bird_anim.AddFrame(f);
        f = new AFrameOnImage(60, 0, 60, 60);
        bird_anim.AddFrame(f);

        bird = new Bird(350, 250, 50, 50);
        ground = new Ground();

        chimneyGroup = new ChimneyGroup();
        BeginGame();
    }

    public static void main(String[] args) {
        new FlappyBirds();
    }

    private void resetGame() {
        bird.setPos(350, 250);
        bird.setVt(0);
        bird.setLive(true);
        point = 0;
        chimneyGroup.resetChimneys();
    }

    @Override
    public void GAME_UPDATE(long deltaTime) {
        if (currentScreen == BEGIN_SCREEN) {
            resetGame();

        } else if (currentScreen == GAMEPLAY_SCREEN) {
            if (bird.getLive()) {
                bird_anim.Update_Me(deltaTime);
            }
            bird.update(deltaTime);
            if (bird.getLive()) {
                ground.Update();
                chimneyGroup.update();
            }

            for (int i = 0; i < chimneyGroup.SIZE; i++) {
                if (bird.getRect().intersects(chimneyGroup.getChimney(i).getRect())) {
                    if (bird.getLive()) {
                        bird.bupSound.play();
                    }
                    bird.setLive(false);
                }
            }
            for (int i = 0; i < ChimneyGroup.SIZE; i++) {
                if (bird.getPosX() > chimneyGroup.getChimney(i).getPosX() && !chimneyGroup.getChimney(i).getIsBehindBird() && i % 2 == 0) {
                    point++;
                    bird.getMoneySound.play();
                    chimneyGroup.getChimney(i).setIsBehindBird(true);
                }
            }
            if ((bird.getPosY() + bird.getH()) > ground.getYGround()) {
                if (bird.getLive()) {
                    bird.bupSound.play();
                }
                currentScreen = GAMEOVER_SCREEN;
            }
        } else {

        }

    }

    @Override
    public void GAME_PAINT(Graphics2D g2) {
        g2.setColor(Color.decode("#b8daef"));
        g2.fillRect(0, 0, MASTER_WIDTH, MASTER_HEIGHT);
        g2.drawImage(backgroundImg, 0, 0, null);
        chimneyGroup.paint(g2);
        ground.Paint(g2);

        if (bird.getIsFlying()) {
            bird_anim.PaintAnims((int) bird.getPosX(), (int) bird.getPosY(), birds, g2, 0, -1);
        } else {
            bird_anim.PaintAnims((int) bird.getPosX(), (int) bird.getPosY(), birds, g2, 0, 0);
        }

        if (currentScreen == BEGIN_SCREEN) {
            g2.setColor(Color.red);
            Font font = new Font("Aria", Font.BOLD, 30);
            g2.setFont(font);
            g2.drawString("Press space to play game", 200, 350);

        }
        if (currentScreen == GAMEOVER_SCREEN) {
            if (hightPoint < point) {
                hightPoint = point;
            }

            g2.setColor(Color.BLUE);
            Font font = new Font("Aria", Font.BOLD, 30);
            g2.setFont(font);
            g2.drawString("Your Point: " + point, 300, 180);
            g2.drawString("Best Point: " + hightPoint, 300, 215);
            g2.drawString("Press space to turn back begin screen", 120, 250);
        }
        g2.setColor(Color.red);
        Font font = new Font("Aria", Font.BOLD, 36);
        g2.setFont(font);
        g2.drawString("Point: " + point, 650, 50);
    }

    @Override
    public void KEY_ACTION(KeyEvent e, int Event) {
        if (Event == KEY_PRESSED) {
            if (currentScreen == BEGIN_SCREEN) {
                currentScreen = GAMEPLAY_SCREEN;

            } else if (currentScreen == GAMEPLAY_SCREEN) {
                if (bird.getLive()) {
                    bird.fly();
                }
            } else if (currentScreen == GAMEOVER_SCREEN) {
                currentScreen = BEGIN_SCREEN;
            }

        }
    }

}
