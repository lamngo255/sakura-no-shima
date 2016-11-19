package dev.doremon.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dev.doremon.gfx.Assets;
import dev.doremon.main.DoremonJump;
import dev.doremon.main.Handler;


public class GameOverState extends State {
    TextureRegion replayButton;
    TextureRegion mainmenuButton;
    private int score;
    private BitmapFont gameOverFont, scoreFont, bestScoreFont, testFont;
    private TextureRegion doremon, background;
    private ShapeRenderer hub;
    String myText;

    public GameOverState(Handler handler, GameStateManager gsm, int score) {
        super(handler, gsm);
        this.doremon = Assets.player_jump[0];
        this.background = Assets.gameoverbackground;
        initFont();
        replayButton =Assets.playButton[3];
        mainmenuButton = Assets.playButton[4];
        DoremonJump.bestScore = Math.max(DoremonJump.bestScore, score);
        this.score = score;
        this.hub = new ShapeRenderer();
    }

    public void initFont() {
        FreeTypeFontGenerator generator =
                new FreeTypeFontGenerator(Gdx.files.internal("gloriahallelujah.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 60;
        parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:";
        parameter.flip = true;

        // For game over text
        gameOverFont = generator.generateFont(parameter);

        // For best score + score text
        parameter.size = (int)(handler.getHeight()*0.035f);
        scoreFont = generator.generateFont(parameter);
        bestScoreFont = generator.generateFont(parameter);


        gameOverFont.setColor(Color.BROWN);
        scoreFont.setColor(Color.BLACK);

        bestScoreFont.setColor(Color.BLACK);
        generator.dispose();

        testFont = new BitmapFont(Gdx.files.internal("fonts/font.fnt"));
        testFont.getData().setScale(1, -1);
        myText = "さくら";
    }

    public void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new GameState(handler, gsm));
        }
    }
    @Override
    public void tick() {
        handleInput();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();

        batch.draw(background, 0, 0, handler.getWidth(), handler.getHeight());
//        batch.draw(doremon, handler.getWidth() / 2 - doremon.getRegionWidth() / 4,
//                            handler.getHeight() / 2 - doremon.getRegionHeight() / 4,
//                            doremon.getRegionWidth() / 2,
//                            doremon.getRegionHeight() / 2);
//        gameOverFont.draw(batch, "Game Over", 90, 50);
        scoreFont.draw(batch, "Score: " + score,(float)handler.getWidth()*0.23f, handler.getHeight()*0.3125f);
        bestScoreFont.draw(batch, "Best score: " + DoremonJump.bestScore, (float)handler.getWidth()*0.23f, handler.getHeight()*0.37f);
//        testFont.draw(batch, myText, handler.getWidth() / 2, handler.getHeight() / 2);
        batch.draw(replayButton,handler.getWidth()*0.27f,handler.getHeight()*0.5f,handler.getWidth() * 0.47f, handler.getHeight() * 0.09f);
        batch.draw(mainmenuButton,handler.getWidth()*0.27f,handler.getHeight()*0.61f,handler.getWidth() * 0.47f, handler.getHeight() * 0.09f);
        batch.end();

        Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND);
        hub.setProjectionMatrix(DoremonJump.camera.combined);
        hub.begin(ShapeRenderer.ShapeType.Filled);
        hub.setColor(33, 150, 243, 0.3f);
        hub.rect(0, 0, handler.getWidth(), 320);
        hub.end();
    }

    @Override
    public void dispose() {
        gameOverFont.dispose();
        testFont.dispose();
        scoreFont.dispose();
        bestScoreFont.dispose();
    }
}
