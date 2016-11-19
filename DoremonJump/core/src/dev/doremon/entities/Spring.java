package dev.doremon.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import dev.doremon.gfx.Assets;
import dev.doremon.main.Handler;


public class Spring extends Entity {

    public static final int DEFAULT_WIDTH = 80;
    public static final int DEFAULT_HEIGHT = 60;

//    private int state = 0;
    private boolean appear = false;


    private Platform attachedPlatform ;

//    public static Spring generate(Handler handler) {
//        return new Spring(handler, 0, 0);
//    }

    public Spring(Handler handler, Platform attachedPlatform) {
        super(handler, attachedPlatform.getX(), attachedPlatform.getY(), DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.attachedPlatform = attachedPlatform;
        appear = false;
    }

    @Override
    public void tick() {
        updatePosition();
    }

    @Override
    public void render(SpriteBatch batch) {
        if (appear) {
            batch.draw(getCurrentFrame(),
                    (int) this.x, (int) this.y,
                    this.width, this.height);
        }
    }

    public void updatePosition(){
        setX(attachedPlatform.getX() + attachedPlatform.getWidth() / 2 - this.getWidth() / 2);
        setY(attachedPlatform.getY() - attachedPlatform.getHeight());
        updateBounds();
    }




    public Platform getAttachedPlatform() {
        return attachedPlatform;
    }

    public TextureRegion getCurrentFrame() {
        return Assets.spring[
//                this.state
                1];
    }

    public void setAttachedPlatform(Platform attachedPlatform) {
        this.attachedPlatform = attachedPlatform;
    }

//    public int getState() {
//        return state;
//    }
//
//    public void setState(int state) {
//        this.state = state;
//    }

    public boolean isAppear() {
        return appear;
    }

    public void setAppear(boolean appear) {
        this.appear = appear;
    }

    public void dispose() {

    }

}
