package dev.game.freaking.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dev.game.freaking.controller.Music;
import dev.game.freaking.controller.Slider;
import dev.game.freaking.main.FreakingHandler;
import dev.game.freaking.model.Alphabet;
import dev.game.main.GameHandler;
import dev.game.modules.GameModuleManager;

import java.util.ArrayList;
import java.util.Random;

public class PlayState extends State {
    private static int ALPHABET_TYPE = 1;
    public static int HIGH_SCORE = 0;
    public static int ACHIEVED_SCORE;
    private Slider slider;
    private String trueLT, caseLT1, caseLT2;
    private boolean isCorrectAnswer = true;
    private int bgColor;
    private int SCREEN_WIDTH;
    private int SCREEN_HEIGHT;
    private int currentAlphabet = 0;

    private BitmapFont japanFont;
    private BitmapFont btnFont1, btnFont2, scoreFont;
    private ShapeRenderer shape;
    private String myText, btnText1, btnText2, scoreText;

    ArrayList<Alphabet> alphabets;

    public PlayState(FreakingHandler freakingHandler, GameStateManager gsm,
                     GameModuleManager cpanel, GameHandler gameHandler) {
        super(freakingHandler, gsm);

        this.cpanel = cpanel;
        this.gameHandler = gameHandler;
        SCREEN_WIDTH = GameHandler.GAME_WIDTH;
        SCREEN_HEIGHT = GameHandler.GAME_HEIGHT;
        ACHIEVED_SCORE = 0;
        init();
        generateFont();

        myText = "さ";
        scoreText = "0";
        btnText1 = "";
        btnText2 = "";
    }

    private void init() {
        Alphabet.initAlphabet();
        alphabets = Alphabet.getAlphabet(ALPHABET_TYPE);
        blendAlpList();
        getContent();

        Random rand = new Random();
        bgColor = rand.nextInt(3);
        shape = new ShapeRenderer();
    }

    public void generateFont() {
        FreeTypeFontGenerator generator =
                new FreeTypeFontGenerator(Gdx.files.internal("fonts/OpenSans-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter =
                new FreeTypeFontGenerator.FreeTypeFontParameter();

        // Japanese Text Font
        japanFont = new BitmapFont(Gdx.files.internal("fonts/font.fnt"), false);
        japanFont.setColor(Color.ORANGE);
        japanFont.getData().setScale(SCREEN_WIDTH / 360f * 0.56f,
                SCREEN_WIDTH / 360f * 0.56f);

        // Button 1 Font
        parameter.size = SCREEN_WIDTH / 6;
        parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        btnFont1 = generator.generateFont(parameter);

        // Button 2 Font
        parameter.size = SCREEN_WIDTH / 6;
        btnFont2 = generator.generateFont(parameter);

        // Score Font
        parameter.size = SCREEN_WIDTH / 10;
        parameter.characters = "0123456789";
        scoreFont = generator.generateFont(parameter);
        generator.dispose();
    }

    private void handleInput() {
        if (Gdx.input.justTouched()) {
            // Choose Left Button
            if (Gdx.input.getX() >= SCREEN_WIDTH * 0.03f
                    && Gdx.input.getX() <= SCREEN_WIDTH * (0.03f + 0.45f)
                    && Gdx.input.getY() >= SCREEN_HEIGHT * (1 - 0.02f - 0.26f)
                    && Gdx.input.getY() <= SCREEN_HEIGHT * (1 - 0.02f)) {
                answerCase1();
            }

            // Choose Right Button
            if (Gdx.input.getX() >= SCREEN_WIDTH * 0.52f
                    && Gdx.input.getX() <= SCREEN_WIDTH * (0.52f + 0.45f)
                    && Gdx.input.getY() >= SCREEN_HEIGHT * (1 - 0.02f - 0.26f)
                    && Gdx.input.getY() <= SCREEN_HEIGHT * (1 - 0.02f)) {
                answerCase2();
            }
        }
    }

    private String getTextCenter(String text) {
        if (text.length() == 1) {
            return " " + text + " ";
        }
        return text;
    }

    @Override
    public void tick() {
        handleInput();
        if (slider != null) {
            slider.update();
            if (slider.getX() <= 0 - SCREEN_WIDTH) {
                slider.setRunning(false);
                isCorrectAnswer = false;
            }
        }
        if (isCorrectAnswer) {
            String text = alphabets.get(currentAlphabet).getJapanese();
            myText = getTextCenter(text);
            btnText1 = getTextCenter(caseLT1);
            btnText2 = getTextCenter(caseLT2);
        } else {
            Music.play("gameover");
            HIGH_SCORE = Math.max(HIGH_SCORE, ACHIEVED_SCORE);
            gsm.set(new GameOverState(freakingHandler, gsm,ACHIEVED_SCORE, cpanel, gameHandler));
        }
        scoreText = String.valueOf(ACHIEVED_SCORE);
    }

    @Override
    public void render(SpriteBatch batch) {
        if (bgColor == 0) {
            Gdx.gl.glClearColor(64 / 255f, 64 / 255f, 64 / 255f, 64 / 255f);
        }
        if (bgColor == 1) {
            Gdx.gl.glClearColor(109 / 255f, 135 / 255f, 100 / 255f, 64 / 255f);
        }
        if (bgColor == 2) {
            Gdx.gl.glClearColor(112 / 255f, 77 / 255f, 54 / 255f, 255 / 255f);
        }

        //-------------------SHAPE RENDERER------------------
        shape.begin(ShapeRenderer.ShapeType.Filled);
        // Button 1 (shape)
        shape.setColor(Color.valueOf("#f9f6f2"));
        shape.rect(SCREEN_WIDTH * 0.03f, SCREEN_HEIGHT * 0.02f,
                SCREEN_WIDTH * 0.45f, SCREEN_HEIGHT * 0.26f);

        // Button 2 (shape)
        shape.rect(SCREEN_WIDTH * 0.52f, SCREEN_HEIGHT * 0.02f,
                SCREEN_WIDTH * 0.45f, SCREEN_HEIGHT * 0.26f);
        shape.end();


        //-------------------TEXT RENDERER------------------
        batch.begin();
        japanFont.draw(batch, myText, Gdx.graphics.getWidth() * 0.17f,
                Gdx.graphics.getHeight() * 0.7f);
        btnFont1.setColor(Color.valueOf("#424242"));
        btnFont1.draw(batch, btnText1, SCREEN_WIDTH * 0.09f,
                SCREEN_HEIGHT * 0.2f);
        btnFont2.setColor(Color.valueOf("#424242"));
        btnFont2.draw(batch, btnText2, SCREEN_WIDTH * 0.58f,
                SCREEN_HEIGHT * 0.2f);
        scoreFont.setColor(Color.valueOf("#f9f6f2"));
        scoreFont.draw(batch, scoreText, SCREEN_WIDTH * 0.8f,
                                    SCREEN_HEIGHT * 0.8f);
        batch.end();

        if (slider != null) {
            slider.render(batch);
        }
    }

    @Override
    public void dispose() {
        japanFont.dispose();
        btnFont1.dispose();
        btnFont2.dispose();
        scoreFont.dispose();
    }

    //get content of label case
    public void getContent() {
        Random rand = new Random();
        trueLT = alphabets.get(currentAlphabet).getLatin();
        int index = rand.nextInt(alphabets.size());
        while (currentAlphabet == index) {
            index = rand.nextInt(alphabets.size());
        }
        String falseLT = alphabets.get(index).getLatin();
        int random = rand.nextInt(10) + 1;
        if (random % 2 == 0) {
            caseLT1 = trueLT;
            caseLT2 = falseLT;
        } else {
            caseLT1 = falseLT;
            caseLT2 = trueLT;
        }
    }

    public static void setAlphabetType(int alphabetType) {
        PlayState.ALPHABET_TYPE = alphabetType;
    }

    //if chose case 1
    public void answerCase1() {
        if (slider == null) {
            slider = new Slider(freakingHandler);
        }
        if (trueLT.equalsIgnoreCase(caseLT1.trim())) {
            ACHIEVED_SCORE += 1;
            updateCurrentAlp();
            slider.setX(0);
            Music.play("point");
            getContent();
        } else {
            slider = null;
            isCorrectAnswer = false;
        }
    }

    //if chose case 2
    public void answerCase2() {
        if (slider == null) {
            slider = new Slider(freakingHandler);
        }

        if (!trueLT.equalsIgnoreCase(caseLT1.trim())) {
            ACHIEVED_SCORE += 1;
            updateCurrentAlp();
            slider.setX(0);
            Music.play("point");
            getContent();
        } else {
            slider = null;
            isCorrectAnswer = false;
        }
    }

    //blend japanese alphabet list
    public void blendAlpList() {
        Random rand = new Random();
        for (int i = 0; i < 50; i++) {
            int j = rand.nextInt(alphabets.size());
            int k = rand.nextInt(alphabets.size());
            while (j == k) {
                k = rand.nextInt(alphabets.size());
            }
            Alphabet temp = alphabets.get(j);
            alphabets.set(j, alphabets.get(k));
            alphabets.set(k, temp);
        }
    }

    public void updateCurrentAlp() {
        currentAlphabet++;
        if (currentAlphabet == alphabets.size()) {
            currentAlphabet = 0;
            blendAlpList();
        }
    }
}
