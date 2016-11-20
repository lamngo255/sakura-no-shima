package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.gfx.Assets;
import com.mygdx.game.main.DoremonJump;
import com.mygdx.game.main.Handler;


public class GameOverState extends State {
    private int score;
    private BitmapFont gameOver, scoreFont, bestScoreFont;
    private TextureRegion doremon, background;
    private ShapeRenderer hub;

    public GameOverState(Handler handler, GameStateManager gsm, int score) {
        super(handler, gsm);
        this.doremon = Assets.player_jump[0];
        this.background = Assets.background;
        initFont();

        DoremonJump.bestScore = Math.max(DoremonJump.bestScore, score);
        this.score = score;
        this.hub = new ShapeRenderer();
    }

    public void initFont() {
        FreeTypeFontGenerator generator =
                new FreeTypeFontGenerator(Gdx.files.internal("gloriahallelujah.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 80;
        parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:";
        parameter.flip = true;
        gameOver = generator.generateFont(parameter);

        parameter.size = 50;
        scoreFont = generator.generateFont(parameter);
        bestScoreFont = generator.generateFont(parameter);


        gameOver.setColor(Color.BROWN);
        scoreFont.setColor(Color.BLACK);
        bestScoreFont.setColor(Color.BLACK);
        generator.dispose();
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
        batch.draw(doremon, handler.getWidth() / 2 - doremon.getRegionWidth() / 4,
                            handler.getHeight() / 2 - doremon.getRegionHeight() / 4,
                            doremon.getRegionWidth() / 2,
                            doremon.getRegionHeight() / 2);
        gameOver.draw(batch, "Game Over", 90, 50);
        scoreFont.draw(batch, "Score: " + score, 200, 180);
        bestScoreFont.draw(batch, "Best score: " + DoremonJump.bestScore, 100, 240);
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
        gameOver.dispose();
    }
}
