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
import dev.freaking.main.Handler;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;

public class MenuState extends State {

    private Label freaking, japanese;
    private LabelStyle style;
    private Skin skin;
    private TextButton btnHira, btnKata, btnBoth;
    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private TextureAtlas buttonsAtlas; //** image of buttons **//
    private TextButton.TextButtonStyle btnHiraStyle, btnKataStyle, btnBothStyle;

    private int screenWidth;
    private int screenHeight;

    public MenuState(Handler handler, GameStateManager gsm) {
        super(handler, gsm);
        screenWidth = handler.getWidth();
        screenHeight = handler.getHeight();

        initFontGenerator();
        initButtonStyle();
        createTitle();
        createButton();
        addButtonListener();
    }

    private void initButtonStyle() {
        buttonsAtlas = new TextureAtlas("skin/ButtonMenu.txt"); //** button atlas image **//
        skin = new Skin();
        skin.addRegions(buttonsAtlas);//** skins for on and off **//
    }

    private void initFontGenerator() {
        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/japanese2.ttc"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        style = new LabelStyle();
    }

    private void createTitle() { // Freaking Japanese
        parameter.size = (int) (screenWidth * 0.08);
        style.font = generator.generateFont(parameter);
        freaking = new Label("Freaking", style);
        freaking.setPosition((float) (screenWidth * 0.35), (float) (screenHeight * 0.7));

        parameter.size = (int) (screenWidth * 0.15);
        style.font = generator.generateFont(parameter);
        japanese = new Label("Japanese", style);
        japanese.setPosition((float) (screenWidth * 0.2), (float) (screenHeight * 0.6));
    }

    private void createButton() {
        btnHiraStyle = new TextButton.TextButtonStyle(); //** Button properties **//
        parameter.size = (int) (screenWidth * 0.08);
        btnHiraStyle.up = skin.getDrawable("ButtonUp");
        btnHiraStyle.down = skin.getDrawable("ButtonDown");
        btnHiraStyle.font = generator.generateFont(parameter);
        btnHira = new TextButton("Hiragana", btnHiraStyle);

        btnKataStyle = new TextButton.TextButtonStyle(); //** Button properties **//
        parameter.size = (int) (screenWidth * 0.08);
        btnKataStyle.up = skin.getDrawable("ButtonUp");
        btnKataStyle.down = skin.getDrawable("ButtonDown");
        btnKataStyle.font = generator.generateFont(parameter);
        btnKata = new TextButton("Katakana", btnKataStyle);

        btnBothStyle = new TextButton.TextButtonStyle(); //** Button properties **//
        parameter.size = (int) (screenWidth * 0.07);
        btnBothStyle.up = skin.getDrawable("ButtonUp");
        btnBothStyle.down = skin.getDrawable("ButtonDown");
        btnBothStyle.font = generator.generateFont(parameter);
        btnBoth = new TextButton("Hira and Kata", btnBothStyle);

        btnHira.setBounds((float) (screenWidth * 0.27), (float) (screenHeight * 0.4), (float) (screenWidth * 0.5), (float) (screenHeight * 0.1));
        btnKata.setBounds((float) (screenWidth * 0.27), (float) (screenHeight * 0.29), (float) (screenWidth * 0.5), (float) (screenHeight * 0.1));
        btnBoth.setBounds((float) (screenWidth * 0.27), (float) (screenHeight * 0.18), (float) (screenWidth * 0.5), (float) (screenHeight * 0.1));
    }

    private void addButtonListener() {
        btnHira.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y,
                                int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                PlayState.setNum(1);
                gsm.set(new PlayState(handler, gsm));
                stage.getRoot().getColor().a = 0;
                stage.getRoot().addAction(fadeIn(0.5f));
            }

        });

        btnKata.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y,
                                int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                PlayState.setNum(2);
                gsm.set(new PlayState(handler, gsm));
                stage.getRoot().getColor().a = 0;
                stage.getRoot().addAction(fadeIn(0.5f));
            }

        });

        btnBoth.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y,
                                int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                PlayState.setNum(0);
                gsm.set(new PlayState(handler, gsm));
                stage.getRoot().getColor().a = 0;
                stage.getRoot().addAction(fadeIn(0.5f));
            }
        });
    }

    @Override
    public void update(float dt) {
        Gdx.gl.glClearColor(64 / 255f, 64 / 255f, 64 / 255f, 64 / 255f);
    }

    @Override
    public void draw() {
        stage.addActor(freaking);
        stage.addActor(btnHira);
        stage.addActor(btnKata);
        stage.addActor(btnBoth);
        stage.addActor(japanese);
    }

    @Override
    public void dispose() {
        parameter.characters = "";
        stage.clear();
        skin.dispose();
        buttonsAtlas.dispose();
        btnKataStyle.font.dispose();
        btnBothStyle.font.dispose();
        btnHiraStyle.font.dispose();
        generator.dispose();
    }

}
