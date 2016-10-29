/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.FlappyBirds;
import java.awt.Rectangle;
import java.io.File;
import Model.Objects;
import Model.SoundPlayer;

/**
 *
 * @author Minh Quan
 */
public class Bird extends Objects {

    private float vt = 0;
    private boolean isFlying = false;
    private Rectangle rect;

    private boolean isLive = true;
    
    public SoundPlayer flapSound, bupSound, getMoneySound;

    public Bird(int x, int y, int w, int h) {
        super(x, y, w, h);
        rect = new Rectangle(x, y, w, h);
        
        flapSound = new SoundPlayer(new File("Assets/fap.wav"));
       bupSound = new SoundPlayer(new File("Assets/fall.wav"));
       getMoneySound = new SoundPlayer(new File("Assets/getpoint.wav"));
        
    }

    public void update(long deltaTime) {
        vt += FlappyBirds.g;

        this.setPosY(this.getPosY() + vt);
        this.rect.setLocation((int) this.getPosX(), (int) this.getPosY());

        if (vt < 0) {
            isFlying = true;
        } else {
            isFlying = false;
        }
    }

    public void fly() {
        vt = -4;
        flapSound.play();
    }

    public boolean getIsFlying() {
        return isFlying;
    }

    public void setVt(float vt) {
        this.vt = vt;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setLive(boolean isLive) {
        this.isLive = isLive;
    }

    public boolean getLive() {
        return isLive;
    }

}
