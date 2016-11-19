package dev.doremon.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import dev.doremon.main.Handler;

/**
 * Created by Nghia on 11/14/2016.
 */


public class FlyingItem extends Entity {

    private static final int SPEED = 5;
    private static final float DEFAULT_COLLISION_BOUNDS_SCALE = 0.5f;
    private static final float DEFAULT_TARGET_COLISSION_RANGE = 20f;

    private ItemAttributes itemAttributes;
    private Entity target;

    private Circle targetBounds;
    private Circle flyingBounds;


    private Vector2 velocity;
    boolean alive;


    public FlyingItem() {
        alive = false;
    }


    public FlyingItem(Handler handler, Item itemTriggered, Entity target, boolean isAlive) {
        super(handler, itemTriggered.getX(), itemTriggered.getY(), itemTriggered.getWidth(), itemTriggered.getHeight());
        this.itemAttributes = itemTriggered.getItemAttributes();
        this.target = target;
        velocity = new Vector2(0, 0);

        //new
        flyingBounds = new Circle(x, y, itemTriggered.getWidth() / 2);
        targetBounds = new Circle(target.getMiddleX(), target.getMiddleY(), DEFAULT_TARGET_COLISSION_RANGE);

        alive = isAlive;
    }

    private Rectangle getTargetCollisionBounds(Entity target) {
        float scale = DEFAULT_COLLISION_BOUNDS_SCALE;

        float newTargetWidth = target.getWidth()*scale;
        float newTargetHeight = target.getHeight() * scale;

        float newTargetX = target.getMiddleX() - newTargetWidth;
        float newTargetY = target.getMiddleY() - newTargetHeight;

        Rectangle newbounds = new Rectangle(newTargetX,newTargetY,newTargetWidth,newTargetHeight);

        return newbounds;
    }

    private Circle getTargetBounds() {
        return targetBounds;

    }

    private void updateFlyingBounds() {
        flyingBounds.setPosition(this.getX(),this.getY());
    }

    @Override
    public void tick() {
        if (isAlive()) {
            updateVelocity(target);
            this.move(velocity);
            updateFlyingBounds();
            updateTargetBounds();
//            updateBounds();
//            if (this.bounds.overlaps(getTargetCollisionBounds(target))) {
            if (this.flyingBounds.overlaps(targetBounds))
            {
                setAlive(false);
                //done colliding
            }
        }

    }

    private void updateTargetBounds() {
        targetBounds.setPosition(target.getMiddleX(),target.getMiddleY());
    }


    private void updateVelocity(Entity target) {
        //set velocity dir toward target with a constant speed
        velocity.set(target.getMiddleLocationVector()).sub(this.getMiddleLocationVector()).setLength(SPEED);
    }

    @Override
    public void render(SpriteBatch batch) {
        if (isAlive()) {
            batch.draw(itemAttributes.getItemTextureRegion(), x, y, getWidth(), getHeight());
        }

    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public static FlyingItem instance = new FlyingItem();

    public static FlyingItem create(Handler handler, Item itemTriggered, Entity target) {
        return new FlyingItem(handler, itemTriggered, target, true);
    }

    public static void trigger(Handler handler, Item itemTriggered, Entity target) {
        instance = new FlyingItem(handler, itemTriggered, target, true);
    }

    public void scroll(float vy) {
        if (isAlive())
            y -= vy;
    }
}
