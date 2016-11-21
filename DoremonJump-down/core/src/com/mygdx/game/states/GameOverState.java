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
    TextureRegion replayButton;
    TextureRegion mainmenuButton;
    private int score;
    private BitmapFont gameOver, scoreFont, bestScoreFont;
    private TextureRegion doremon, background;
    private ShapeRenderer hub;
    String myText;

    public GameOverState(Handler handler, GameStateManager gsm, int score) {
        super(handler, gsm);
        this.doremon = Assets.player_jump[0];
        this.background = Assets.bgGameOver;
        initFont();
        replayButton = Assets.playButton[3];
        mainmenuButton = Assets.playButton[4];
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

        parameter.size = (int) (Handler.GAME_HEIGHT * 0.035f);
        scoreFont = generator.generateFont(parameter);
        bestScoreFont = generator.generateFont(parameter);


        gameOver.setColor(Color.BROWN);
        scoreFont.setColor(Color.BLACK);
        bestScoreFont.setColor(Color.BLACK);
        generator.dispose();
    }

    public void handleInput() {
        if (Gdx.input.justTouched()) {
            if (Gdx.input.getX() >= Handler.GAME_WIDTH * 0.27f &&
                    Gdx.input.getX() <= Handler.GAME_WIDTH * 0.69f &&
                    Gdx.input.getY() >= Handler.GAME_HEIGHT * 0.5f &&
                    Gdx.input.getY() <= Handler.GAME_HEIGHT * 0.58f) {
                gsm.set(new GameState(handler, gsm));
            } else if (Gdx.input.getX() >= Handler.GAME_WIDTH * 0.27f &&
                    Gdx.input.getX() <= Handler.GAME_WIDTH * 0.69f &&
                    Gdx.input.getY() >= Handler.GAME_HEIGHT * 0.64f &&
                    Gdx.input.getY() <= Handler.GAME_HEIGHT * 0.69f
                    ) {
                gsm.set(new MenuState(handler, gsm));

            }
        }
    }

    @Override
    public void tick() {
        handleInput();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(background, 0, 0, Handler.GAME_WIDTH, Handler.GAME_HEIGHT);

        scoreFont.draw(batch, "Score: " + score, (float) Handler.GAME_WIDTH * 0.23f, Handler.GAME_HEIGHT * 0.3125f);
        bestScoreFont.draw(batch, "Best score: " + DoremonJump.bestScore, (float) Handler.GAME_WIDTH * 0.23f, Handler.GAME_HEIGHT * 0.37f);
        batch.draw(replayButton, Handler.GAME_WIDTH * 0.27f, Handler.GAME_HEIGHT * 0.5f, Handler.GAME_WIDTH * 0.47f, Handler.GAME_HEIGHT * 0.09f);
        batch.draw(mainmenuButton, Handler.GAME_WIDTH * 0.27f, Handler.GAME_HEIGHT * 0.63f, Handler.GAME_WIDTH * 0.47f, Handler.GAME_HEIGHT * 0.09f);

        batch.end();

        Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND);
        hub.setProjectionMatrix(DoremonJump.camera.combined);
        hub.begin(ShapeRenderer.ShapeType.Filled);
        hub.setColor(33, 150, 243, 0.3f);
        hub.rect(0, 0, Handler.GAME_WIDTH, 320);
        hub.end();
    }

    @Override
    public void dispose() {
        gameOver.dispose();
        scoreFont.dispose();
        bestScoreFont.dispose();
    }
}
