package dev.game.doremon.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import dev.game.doremon.gfx.Assets;
import dev.game.doremon.main.DoremonHandler;
import dev.game.main.GameHandler;


public class Spring extends Entity {

    public static final int DEFAULT_WIDTH = GameHandler.GAME_WIDTH / 9;
    public static final int DEFAULT_HEIGHT = GameHandler.GAME_HEIGHT / 18;
    private boolean appear = false;
    private Platform attachedPlatform ;

    public Spring(DoremonHandler doremonHandler, Platform attachedPlatform) {
        super(doremonHandler, attachedPlatform.getX(), attachedPlatform.getY(), DEFAULT_WIDTH, DEFAULT_HEIGHT);
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
                     this.x,  this.y,
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
        return Assets.spring[1];
    }

    public void setAttachedPlatform(Platform attachedPlatform) {
        this.attachedPlatform = attachedPlatform;
    }
    public boolean isAppear() {
        return appear;
    }
    public void setAppear(boolean appear) {
        this.appear = appear;
    }
    public void dispose() {

    }
}
