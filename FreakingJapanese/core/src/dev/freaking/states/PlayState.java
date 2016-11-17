package dev.freaking.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import dev.freaking.controller.HighScoreData;
import dev.freaking.main.Handler;
import dev.freaking.controller.Music;
import dev.freaking.controller.Slider;
import dev.freaking.model.Alphabet;

import java.util.ArrayList;
import java.util.Random;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;

public class PlayState extends State {
    private static int alphabetType = 1;
    public static int highScore = 0;
    private Slider slider;
    private Label japanLabel, scoreLabel;
    private int currentAlphabet = 0;
    private String trueLT, caseLT1, caseLT2;
    private Random rand;
    private boolean isCorrectAnswer = true;
    private int bgColor;
    private TextButton.TextButtonStyle buttonStyle;
    private TextButton btnCase1, btnCase2;

    private int screenWidth;
    private int screenHeight;

    ArrayList<Alphabet> alphabets;

    public PlayState(Handler handler, GameStateManager gsm) {
        super(handler, gsm);
        screenWidth = handler.getWidth();
        screenHeight = handler.getHeight();

        rand = new Random();
        Alphabet.initAlphabet();
        alphabets = Alphabet.getAlphabet(alphabetType);

        initJapaneseLabel();
        initScoreLabel();
        initButton();
        getContent();

        btnCase1.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                answerCase1();
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        btnCase2.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                answerCase2();
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        bgColor = rand.nextInt(3);
    }

    private void initJapaneseLabel() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/japanese6.ttf"));
        LabelStyle japanLabelStyle = new LabelStyle();
        FreeTypeFontGenerator.FreeTypeFontParameter paramJapan
                = new FreeTypeFontGenerator.FreeTypeFontParameter();
        paramJapan.size = (int) (screenWidth * 0.33);
        paramJapan.characters = Alphabet.getAllCharacter();
        japanLabelStyle.font = generator.generateFont(paramJapan);

        japanLabel = new Label("0", japanLabelStyle);
        japanLabel.setPosition((float) (screenWidth * 0.2), (float) (screenHeight * 0.45));
        generator.dispose();
    }

    private void initScoreLabel() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/japanese6.ttf"));
        LabelStyle scoreLabelStyle = new LabelStyle();
        FreeTypeFontGenerator.FreeTypeFontParameter paramScore
                = new FreeTypeFontGenerator.FreeTypeFontParameter();
        paramScore.size = (int) (screenWidth * 0.084);
        scoreLabelStyle.font = generator.generateFont(paramScore);

        scoreLabel = new Label("0", scoreLabelStyle);
        scoreLabel.setPosition((float) (screenWidth * 0.8), (float) (screenHeight * 0.8));
    }

    private void initButton() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/japanese6.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter paramButton
                = new FreeTypeFontGenerator.FreeTypeFontParameter();
        buttonStyle = new TextButton.TextButtonStyle(); //** Button properties **//
        paramButton.size = (int) (screenWidth * 0.27);
        paramButton.color = Color.RED;
        buttonStyle.font = generator.generateFont(paramButton);


        btnCase1 = new TextButton("", buttonStyle);
        btnCase2 = new TextButton("", buttonStyle);
        btnCase1.setBounds((float) (screenWidth * 0.025), (float) (screenHeight * 0.006), (float) (screenWidth * 0.45), (float) (screenWidth * 0.45));
        btnCase2.setBounds((float) (screenWidth * 0.5 + screenWidth * 0.025), (float) (screenHeight * 0.006), (float) (screenWidth * 0.45), (float) (screenWidth * 0.45));
    }

    @Override
    public void update(float dt) {
        if (slider != null) {
            slider.update();
            if (slider.getIlong() <= 0) {
                slider.setRunning(false);
                isCorrectAnswer = false;
            }
        }
        if (isCorrectAnswer) {
            String text = alphabets.get(currentAlphabet).getJapanese();
            if (text.length() == 1) {
                text = " " + text + " ";
            }
            japanLabel.setText(text);
            btnCase1.setText(caseLT1);
            btnCase2.setText(caseLT2);
        } else {
            stage.clear();
            Music.play("gameover");
            if (highScore > HighScoreData.getScoreData()) {
                HighScoreData.addScore(highScore);
            }
            gsm.set(new GameOverState(handler, gsm));
            stage.getRoot().getColor().a = 0;
            stage.getRoot().addAction(fadeIn(0.5f));
        }
        scoreLabel.setText("" + highScore);
    }

    @Override
    public void draw() {
        if (bgColor == 0) {
            Gdx.gl.glClearColor(64 / 255f, 64 / 255f, 64 / 255f, 64 / 255f);
        }
        if (bgColor == 1) {
            Gdx.gl.glClearColor(1, 0, 0, 0);
        }
        if (bgColor == 2) {
            Gdx.gl.glClearColor(112 / 255f, 77 / 255f, 54 / 255f, 255 / 255f);
        }

        if (slider != null) {
            stage.addActor(slider.getSlider());
        }
        stage.addActor(btnCase1);
        stage.addActor(btnCase2);
        stage.addActor(japanLabel);
        stage.addActor(scoreLabel);
    }

    @Override
    public void dispose() {
        japanLabel.clear();
        scoreLabel.clear();
        buttonStyle.font.dispose();
    }

    //get content of label case
    public void getContent() {
        trueLT = alphabets.get(currentAlphabet).getLatin();
        int index = rand.nextInt(alphabets.size());
        while (alphabets.get(currentAlphabet).equals(alphabets.get(index))) {
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

    public static int getHighScore() {
        return highScore;
    }

    public static void setAlphabetType(int alphabetType) {
        PlayState.alphabetType = alphabetType;
    }

    //if chose case 1
    public void answerCase1() {
        if (slider == null) {
            slider = new Slider();
        }
        if (trueLT.equalsIgnoreCase(caseLT1.trim())) {
            highScore += 1;
            slider.setIlong(screenWidth);
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
            slider = new Slider();
        }

        if (!trueLT.equalsIgnoreCase(caseLT1.trim())) {
            highScore += 1;
            slider.setIlong(screenWidth);
            Music.play("point");
            getContent();
        } else {
            slider = null;
            isCorrectAnswer = false;
        }
    }

    //blend japanese alphabet list
    public void blendAlpList() {
        for (int i = 0; i < 500; i++) {
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

}

