package dev.game.doremon.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import dev.game.doremon.gfx.Assets;
import dev.game.doremon.main.DoremonHandler;
import dev.game.main.GameHandler;

/**
 * Created by Nghia on 11/12/2016.
 */

public class Item extends Entity {

    private static final int DEFAULT_WIDTH = 80;
    private static final int DEFAULT_HEIGHT = 80;

    private static final int DEFAULT_BOUNDS_RADIUS = 220;





    private static final int DEFAULT_TYPE = 0;

    private Circle gravityCircle;

    private int type;
    private boolean alive;

    public Item(DoremonHandler doremonHandler, float x, float y, int type) {
        super(doremonHandler, x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        if (type < Assets.itemTypes.size()) {
            this.type = type;
        } else {
            this.type = DEFAULT_TYPE;
        }

        gravityCircle = new Circle(x, y, DEFAULT_BOUNDS_RADIUS);
        alive = false;
    }

    @Override
    public String toString() {
        return Assets.itemTypes.get(type).toString();
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
        return isAlive() && getY()+getHeight() <= GameHandler.GAME_HEIGHT;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }


    @Override
    public void render(SpriteBatch batch) {
        if (alive) {
            batch.draw(Assets.itemTypes.get(type).getItemTextureRegion(), x, y, width, height);
        }
    }

    public void getNewRandomType() {
        type = (int) (Math.random() * (Assets.itemTypes.size()));
    }

    public void updateWithPlatform(Platform platform) {
        setX(platform.getX() + (platform.getWidth() - getWidth()) / 2);
        setY(platform.getY() - getHeight());
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
        return Assets.itemTypes.get(type);
    }


    public static Item createRandomItemForPlatfrom(DoremonHandler doremonHandler, Platform platform) {
        return new Item(doremonHandler, platform.getX() + (platform.getWidth() - DEFAULT_WIDTH) / 2
                , platform.getY() - DEFAULT_HEIGHT
                , (int) (Math.random() * (Assets.itemTypes.size())));
    }

    public void dispose() {

    }




}
