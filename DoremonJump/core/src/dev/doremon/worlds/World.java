package dev.doremon.worlds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dev.doremon.entities.Base;
import dev.doremon.entities.FlyingItem;
import dev.doremon.entities.Item;
import dev.doremon.entities.Platform;
import dev.doremon.entities.PlatformBroken;
import dev.doremon.entities.Player;
import dev.doremon.entities.Spring;
import dev.doremon.gfx.Assets;
import dev.doremon.main.DoremonJump;
import dev.doremon.main.Handler;

import java.util.ArrayList;


public class World {

    private static final int TIME_TO_SHOW_AN_ITEM = 100;
    private static final int TIME_TO_SHOW_AN_SPRING = 300;

    private static final int DEFAULT_PLATFORM_POSITION_WITH_SPRING = 0;
    private Handler handler;

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

    public World(Handler handler) {
        this.handler = handler;
        this.handler.setWorld(this);
        loadWorld();
        gameOver = false;

        background = Assets.background;
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
        base.tick();
        platformScrolling();

//        createSpring();
        spring.tick();

        collides();
        if (player.isDead()) {
            gameOver();
        }
    }

    private void instancesTick() {
        FlyingItem.instance.tick();
    }

    private void showAnRandomItem() {
        //spawn an item on a random platform which is not having a spring
        int randomPosition;
        do {

            randomPosition = (int) (Math.random() * platforms.size());
        }
        while (randomPosition == DEFAULT_PLATFORM_POSITION_WITH_SPRING);
        platforms.get(randomPosition).getItem().setAlive(true);
    }

    public void render(SpriteBatch batch) {
        batch.draw(background, 0, 0, handler.getWidth(), handler.getHeight());
        batch.end();

        Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND);
        hub.setProjectionMatrix(DoremonJump.camera.combined);
        hub.begin(ShapeRenderer.ShapeType.Filled);
        hub.setColor(0, 51 / 255, 153 / 255, 0.3f);
        hub.rect(0, 0, handler.getWidth(), 105);
        hub.end();

        batch.begin();


        font.draw(batch, "Score: " + score, 25, 45);

//        font.draw(batch, "Spring state: " + spring.getState(), 25, 45);


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
        FlyingItem.instance.render(batch);
    }

    private void createSpring() {
        // attach spring to the platform of index 0
        Platform p = platforms.get(DEFAULT_PLATFORM_POSITION_WITH_SPRING);
        if (p.getType() == 1 || p.getType() == 3 || p.getType() == 2) {
            spring.setX(p.getX() + p.getWidth() / 2 - spring.getWidth() / 2);
            spring.setY(p.getY() - p.getHeight());
            spring.setAppear(true);

            if (spring.getY() > handler.getHeight() / 1.1 || p.getFlag() == 1) {
//                spring.setState(0);
                spring.setAppear(false);
            }
        } else {
            // if not the specified type, remove the spring

            spring.setX(0 - spring.getX());
            spring.setY(0 - spring.getY());
        }
    }

    private void platformScrolling() {
        // create a illusion of scrolling when player reach above half screen
        if (player.getY() >= handler.getHeight() / 2 - player.getHeight() / 2) {
            return;
        }
        for (Platform platform : platforms) {

            // move the platform downward while player get higher


            if (player.getVy() < 0) {
                platform.setY(platform.getY() - player.getVy());

                //newww
                //scroll the FlyingItems too
//                FlyingItem.instance.scroll(player.getVy());

                //Its a new feature now

            }

            // reset the platform's position which was below the screen
            if (platform.getY() > handler.getHeight()) {
                platform.setType(platform.generateType());
                platform.setY(platform.getY() - handler.getHeight());

                //new
                platform.getItem().getNewRandomType();

                if (countItemTime >= TIME_TO_SHOW_AN_ITEM) {
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
                FlyingItem.trigger(handler, item, player);
            }

//            if (player.getBounds().overlaps(item.getBounds())
//                    && item.isAlive()) {
//                System.out.println("Got " + item);
//                item.setAlive(false);
//                FlyingItem.trigger(handler,item,player);
//            }

            // overlap with circle range


            // collides with platforms
            if (player.getVy() > 0 && platform.getState() == 0
                    && (player.getX() + offset < platform.getX() + platform.getWidth())
                    && (player.getX() + player.getWidth() - offset > platform.getX())
                    && (player.getY() + player.getHeight() - 10 > platform.getY())
                    && (player.getY() + player.getHeight()
                    < platform.getY() + platform.getHeight())) {

                if (platform.getType() == 2 && platform.getFlag() == 0) {
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
//                && spring.getState() == 0

//                && (player.getX() + offset < spring.getX() + spring.getWidth())
//                && (player.getX() + player.getWidth() - offset > spring.getX())
//                && (player.getY() + player.getHeight() > spring.getY())
//                && (player.getY() + player.getHeight()
//                < spring.getY() + spring.getHeight())
                ) {
//            spring.setState(1);
            player.jumpHigh();
            spring.setAppear(false);
            rearrangeSpring();
        }
    }


    public void rearrangeSpring() {
        spring.setAppear(false);
        countSpringTime = 0;
        int newPlatformNum = (int) (Math.random() * platforms.size());
        spring.setAttachedPlatform(platforms.get(newPlatformNum));
    }

    private void gameOver() {

        destroyInstances();

        for (Platform platform : platforms) {
            platform.setY(platform.getY() - 20);
        }
        base.setY(10000);

        if (player.getY() > handler.getHeight() / 2 && falling == 0) {
            player.setY(player.getY() - 20);
            player.setVy(0);
        } else if (player.getY() < handler.getHeight() / 2) {
            falling = 1;
        } else if (player.getY() > handler.getHeight()) {
            gameOver = true;
        }
        player.setDeadDir();
    }

    private void destroyInstances() {
        FlyingItem.instance.setAlive(false);
    }

    private void loadWorld() {
        base = Base.generate(handler);
        player = Player.generate(handler);

        platforms = new ArrayList<Platform>();
        Platform.POSITION = 0;
        for (int i = 0; i < Platform.PLATFORM_COUNT; i++) {
            platforms.add(Platform.generate(handler));
        }
        platformBroken = PlatformBroken.generate(handler);

        //changed
//        spring = Spring.generate(handler);

        spring = new Spring(handler, platforms.get(DEFAULT_PLATFORM_POSITION_WITH_SPRING));


        hub = new ShapeRenderer();
        score = 0;
        falling = 0;
        player.setDead(false);
        player.setDir("left");

        // FONTS
        FreeTypeFontGenerator generator =
                new FreeTypeFontGenerator(Gdx.files.internal("neuropol.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 40;
        parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:";
        parameter.flip = true;
        font = generator.generateFont(parameter);
        font.setColor(Color.WHITE);
        generator.dispose();
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
    }

}
