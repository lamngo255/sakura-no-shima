/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.Chimney;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import Model.QueueList;

/**
 *
 * @author Minh Quan
 */
public class ChimneyGroup {

    private QueueList<Chimney> chimneys;
    private BufferedImage chimneyImage, chimneyImage2;
    public static int SIZE = 6;
    
    private int topChimneyY = -350;
    private int bottonChimneyY = 195;

    public Chimney getChimney(int i) {
        return chimneys.get(i);
    }

    public int getRandomY() {
        Random random = new Random();
        int a;
        a = random.nextInt(10);
        return a*35;
    }

    public ChimneyGroup() {

        try {
            chimneyImage = ImageIO.read(new File("Assets/chimney.png"));
            chimneyImage2 = ImageIO.read(new File("Assets/chimney2.png"));
        } catch (IOException ex) {
            Logger.getLogger(ChimneyGroup.class.getName()).log(Level.SEVERE, null, ex);
        }

        chimneys = new QueueList<Chimney>();

        Chimney cn;

        for (int i = 0; i < SIZE / 2; i++) {
            int deltaY = getRandomY();
            cn = new Chimney(830 + i * 300, bottonChimneyY + deltaY, 74, 400);
            chimneys.push(cn);
            cn = new Chimney(830 + i * 300, topChimneyY + deltaY, 74, 400);
            chimneys.push(cn);
        }
    }

    public void update() {
        for (int i = 0; i < SIZE; i++) {
            chimneys.get(i).update();
        }

        if (chimneys.get(0).getPosX() < -74) {
            Chimney cn;
            cn = chimneys.pop();
            int deltaY=getRandomY();
            
            cn.setPosX(chimneys.get(4).getPosX() + 300);
            cn.setPosY(bottonChimneyY+deltaY);
            cn.setIsBehindBird(false);
            chimneys.push(cn);

            cn = chimneys.pop();
            cn.setPosX(chimneys.get(4).getPosX());
             cn.setPosY(topChimneyY+deltaY);
            cn.setIsBehindBird(false);
            chimneys.push(cn);
        }
    }

    public void paint(Graphics2D g2) {
        for (int i = 0; i < 6; i++) {
            if (i % 2 == 0) {
                g2.drawImage(chimneyImage, (int) chimneys.get(i).getPosX(), (int) chimneys.get(i).getPosY(), null);
            } else {
                g2.drawImage(chimneyImage2, (int) chimneys.get(i).getPosX(), (int) chimneys.get(i).getPosY(), null);
            }
        }
    }

    public void resetChimneys() {
        chimneys = new QueueList<Chimney>();

        Chimney cn;

        for (int i = 0; i < SIZE / 2; i++) {
            int deltaY = getRandomY();
            cn = new Chimney(830 + i * 300, bottonChimneyY + deltaY, 74, 400);
            chimneys.push(cn);
            cn = new Chimney(830 + i * 300, topChimneyY + deltaY, 74, 400);
            chimneys.push(cn);
       
        }
    }
}
