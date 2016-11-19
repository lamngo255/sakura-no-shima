package dev.doremon.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import dev.doremon.gfx.Assets;
import dev.doremon.main.Handler;


public class Player extends Entity {
    public static final int PLAYER_WIDTH = 130;
    public static final int PLAYER_HEIGHT = 130;

    public static float GRAVITY = 0.25f;
    public static int JUMP = -14;
    public static int JUMP_HIGH = -50;

    private float vx, vy;
    private boolean isMovingLeft, isMovingRight;
    private String dir;
    private boolean dead = false;
    private float angle = 0;


    public static Player generate(Handler handler) {
        return new Player(handler,
                handler.getWidth() / 2 - PLAYER_WIDTH / 2,
                handler.getHeight() - Player.PLAYER_HEIGHT);
    }

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, PLAYER_WIDTH, PLAYER_HEIGHT);

        // vy > 0: moving downwards
        // vy < 0: moving upwards, JUMP: negative value
        this.dir = "left";
        this.vx = 0;
        this.vy = GRAVITY;
        this.isMovingLeft = false;
        this.isMovingRight = false;

    }

    @Override
    public void tick() {
        controlDirection();
        playerDirection();
        playerMove();
        playerJump();
        playerFallDown();
        if (handler.getWorld().getBase().getY() > handler.getHeight()
                && (this.y + this.height > handler.getHeight())
                && !dead) {
            this.setDead(true);
        }
        updateBounds();
    }


    private void controlDirection() {

        //check horizontal moving direction
        float accelX = Gdx.input.getAccelerometerX();
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            accelX = 5;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            accelX = -5;
        }

        isMovingRight = false;
        isMovingLeft = false;
//        if (Gdx.input.isTouched()) {
//            if (Gdx.input.getX() <= handler.getWidth() / 2) {
//                isMovingLeft = true;
//                isMovingRight = false;
//            } else if (Gdx.input.getX() > handler.getWidth() / 2) {
//                isMovingRight = true;
//                isMovingLeft = false;
//            }
//        }
        if (accelX >= 2) {
            isMovingLeft = true;
            isMovingRight = false;
        } else if (accelX < -2) {
            isMovingLeft = false;
            isMovingRight = true;
        }
    }

    private void playerDirection() {
        if (isMovingLeft) {
            this.dir = "left";
            if (this.vy < JUMP && this.vy > JUMP_HIGH) {
                this.dir = "left_land";
            }
        } else if (isMovingRight) {
            this.dir = "right";
            if (this.vy < JUMP && this.vy > JUMP_HIGH) {
                this.dir = "right_land";
            }
        } else {
            if (this.vy < JUMP && this.vy > JUMP_HIGH) {
                if (this.dir.equals("left")) {
                    this.dir = "left_land";
                } else if (this.dir.equals("right")) {
                    this.dir = "right_land";
                }
            }
        }
    }

    private void playerMove() {
        // Moving back and forth with acceleration
        if (isMovingLeft) {
            this.x += this.vx;
            this.vx -= 0.20;
        } else {
            this.x += this.vx;
            if (this.vx < 0) {
                this.vx += 0.15;
            }
        }

        if (isMovingRight) {
            this.x += this.vx;
            this.vx += 0.20;
        } else {
            this.x += this.vx;
            if (this.vx > 0) {
                this.vx -= 0.15;
            }
        }

        // Move through the wall
        if (this.x > handler.getWidth()) {
            this.x = 0 - this.width;
        } else if (this.x < 0 - this.width) {
            this.x = handler.getWidth();
        }
    }

    public void playerJump() {
        // Jump from the base
        if (this.y + this.height > handler.getWorld().getBase().y) {
            jump();
        }

        // keep the player below half the screen when jumping
        if (this.y >= (handler.getHeight() - this.height) / 2) {
            this.y += this.vy;

            //vy should always be affected by GRAVITY
            this.vy += GRAVITY;
        }
    }

    public void jump() {
        this.vy = JUMP;
    }

    public void jumpHigh() {
        this.vy = JUMP_HIGH;
    }

    private void playerFallDown() {
        if (this.y >= handler.getHeight() / 2 - height / 2) {
            return;
        }
        this.vy += GRAVITY;
        if (this.vy >= 0) {
            this.y += this.vy;
            this.vy += GRAVITY;
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        Sprite sprite = new Sprite(getCurrentFrame());
        if (this.isDead()) {
            angle += 4;
            if (this.width < PLAYER_WIDTH * 2)
                this.width += 3;
            if (this.height < PLAYER_HEIGHT * 2)
                this.height += 3;
        }
        batch.draw(sprite,
                (int) this.x, (int) this.y,
                width / 2, height / 2, this.width, this.height, 1, 1, angle);
    }

    public TextureRegion getCurrentFrame() {
        if (dir.equals("right")) {
            return Assets.player_jump[1];
        } else if (dir.equals("left")) {
            return Assets.player_jump[0];
        } else if (dir.equals("right_land")) {
            return Assets.player_high[1];
        } else if (dir.equals("left_land")) {
            return Assets.player_high[0];
        } else if (dir.equals("left_fall")){
            return Assets.player_fall[0];
        } else if (dir.equals("right_fall")) {
            return Assets.player_fall[1];
        } else {
            return Assets.player_jump[0];
        }
    }

    public float getVx() {
        return vx;
    }

    public void setVx(float vx) {
        this.vx = vx;
    }

    public float getVy() {
        return vy;
    }

    public void setVy(float vy) {
        this.vy = vy;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }


    public void setDeadDir() {
        if (this.dir.equals("right") || this.dir.equals("right_land")) {
            this.dir = "right_fall";
        } else if (this.dir.equals("left") || this.dir.equals("left_land")) {
            this.dir = "left_fall";
        }
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public void dispose() {

    }
}
