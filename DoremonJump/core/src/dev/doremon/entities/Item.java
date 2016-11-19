package dev.doremon.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import dev.doremon.main.Handler;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Nghia on 11/12/2016.
 */

public class Item extends Entity {

    private static final int DEFAULT_WIDTH = 80;
    private static final int DEFAULT_HEIGHT = 80;

    private static final int DEFAULT_BOUNDS_RADIUS = 220;



    private static final ItemAttributes[] attributes = new ItemAttributes[]{
            new ItemAttributes("food-01.png"),
            new ItemAttributes("food-02.png"),
            new ItemAttributes("food-03.png"),
            new ItemAttributes("food-04.png"),
            new ItemAttributes("Untitled-1-05.png"),
            new ItemAttributes("Untitled-1-06.png"),
            new ItemAttributes("Untitled-1-07.png"),
            new ItemAttributes("Untitled-1-08.png"),
            new ItemAttributes("Untitled-1-09.png"),
            new ItemAttributes("Untitled-1-10.png"),
            new ItemAttributes("Untitled-1-11.png"),
            new ItemAttributes("Untitled-1-12.png"),
            new ItemAttributes("Untitled-1-13.png"),
            // item 14 looks like shit
//            new ItemAttributes("Untitled-1-14.png"),
            new ItemAttributes("Untitled-1-15.png"),
            new ItemAttributes("Untitled-1-16.png"),
            new ItemAttributes("Untitled-1-17.png"),
            new ItemAttributes("Untitled-1-18.png"),
            new ItemAttributes("Untitled-1-19.png"),
            new ItemAttributes("Untitled-1-20.png"),
            new ItemAttributes("Untitled-1-21.png"),
            new ItemAttributes("Untitled-1-22.png")

    };
    public static final ArrayList<ItemAttributes> itemTypes = new ArrayList<ItemAttributes>(Arrays.asList(attributes));
    private static final int DEFAULT_TYPE = 0;

    private Circle gravityCircle;

    private int type;
    private boolean alive;

    public Item(Handler handler, float x, float y, int type) {
        super(handler, x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        if (type < itemTypes.size()) {
            this.type = type;
        } else {
            this.type = DEFAULT_TYPE;
        }

        gravityCircle = new Circle(x, y, DEFAULT_BOUNDS_RADIUS);
        alive = false;
//        alive = true;
    }

    @Override
    public String toString() {
        return itemTypes.get(type).toString();
    }

    @Override
    public void tick() {
        updateBounds();
        updateGravityCircle();
    }


    public int getType() {
        return type;
    }


    public boolean isAlive() {
        return alive;
    }


    public boolean isCollidable() {
        return isAlive() && getY()+getHeight() <= handler.getHeight();
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }


    @Override
    public void render(SpriteBatch batch) {
        if (alive) {
            batch.draw(itemTypes.get(type).getItemTextureRegion(), x, y, width, height);
        }
    }

    public void getNewRandomType() {
        type = (int) (Math.random() * (itemTypes.size()));
    }

    public void updateWithPlatform(Platform platform) {

        setX(platform.getX() + (platform.getWidth() - DEFAULT_WIDTH) / 2);
        setY(platform.getY() - DEFAULT_HEIGHT);
        updateBounds();
        updateGravityCircle();
    }

    private void updateGravityCircle() {
        gravityCircle.setX(x);
        gravityCircle.setY(y);
    }

    public boolean gravityRangeReached(Entity target) {
        return gravityCircle.contains(target.getMiddleX(), target.getMiddleY());
    }

    public ItemAttributes getItemAttributes() {
        return itemTypes.get(type);
    }


    public static Item createRandomItemForPlatfrom(Handler handler, Platform platform) {
        return new Item(handler, platform.getX() + (platform.getWidth() - DEFAULT_WIDTH) / 2
                , platform.getY() - DEFAULT_HEIGHT
                , (int) (Math.random() * (itemTypes.size())));
    }


}
