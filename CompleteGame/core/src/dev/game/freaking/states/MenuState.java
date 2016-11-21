package dev.game.freaking.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dev.game.freaking.main.FreakingHandler;
import dev.game.freaking.model.Assets;
import dev.game.main.GameHandler;
import dev.game.modules.GameModuleManager;
import dev.game.modules.MainGameModule;

public class MenuState extends State {

    private int SCREEN_WIDTH;
    private int SCREEN_HEIGHT;
    private BitmapFont hiraFont, kataFont, mixFont, homeFont;
    private ShapeRenderer shape;
    private TextureRegion bgMenu;


    public MenuState(FreakingHandler freakingHandler, GameStateManager gsm,
                     GameModuleManager cpanel, GameHandler gameHandler) {
        super(freakingHandler, gsm);

        this.cpanel = cpanel;
        this.gameHandler = gameHandler;
        SCREEN_WIDTH = GameHandler.GAME_WIDTH;
        SCREEN_HEIGHT =  GameHandler.GAME_HEIGHT;
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
        parameter.characters = "   Hiragana";
        hiraFont = generator.generateFont(parameter);

        // Katakana Font
        parameter.size = SCREEN_WIDTH / 12;
        parameter.characters = "  Katakana";
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
            if (Gdx.input.getX() >= GameHandler.GAME_WIDTH * 0.2f
                    && Gdx.input.getX() <= GameHandler.GAME_WIDTH * (0.2f + 0.6f)
                    && Gdx.input.getY() >=  GameHandler.GAME_HEIGHT * (1 - 0.5f - 0.1f)
                    && Gdx.input.getY() <=  GameHandler.GAME_HEIGHT * (1 - 0.5f)) {
                PlayState.setAlphabetType(1);
                gsm.set(new PlayState(freakingHandler, gsm, cpanel, gameHandler));
            }

            // Choose Katakana
            if (Gdx.input.getX() >= GameHandler.GAME_WIDTH * 0.2f
                    && Gdx.input.getX() <= GameHandler.GAME_WIDTH * (0.2f + 0.6f)
                    && Gdx.input.getY() >=  GameHandler.GAME_HEIGHT * (1 - 0.385f - 0.1f)
                    && Gdx.input.getY() <=  GameHandler.GAME_HEIGHT * (1 - 0.385f)) {
                PlayState.setAlphabetType(2);
                gsm.set(new PlayState(freakingHandler, gsm, cpanel, gameHandler));
            }

            // Choose Hira + Kata
            if (Gdx.input.getX() >= GameHandler.GAME_WIDTH * 0.2f
                    && Gdx.input.getX() <= GameHandler.GAME_WIDTH * (0.2f + 0.6f)
                    && Gdx.input.getY() >=  GameHandler.GAME_HEIGHT * (1 - 0.27f - 0.1f)
                    && Gdx.input.getY() <=  GameHandler.GAME_HEIGHT * (1 - 0.27f)) {
                PlayState.setAlphabetType(0);
                gsm.set(new PlayState(freakingHandler, gsm, cpanel, gameHandler));
            }

            // Choose Home
            if (Gdx.input.getX() >= GameHandler.GAME_WIDTH * 0.2f
                    && Gdx.input.getX() <= GameHandler.GAME_WIDTH * (0.2f + 0.6f)
                    && Gdx.input.getY() >=  GameHandler.GAME_HEIGHT * (1 - 0.155f - 0.1f)
                    && Gdx.input.getY() <=  GameHandler.GAME_HEIGHT * (1 - 0.155f)) {
                cpanel.set(new MainGameModule(gameHandler, cpanel));
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
        batch.draw(bgMenu, 0, 0, GameHandler.GAME_WIDTH,  GameHandler.GAME_HEIGHT);
        batch.end();
//
//        // -------------SHAPE RENDERER---------------
//        // Transparent button
//        Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND);
//        shape.setProjectionMatrix(ModuleFreaking.camera.combined);
//
//        // Hiragana (shape)
//        shape.begin(ShapeRenderer.ShapeType.Filled);
//        shape.setColor(97, 97, 97, 0.7f);
//        shape.rect(GameHandler.GAME_WIDTH * 0.2f,  GameHandler.GAME_HEIGHT * 0.5f,
//                GameHandler.GAME_WIDTH * 0.6f,  GameHandler.GAME_HEIGHT * 0.1f);
//
//        // Katakana (shape)
//        shape.rect(GameHandler.GAME_WIDTH * 0.2f,  GameHandler.GAME_HEIGHT * 0.385f,
//                GameHandler.GAME_WIDTH * 0.6f,  GameHandler.GAME_HEIGHT * 0.1f);
//
//        // Mix Hira + Kata (shape)
//        shape.rect(GameHandler.GAME_WIDTH * 0.2f,  GameHandler.GAME_HEIGHT * 0.27f,
//                GameHandler.GAME_WIDTH * 0.6f,  GameHandler.GAME_HEIGHT * 0.1f);
//
//        // Home (shape)
//        shape.rect(GameHandler.GAME_WIDTH * 0.2f,  GameHandler.GAME_HEIGHT * 0.155f,
//                GameHandler.GAME_WIDTH * 0.6f,  GameHandler.GAME_HEIGHT * 0.1f);
//        shape.end();


        //------------TEXT RENDERER-----------------
//        batch.begin();
//        hiraFont.setColor(Color.valueOf("#424242"));
//        kataFont.setColor(Color.valueOf("#424242"));
//        mixFont.setColor(Color.valueOf("#424242"));
//        homeFont.setColor(Color.valueOf("#424242"));
//
//        hiraFont.draw(batch, "   Hiragana", GameHandler.GAME_WIDTH * 0.25f,
//                 GameHandler.GAME_HEIGHT * 0.57f);
//        kataFont.draw(batch, "   Katakana", GameHandler.GAME_WIDTH * 0.25f,
//                 GameHandler.GAME_HEIGHT * 0.455f);
//        mixFont.draw(batch, "Hira and Kata", GameHandler.GAME_WIDTH * 0.25f,
//                 GameHandler.GAME_HEIGHT * 0.34f);
//        homeFont.draw(batch, "       Home", GameHandler.GAME_WIDTH * 0.25f,
//                 GameHandler.GAME_HEIGHT * 0.225f);
//
//        batch.end();
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
