package dev.game.doremon.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import dev.game.doremon.gfx.Assets;
import dev.game.doremon.main.DoremonHandler;
import dev.game.main.GameHandler;


public class Platform extends Entity {
    public static final int PLATFORM_WIDTH = GameHandler.GAME_WIDTH / 4;
    public static final int PLATFORM_HEIGHT = GameHandler.GAME_HEIGHT / 24;
    public static final int PLATFORM_COUNT = 7;
    public static float POSITION = 0;
    public static boolean BROKEN = false;

    private int type;
    private int flag; // mark when cracked up
    private int state;
    private float vx;

    private Item item;

    public static Platform generate(DoremonHandler doremonHandler) {
        return new Platform(doremonHandler,
                (float) Math.random() * (GameHandler.GAME_WIDTH - PLATFORM_WIDTH),
                Platform.POSITION);
    }

    public Platform(DoremonHandler doremonHandler, float x, float y) {
        super(doremonHandler, x, y, PLATFORM_WIDTH, PLATFORM_HEIGHT);

        POSITION += GameHandler.GAME_HEIGHT / PLATFORM_COUNT;
        this.vx = GameHandler.GAME_WIDTH / 170;
        this.type = (int) (Math.random() * 2);
        this.item = Item.createRandomItemForPlatfrom(doremonHandler, this);
    }

    @Override
    public void tick() {
        item.tick();
        moveAround();
        crackUp();

        item.updateWithPlatform(this);
    }

    @Override
    public void setY(float y) {
        super.setY(y);
        item.updateWithPlatform(this);
    }

    @Override
    public void render(SpriteBatch batch) {
        if (getCurrentFrame() == null) {
            return;
        }

        Sprite sprite = new Sprite(getCurrentFrame());
        batch.draw(sprite,
                this.x, this.y,
                this.width, this.height);
        item.render(batch);
    }

    private void moveAround() {
        if (this.type == 1 || this.type == 2) {
            if (x < 0 || x + width > GameHandler.GAME_WIDTH) {
                this.vx *= -1;
            }
            this.x += this.vx;
        }
    }

    private void crackUp() {
        PlatformBroken subs = doremonHandler.getWorld().getPlatformBroken();
        if (this.flag == 1 && !subs.isAppear() && this.state == 1) {
            subs.setX(this.x);
            subs.setY(this.y);
            subs.setAppear(true);
            this.state = 0;
        }
    }

    public int generateType() {
        int score = doremonHandler.getWorld().getScore();
        int[] types;

        if (score >= 5000) {
            types = new int[]{1, 2, 2, 2, 3, 3, 3, 3};
        } else if (score >= 2000 && score < 5000) {
            types = new int[]{1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3};
        } else if (score >= 1000 && score < 2000) {
            types = new int[]{1, 1, 1, 2, 2, 2, 2};
        } else if (score >= 500 && score < 1000) {
            types = new int[]{0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2};
        } else if (score >= 50 && score < 500) {
            types = new int[]{0, 0, 0, 1, 1, 1};
        } else {
            types = new int[]{0};
        }

        this.type = types[(int) (Math.random() * types.length)];
        if (!BROKEN && this.type == 2) {
            BROKEN = true;
        } else if (BROKEN && this.type == 2) {
            BROKEN = false;
            this.type = 0;
        }
        return this.type;
    }

    public void showItem(){
        item.setAlive(true);
    }

    public TextureRegion getCurrentFrame() {
        switch (this.type) {
            case 0:
                return Assets.platform[0];
            case 1:
                return Assets.platform[1];
            case 2:
                if (this.flag == 0) {
                    return Assets.platform[2];
                } else {
                    return null;
                }
            case 3:
                if (this.flag == 0) {
                    return Assets.platform[3];
                } else {
                    return null;
                }
            default:
                return Assets.platform[0];
        }
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }

    public Item getItem() {
        return item;
    }

    public void dispose() {

    }
}
