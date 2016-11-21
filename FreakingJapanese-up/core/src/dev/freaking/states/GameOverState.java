package dev.freaking.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import dev.freaking.main.Handler;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;

public class GameOverState extends State {

    private Label noiDung, Score, BestScore;
    private LabelStyle style;
    private Skin skin;

    private int SCREEN_WIDTH = Gdx.graphics.getWidth();
    private int SCREEN_HEIGHT = Gdx.graphics.getHeight();

    private BitmapFont gameOverFont;

    public GameOverState(Handler handler, GameStateManager gsm) {
        super(handler, gsm);

        generateFont();
//        noiDung.setPosition((float) (SCREEN_WIDTH * 0.26), (float) (SCREEN_HEIGHT * 0.6));
//        Score = new Label("Score: " + PlayState.ACHIEVED_SCORE, style);
//        Score.setPosition((float) (SCREEN_WIDTH * 0.30), (float) (SCREEN_HEIGHT * 0.54));
//        BestScore = new Label("Best: " + PlayState.HIGH_SCORE, style);
//        BestScore.setPosition((float) (SCREEN_WIDTH * 0.30), (float) (SCREEN_HEIGHT * 0.48));
//
//        btnTryStyle = new TextButton.TextButtonStyle(); //** Button properties **//
//        parameter.size = (int) (SCREEN_WIDTH * 0.08);
//        btnTryStyle.up = skin.getDrawable("ButtonUp");
//        btnTryStyle.down = skin.getDrawable("ButtonDown");
//        btnTryStyle.font = generator.generateFont(parameter);
//        btn = new TextButton("Try Again", btnTryStyle);
//        btn.setBounds((float) (SCREEN_WIDTH * 0.27), (float) (SCREEN_HEIGHT * 0.3), (float) (SCREEN_WIDTH * 0.5), (float) (SCREEN_HEIGHT * 0.1));
//
//        btnMainStyle = new TextButton.TextButtonStyle(); //** Button properties **//
//        parameter.size = (int) (SCREEN_WIDTH * 0.08);
//        btnMainStyle.up = skin.getDrawable("ButtonUp");
//        btnMainStyle.down = skin.getDrawable("ButtonDown");
//        btnMainStyle.font = generator.generateFont(parameter);
//        btnMainMenu = new TextButton("Main Menu", btnMainStyle);
//        btnMainMenu.setBounds((float) (SCREEN_WIDTH * 0.27), (float) (SCREEN_HEIGHT * 0.18), (float) (SCREEN_WIDTH * 0.5), (float) (SCREEN_HEIGHT * 0.1));
//
//        btn.addListener(new ClickListener() {
//
//            @Override
//            public void touchUp(InputEvent event, float x, float y,
//                                int pointer, int button) {
//                super.touchUp(event, x, y, pointer, button);
//                parameter.characters = "";
//                skin.dispose();
//                style.font.dispose();
//                buttonsAtlas.dispose();
//                generator.dispose();
//                gsm.set(new PlayState(handler, gsm));
//            }
//
//        });
//        btnMainMenu.addListener(new ClickListener() {
//
//            @Override
//            public void touchUp(InputEvent event, float x, float y,
//                                int pointer, int button) {
//                super.touchUp(event, x, y, pointer, button);
//                parameter.characters = "";
//                skin.dispose();
//                style.font.dispose();
//                btnTryStyle.font.dispose();
//                btnMainStyle.font.dispose();
//                buttonsAtlas.dispose();
//                generator.dispose();
//                gsm.set(new MenuState(handler, gsm));
//            }
//
//        });
    }

    private void generateFont() {
        FreeTypeFontGenerator generator =
                new FreeTypeFontGenerator(Gdx.files.internal("fonts/OpenSans-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter =
                new FreeTypeFontGenerator.FreeTypeFontParameter();

        // Game Over Font
        parameter.size = SCREEN_WIDTH / 10;
        parameter.characters = "GameOver";
        gameOverFont = generator.generateFont(parameter);

        generator.dispose();
    }

    private void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new PlayState(handler, gsm));
        }
    }

    @Override
    public void update(float dt) {
        Gdx.gl.glClearColor(64 / 255f, 64 / 255f, 64 / 255f, 64 / 255f);
        handleInput();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        gameOverFont.draw(batch, "Game Over", SCREEN_WIDTH * 0.26f, SCREEN_HEIGHT * 0.6f);
        batch.end();
    }

    @Override
    public void dispose() {
        gameOverFont.dispose();
    }

}
