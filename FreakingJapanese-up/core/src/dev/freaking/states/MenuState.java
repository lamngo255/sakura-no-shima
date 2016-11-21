package dev.freaking.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dev.freaking.main.FreakingGame;
import dev.freaking.main.Handler;
import dev.freaking.model.Assets;

public class MenuState extends State {

    private int SCREEN_WIDTH;
    private int SCREEN_HEIGHT;
    private BitmapFont hiraFont, kataFont, mixFont, homeFont;
    private ShapeRenderer shape;

    private TextureRegion bgMenu;


    public MenuState(Handler handler, GameStateManager gsm) {
        super(handler, gsm);
        SCREEN_WIDTH = handler.getWidth();
        SCREEN_HEIGHT = handler.getHeight();

        shape = new ShapeRenderer();
        bgMenu = Assets.bgMenu;
        generateFont();
    }

    private void generateFont() {
        // Freaking Japanese
        FreeTypeFontGenerator generator =
                new FreeTypeFontGenerator(Gdx.files.internal("fonts/OpenSans.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter =
                new FreeTypeFontGenerator.FreeTypeFontParameter();

        // Hiragana Font
        parameter.size = SCREEN_WIDTH / 12;
        parameter.characters = "Hiragana";
        hiraFont = generator.generateFont(parameter);

        // Katakana Font
        parameter.size = SCREEN_WIDTH / 12;
        parameter.characters = "Katakana";
        kataFont = generator.generateFont(parameter);

        // Mix Font
        parameter.size = SCREEN_WIDTH / 13;
        parameter.characters = "HiraandKata";
        mixFont = generator.generateFont(parameter);

        // Home Font
        parameter.size = SCREEN_WIDTH / 13;
        parameter.characters = "Home";
        homeFont = generator.generateFont(parameter);
        generator.dispose();
    }


    private void handleInput() {
        if (Gdx.input.justTouched()) {
            // Choose Hiragana
            if (Gdx.input.getX() >= handler.getWidth() * 0.2f
                    && Gdx.input.getX() <= handler.getWidth() * (0.2f + 0.6f)
                    && Gdx.input.getY() >= handler.getHeight() * (1 - 0.5f - 0.1f)
                    && Gdx.input.getY() <= handler.getHeight() * (1 - 0.5f)) {
                System.out.println("Hiragana");
                PlayState.setAlphabetType(1);
                gsm.set(new PlayState(handler, gsm));
            }

            // Choose Katakana
            if (Gdx.input.getX() >= handler.getWidth() * 0.2f
                    && Gdx.input.getX() <= handler.getWidth() * (0.2f + 0.6f)
                    && Gdx.input.getY() >= handler.getHeight() * (1 - 0.385f - 0.1f)
                    && Gdx.input.getY() <= handler.getHeight() * (1 - 0.385f)) {
                System.out.println("Katakana");
            }

            // Choose Hira + Kata
            if (Gdx.input.getX() >= handler.getWidth() * 0.2f
                    && Gdx.input.getX() <= handler.getWidth() * (0.2f + 0.6f)
                    && Gdx.input.getY() >= handler.getHeight() * (1 - 0.27f - 0.1f)
                    && Gdx.input.getY() <= handler.getHeight() * (1 - 0.27f)) {
                System.out.println("Mix");
            }

            // Choose Home
            if (Gdx.input.getX() >= handler.getWidth() * 0.2f
                    && Gdx.input.getX() <= handler.getWidth() * (0.2f + 0.6f)
                    && Gdx.input.getY() >= handler.getHeight() * (1 - 0.155f - 0.1f)
                    && Gdx.input.getY() <= handler.getHeight() * (1 - 0.155f)) {
                System.out.println("Home");
            }
        }
    }


    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(bgMenu, 0, 0, handler.getWidth(), handler.getHeight());
        batch.end();

        // -------------SHAPE RENDERER---------------
        // Transparent button
        Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND);
        shape.setProjectionMatrix(FreakingGame.camera.combined);

        // Hiragana (shape)
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(97, 97, 97, 0.7f);
        shape.rect(handler.getWidth() * 0.2f, handler.getHeight() * 0.5f,
                handler.getWidth() * 0.6f, handler.getHeight() * 0.1f);

        // Katakana (shape)
        shape.rect(handler.getWidth() * 0.2f, handler.getHeight() * 0.385f,
                handler.getWidth() * 0.6f, handler.getHeight() * 0.1f);

        // Mix Hira + Kata (shape)
        shape.rect(handler.getWidth() * 0.2f, handler.getHeight() * 0.27f,
                handler.getWidth() * 0.6f, handler.getHeight() * 0.1f);

        // Home (shape)
        shape.rect(handler.getWidth() * 0.2f, handler.getHeight() * 0.155f,
                handler.getWidth() * 0.6f, handler.getHeight() * 0.1f);
        shape.end();


        //------------TEXT RENDERER-----------------
        batch.begin();
        hiraFont.setColor(Color.valueOf("#424242"));
        kataFont.setColor(Color.valueOf("#424242"));
        mixFont.setColor(Color.valueOf("#424242"));
        homeFont.setColor(Color.valueOf("#424242"));

        hiraFont.draw(batch, "Hiragana", handler.getWidth() * 0.25f,
                handler.getHeight() * 0.57f);
        kataFont.draw(batch, "Katakana", handler.getWidth() * 0.25f,
                handler.getHeight() * 0.455f);
        mixFont.draw(batch, "Hira and Kata", handler.getWidth() * 0.25f,
                handler.getHeight() * 0.34f);
        homeFont.draw(batch, "Home", handler.getWidth() * 0.25f,
                handler.getHeight() * 0.225f);

        batch.end();
    }

    @Override
    public void dispose() {
        hiraFont.dispose();
        kataFont.dispose();
        mixFont.dispose();
        homeFont.dispose();

        shape.dispose();
    }

}
