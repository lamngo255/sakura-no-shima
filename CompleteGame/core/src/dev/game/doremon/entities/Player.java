package dev.game.doremon.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import dev.game.doremon.gfx.Assets;
import dev.game.doremon.main.DoremonHandler;


public class Player extends Entity {
    public static final int PLAYER_WIDTH = (int) (DoremonHandler.GAME_WIDTH / 5.3);
    public static final int PLAYER_HEIGHT = (int) (DoremonHandler.GAME_HEIGHT / 8.3);
    private static final float HORIZONTAL_VELOCITY_LIMIT = 3;
    private static final float HORIZONTAL_ACCELERATION = 0.1f;
    private static final float HORIZONTAL_BRAKE_ACCELEBRATION = 0.075f;

    public static float GRAVITY = DoremonHandler.GAME_HEIGHT / 7200f;
    public static int JUMP = -(int) (DoremonHandler.GAME_HEIGHT / 67.5);
    public static int JUMP_HIGH = -(int) (DoremonHandler.GAME_HEIGHT / 20.56);

    private float vx, vy;
    private boolean isMovingLeft, isMovingRight;
    private String dir;
    private boolean dead = false;
    private float angle = 0;

    private Sound jumpSound;
    private Sound flyHighSound;
    private Sound deadSound;
    private Sound breakSound;


    public static Player generate(DoremonHandler doremonHandler) {
        return new Player(doremonHandler,
                DoremonHandler.GAME_WIDTH / 2 - PLAYER_WIDTH / 2,
                DoremonHandler.GAME_HEIGHT - Player.PLAYER_HEIGHT);
    }

    public Player(DoremonHandler doremonHandler, float x, float y) {
        super(doremonHandler, x, y, PLAYER_WIDTH, PLAYER_HEIGHT);

        // vy > 0: moving downwards
        // vy < 0: moving upwards, JUMP: negative value
        this.dir = "left";
        this.vx = 0;
        this.vy = GRAVITY;
        this.isMovingLeft = false;
        this.isMovingRight = false;

        jumpSound = Gdx.audio.newSound(Gdx.files.internal("Jump5.wav"));
        flyHighSound = Gdx.audio.newSound(Gdx.files.internal("Powerup5.wav"));
        deadSound = Gdx.audio.newSound(Gdx.files.internal("Man Screaming Sound Effect.wav"));
        breakSound = Gdx.audio.newSound(Gdx.files.internal("platform_break.wav"));

    }

    @Override
    public void tick() {
        controlDirection();
        playerDirection();
        playerMove();
        playerJump();
        playerFallDown();
        if (doremonHandler.getWorld().getBase().getY() > DoremonHandler.GAME_HEIGHT
                && (this.y + this.height > DoremonHandler.GAME_HEIGHT)
                && !dead) {
            this.setDead(true);
            deadSound.play(0.5f);
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
//            if (Gdx.input.getX() <= DoremonHandler.GAME_WIDTH / 2) {
//                isMovingLeft = true;
//                isMovingRight = false;
//            } else if (Gdx.input.getX() > DoremonHandler.GAME_WIDTH / 2) {
//                isMovingRight = true;
//                isMovingLeft = false;
//            }
//        }
        if (accelX >= 1) {
            isMovingLeft = true;
            isMovingRight = false;
        } else if (accelX < -1) {
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

        if (Math.abs(vx) > HORIZONTAL_VELOCITY_LIMIT) {
            if (vx < 0) {
                vx = HORIZONTAL_VELOCITY_LIMIT * -1;
            } else {
                vx = HORIZONTAL_VELOCITY_LIMIT;
            }

        }
        // Moving back and forth with acceleration
        if (isMovingLeft) {
            this.x += this.vx;
            this.vx -= HORIZONTAL_ACCELERATION;
        } else {
            this.x += this.vx;
            if (this.vx < 0) {
                this.vx += HORIZONTAL_BRAKE_ACCELEBRATION;
            }
        }

        if (isMovingRight) {
            this.x += this.vx;
            this.vx += HORIZONTAL_ACCELERATION;
        } else {
            this.x += this.vx;
            if (this.vx > 0) {
                this.vx -= HORIZONTAL_BRAKE_ACCELEBRATION;
            }
        }

        // Move through the wall
        if (this.x > DoremonHandler.GAME_WIDTH) {
            this.x = 0 - this.width;
        } else if (this.x < 0 - this.width) {
            this.x = DoremonHandler.GAME_WIDTH;
        }

    }

    public void playerJump() {
        // Jump from the base
        if (this.y + this.height > doremonHandler.getWorld().getBase().y) {
            jump();
        }

        // keep the player below half the screen when jumping
        if (this.y >= (DoremonHandler.GAME_HEIGHT - this.height) / 2) {
            this.y += this.vy;

            //vy should always be affected by GRAVITY
            this.vy += GRAVITY;
        }
    }

    public void jump() {
        jumpSound.play(0.2f);
        this.vy = JUMP;
    }

    public void jumpHigh() {
        flyHighSound.play();
        this.vy = JUMP_HIGH;
    }

    private void playerFallDown() {
        if (this.y >= DoremonHandler.GAME_HEIGHT / 2 - height / 2) {
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
            if (this.width < PLAYER_WIDTH * 3)
                this.width += DoremonHandler.GAME_WIDTH / 180;
            if (this.height < PLAYER_HEIGHT * 3)
                this.height += DoremonHandler.GAME_HEIGHT / 360;
        }
        batch.draw(sprite,
                this.x, this.y,
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
        } else if (dir.equals("left_fall")) {
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
        jumpSound.dispose();
        flyHighSound.dispose();
        deadSound.dispose();
        breakSound.dispose();
    }

    public void breakPlatform() {
        breakSound.play();
    }
}
