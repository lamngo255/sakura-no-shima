package dev.freaking.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.sun.media.jfxmedia.events.PlayerStateEvent;
import dev.freaking.main.Handler;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;

public class GameOverState extends State {

    private Label noiDung, Score, BestScore;
    private LabelStyle style;
    private Skin skin;
    private TextButton btn, btnMainMenu;
    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private TextureAtlas buttonsAtlas; //** image of buttons **//
    TextButton.TextButtonStyle btnTryStyle, btnMainStyle;

    private int screenWidth = Gdx.graphics.getWidth();
    private int screenHight = Gdx.graphics.getHeight();

    public GameOverState(final Handler handler, final GameStateManager gsm) {
        super(handler, gsm);
        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/japanese6.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        buttonsAtlas = new TextureAtlas("skin/ButtonMenu.txt"); //** button atlas image **//
        skin = new Skin();
        skin.addRegions(buttonsAtlas);//** skins for on and off **//
        style = new LabelStyle();

        parameter.size = (int) (screenWidth * 0.084);
        style.font = generator.generateFont(parameter);

        noiDung = new Label("GAME OVER", style);
        noiDung.setPosition((float) (screenWidth * 0.26), (float) (screenHight * 0.6));
        Score = new Label("Score: " + PlayState.ACHIEVED_SCORE, style);
        Score.setPosition((float) (screenWidth * 0.30), (float) (screenHight * 0.54));
        BestScore = new Label("Best: " + PlayState.HIGH_SCORE, style);
        BestScore.setPosition((float) (screenWidth * 0.30), (float) (screenHight * 0.48));

        btnTryStyle = new TextButton.TextButtonStyle(); //** Button properties **//
        parameter.size = (int) (screenWidth * 0.08);
        btnTryStyle.up = skin.getDrawable("ButtonUp");
        btnTryStyle.down = skin.getDrawable("ButtonDown");
        btnTryStyle.font = generator.generateFont(parameter);
        btn = new TextButton("Try Again", btnTryStyle);
        btn.setBounds((float) (screenWidth * 0.27), (float) (screenHight * 0.3), (float) (screenWidth * 0.5), (float) (screenHight * 0.1));

        btnMainStyle = new TextButton.TextButtonStyle(); //** Button properties **//
        parameter.size = (int) (screenWidth * 0.08);
        btnMainStyle.up = skin.getDrawable("ButtonUp");
        btnMainStyle.down = skin.getDrawable("ButtonDown");
        btnMainStyle.font = generator.generateFont(parameter);
        btnMainMenu = new TextButton("Main Menu", btnMainStyle);
        btnMainMenu.setBounds((float) (screenWidth * 0.27), (float) (screenHight * 0.18), (float) (screenWidth * 0.5), (float) (screenHight * 0.1));

        btn.addListener(new ClickListener() {

            @Override
            public void touchUp(InputEvent event, float x, float y,
                                int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                parameter.characters = "";
                stage.clear();
                skin.dispose();
                style.font.dispose();
                buttonsAtlas.dispose();
                generator.dispose();
                gsm.set(new PlayState(handler, gsm));
                stage.getRoot().getColor().a = 0;
                stage.getRoot().addAction(fadeIn(0.5f));
            }

        });
        btnMainMenu.addListener(new ClickListener() {

            @Override
            public void touchUp(InputEvent event, float x, float y,
                                int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                parameter.characters = "";
                stage.clear();
                skin.dispose();
                style.font.dispose();
                btnTryStyle.font.dispose();
                btnMainStyle.font.dispose();
                buttonsAtlas.dispose();
                generator.dispose();
                gsm.set(new MenuState(handler, gsm));
                stage.getRoot().getColor().a = 0;
                stage.getRoot().addAction(fadeIn(0.5f));
            }

        });
    }

    @Override
    public void update(float dt) {
        //Gdx.gl.glClearColor(0, 1, 1, 1);
        Gdx.gl.glClearColor(64 / 255f, 64 / 255f, 64 / 255f, 64 / 255f);
    }

    @Override
    public void draw() {
        stage.addActor(noiDung);
        stage.addActor(Score);
        stage.addActor(BestScore);
        stage.addActor(btn);
        stage.addActor(btnMainMenu);
    }

    @Override
    public void dispose() {

    }

}
