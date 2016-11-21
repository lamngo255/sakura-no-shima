package dev.game.doremon.worlds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dev.game.doremon.entities.*;
import dev.game.doremon.gfx.Assets;
import dev.game.doremon.main.DoremonHandler;
import dev.game.main.GameHandler;
import dev.game.modules.ModuleDoremon;

import java.util.ArrayList;


public class World {

    private static final int TIME_TO_SHOW_AN_ITEM = 200;
    private static final int TIME_TO_SHOW_AN_SPRING = 300;

    private static int PLATFORM_POSITION_WITH_SPRING = 0;
    private DoremonHandler doremonHandler;

    // Entities
    private Base base;
    private Player player;
    private Spring spring;
    private PlatformBroken platformBroken;
    private ArrayList<Platform> platforms;
    private TextureRegion background;

    // Game HUB
    private int score;
    private int falling;
    private ShapeRenderer hub;
    BitmapFont font;
    private int countItemTime = 0;
    private int countSpringTime = 0;

    private boolean gameOver;

    public World(DoremonHandler doremonHandler) {
        this.doremonHandler = doremonHandler;
        this.doremonHandler.setWorld(this);
        loadWorld();
        gameOver = false;
        background = Assets.bgGamePlay;
    }

    public void tick() {
        instancesTick();
        countItemTime++;
        countSpringTime++;

        for (Platform platform : platforms) {
            platform.tick();
        }
        platformBroken.tick();
        player.tick();
        player.tick();
        base.tick();
        platformScrolling();
        spring.tick();

        collides();
        if (player.isDead()) {
            gameOver();
        }
    }

    private void instancesTick() {
        FlyingItemManager.instance.tick();
    }

    public void render(SpriteBatch batch) {
        batch.draw(background, 0, 0, GameHandler.GAME_WIDTH, GameHandler.GAME_HEIGHT);
        batch.end();

        Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND);
        hub.setProjectionMatrix(ModuleDoremon.camera.combined);
        hub.begin(ShapeRenderer.ShapeType.Filled);
        hub.setColor(0, 51 / 255, 153 / 255, 0.3f);
        hub.rect(0, 0, GameHandler.GAME_WIDTH, GameHandler.GAME_HEIGHT / 10.3f);
        hub.end();
        batch.begin();


        font.draw(batch, "Score: " + score, GameHandler.GAME_WIDTH / 28.8f,
                GameHandler.GAME_HEIGHT / 43f);
        for (Platform platform : platforms) {
            platform.render(batch);
        }
        platformBroken.render(batch);
        base.render(batch);
        spring.render(batch);
        player.render(batch);
        instancesRender(batch);
    }

    private void instancesRender(SpriteBatch batch) {
        FlyingItemManager.instance.render(batch);
//        FlyingItem.instance.render(batch);
    }

    private void platformScrolling() {
        // create a illusion of scrolling when player reach above half screen
        if (player.getY() >= GameHandler.GAME_HEIGHT / 2 - player.getHeight() / 2) {
            return;
        }
        for (int position = 0; position < platforms.size(); position++) {
            Platform platform = platforms.get(position);

            if (player.getVy() < 0) {
                platform.setY(platform.getY() - player.getVy());
            }

            // reset the platform's position which was below the screen
            if (platform.getY() > GameHandler.GAME_HEIGHT) {
                platform.setType(platform.generateType());
                platform.setY(platform.getY() - GameHandler.GAME_HEIGHT);

                //new
                platform.getItem().getNewRandomType();

                if (countItemTime >= TIME_TO_SHOW_AN_ITEM && position != PLATFORM_POSITION_WITH_SPRING) {
                    countItemTime = 0;
                    platform.showItem();
                }


                if (platform == spring.getAttachedPlatform()) {
                    if (spring.isAppear()) {

                    }
                }
                /*----------------------------spring collide--------------------*/
                if (platform == spring.getAttachedPlatform()) {
                    if (spring.isAppear()) {
                        //spring passed away
                        rearrangeSpring();
                    } else {
                        if (countSpringTime >= TIME_TO_SHOW_AN_SPRING) {
                            //show the spring
                            countSpringTime = 0;
                            spring.setAppear(true);
                        }
                    }
                }
                platform.setFlag(0);
            }
        }

        // moving the base downward
        base.setY(base.getY() - player.getVy());
        score++;
    }

    private void collides() {
        // collides with platforms
        int offset = 45;
        for (Platform platform : platforms) {

            //with Items
            Item item = platform.getItem();
            if (item.gravityRangeReached(player)
                    && item.isCollidable()) {
                System.out.println("Reached " + item);
                //debug

                item.setAlive(false);
                FlyingItemManager.instance.spawn(doremonHandler, item, player);
            }

            // collides with platforms
            if (player.getVy() > 0 && platform.getState() == 0
                    && (player.getX() + offset < platform.getX() + platform.getWidth())
                    && (player.getX() + player.getWidth() - offset > platform.getX())
                    && (player.getY() + player.getHeight() - 10 > platform.getY())
                    && (player.getY() + player.getHeight()
                    < platform.getY() + platform.getHeight())) {

                if (platform.getType() == 2 && platform.getFlag() == 0) {
                    //break platform
                    player.breakPlatform();
                    platform.setFlag(1);
                    platform.setState(1);
                    return;
                } else if (platform.getType() == 3 && platform.getFlag() == 0) {
                    player.jump();
                    platform.setFlag(1);
                    platform.setState(1);
                }
                if (platform.getFlag() == 1) {
                    return;
                }
                player.jump();
            }
        }

        // collides with spring
        if (player.getVy() >= 0
                && spring.isAppear()
                && player.getBounds().overlaps(spring.getBounds())
                ) {
            player.jumpHigh();
            spring.setAppear(false);
            rearrangeSpring();
        }
    }


    public void rearrangeSpring() {
        spring.setAppear(false);
        countSpringTime = 0;
        int newPlatformNum = (int) (Math.random() * platforms.size());
        PLATFORM_POSITION_WITH_SPRING = newPlatformNum;
        spring.setAttachedPlatform(platforms.get(newPlatformNum));
    }

    private void gameOver() {

        destroyInstances();

        for (Platform platform : platforms) {
            platform.setY(platform.getY() - 20);
        }
        base.setY(10000);

        if (player.getY() > GameHandler.GAME_HEIGHT / 2 && falling == 0) {
            player.setY(player.getY() - 20);
            player.setVy(0);
        } else if (player.getY() < GameHandler.GAME_HEIGHT / 2) {
            falling = 1;
        } else if (player.getY() > GameHandler.GAME_HEIGHT) {
            gameOver = true;
        }
        player.setDeadDir();
    }

    private void destroyInstances() {
        FlyingItemManager.instance.refresh();
    }

    private void loadWorld() {
        initInstances();
        base = Base.generate(doremonHandler);
        player = Player.generate(doremonHandler);

        platforms = new ArrayList<Platform>();
        Platform.POSITION = 0;
        for (int i = 0; i < Platform.PLATFORM_COUNT; i++) {
            platforms.add(Platform.generate(doremonHandler));
        }
        platformBroken = PlatformBroken.generate(doremonHandler);

        //changed
        spring = new Spring(doremonHandler, platforms.get(PLATFORM_POSITION_WITH_SPRING));
        hub = new ShapeRenderer();
        score = 0;
        falling = 0;
        player.setDead(false);
        player.setDir("left");

        // FONTS
        FreeTypeFontGenerator generator =
                new FreeTypeFontGenerator(Gdx.files.internal("neuropol.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = GameHandler.GAME_WIDTH / 14;
        parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:";
        parameter.flip = true;
        font = generator.generateFont(parameter);
        font.setColor(Color.WHITE);
        generator.dispose();
    }

    private void initInstances() {
        FlyingItemManager.instance.init();
    }

    public int getCountOf(int TIME_TO_SHOW_AN_ITEM) {
        return countItemTime % TIME_TO_SHOW_AN_ITEM;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(int amount) {
        this.score += amount;
    }

    public Base getBase() {
        return base;
    }

    public PlatformBroken getPlatformBroken() {
        return platformBroken;
    }

    public void setPlatformBroken(PlatformBroken platformBroken) {
        this.platformBroken = platformBroken;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void dispose() {
        base.dispose();
        player.dispose();
        spring.dispose();
        platformBroken.dispose();
        for (Platform platform : platforms) {
            platform.dispose();
        }
        font.dispose();
        FlyingItemManager.instance.dispose();
    }

}
