package main;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Ha San~ on 10/27/2016.
 */
public class Tile {
    public static final int WIDTH = 80;
    public static final int HEIGHT = 80;
    public static final int SLIDE_SPEED = 20;
    public static final int ARC_WIDTH = 15;
    public static final int ARC_HEIGHT = 15;
    private Point slideTo;
    private int value;
    private BufferedImage titleImage;
    private Color background;
    private Color text;
    private Font font;
    private int x;
    private int y;

    private boolean canCombine = true;

    public boolean canCombine() {
        return canCombine;
    }


    public void setCanCombine(boolean canCombine) {
        this.canCombine = canCombine;
    }


    public Tile(int value, int x, int y) {
        this.value = value;
        this.x = x;
        this.y = y;
        slideTo = new Point(x, y);
        titleImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        drawImage();
    }

    private void drawImage() {
        Graphics2D g = (Graphics2D) titleImage.getGraphics();
        if (value == 2) {
            background = new Color(0xe9e9e9);
            text = new Color(0x000000);
        } else if (value == 4) {
            background = new Color(0xe6daab);
            text = new Color(0x000000);
        } else if (value == 8) {
            background = new Color(0xf79d3d);
            text = new Color(0x000000);
        } else if (value == 16) {
            background = new Color(0xf28007);
            text = new Color(0x000000);
        } else if (value == 32) {
            background = new Color(0xf55e3b);
            text = new Color(0x000000);
        } else if (value == 64) {
            background = new Color(0xff0000);
            text = new Color(0x000000);
        } else if (value == 128) {
            background = new Color(0xe9de84);
            text = new Color(0x000000);
        } else if (value == 256) {
            background = new Color(0xf6e873);
            text = new Color(0x000000);
        } else if (value == 512) {
            background = new Color(0xf5e455);
            text = new Color(0x000000);
        } else if (value == 1024) {
            background = new Color(0xf7e12c);
            text = new Color(0x000000);
        } else if (value == 2048) {
            background = new Color(0xffe400);
            text = new Color(0x000000);
        } else {
            background = Color.black;
            text = Color.white;
        }
        g.setColor(new Color(0, 0, 0, 0));
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(background);
        g.fillRoundRect(0, 0, WIDTH, HEIGHT, ARC_WIDTH, ARC_HEIGHT);
        g.setColor(text);
        if (value <= 64) {
            font = Game.main.deriveFont(36f);
        } else {
            font = Game.main;
        }
        g.setFont(font);
        int drawX = WIDTH / 2 - DrawUtils.getMessageWidth("" + value, font, g) / 2;
        int drawY = HEIGHT / 2 + DrawUtils.getMessageHeight("" + value, font, g) / 2;
        g.drawString("" + value, drawX, drawY);
        g.dispose();
    }

    public void update() {

    }

    public void render(Graphics2D g) {
        g.drawImage(titleImage, x, y, null);
    }

    public int getValue() {
        return value;
    }

    public Point getSlideTo() {
        return slideTo;
    }

    public void setValue(int value) {
        this.value = value;
        drawImage();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setSlideTo(Point slideTo) {
        this.slideTo = slideTo;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "value=" + value +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
