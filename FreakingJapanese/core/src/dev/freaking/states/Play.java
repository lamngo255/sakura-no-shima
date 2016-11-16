package dev.freaking.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import dev.freaking.states.GameState;
import dev.freaking.states.GameStateManager;
import dev.freaking.controller.GetAlphabet;
import dev.freaking.controller.HightScoreData;
import dev.freaking.controller.Music;
import dev.freaking.controller.Slider;
import dev.freaking.model.Alphabet;

import java.util.ArrayList;
import java.util.Random;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;

public class Play extends GameState {

    private Slider slider;
    private LabelStyle styleCont,styleScore;
    private Label jpnContent, Score;//label Japanese and score display
    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameterCont,parameterScore,parameterCase;
    private int currentAlp=0;

    private String trueLT, caseLT1, caseLT2;

    private Random r;
    private boolean nd;
    private int rd;
    public static int HighScore;

    private TextureAtlas buttonsAtlas; //** image of buttons **//
    TextButton.TextButtonStyle btnCase1Style;
    private Skin btnSkin;
    private TextButton btnCase1,btnCase2;

    private int screenWidth = Gdx.graphics.getWidth();
    private int screenHight = Gdx.graphics.getHeight();
    private GetAlphabet getAlphabet = new GetAlphabet();
    private static int num=1;

    ArrayList<Alphabet> alphabets = null;

    public Play(GameStateManager gsm) {
        super(gsm);
        HighScore = 0;
        nd = true;
        r = new Random();
        //   System.out.println("num: "+num);
        alphabets = getAlphabet.getAllAlphabet(num);

        styleCont = new LabelStyle();
        styleScore = new LabelStyle();

        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/japanese4.ttf"));
        parameterCont = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameterScore = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameterCase = new FreeTypeFontGenerator.FreeTypeFontParameter();

        buttonsAtlas = new TextureAtlas("skin/ButtonSkin.txt"); //** button atlas image **//
        btnSkin = new Skin();
        btnSkin.addRegions(buttonsAtlas);//** skins for on and off **//

//init label japanese
        parameterCont.size = (int)(screenWidth*0.33);
        String character = "あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめもやゆよらりるれろわをんがぎぐげござじずぜぞだぢづでどばびぶべぼぱぴぷぺぽきゃきゅきょしゃしゅしょちゃちゅちょにゃにょにゅひゃひゅひょみゃみゅみょりゃりゅりょぎゃぎゅぎょじゃじゅじょびゃびゅびょぴゃぴゅぴょ";
        character+="アイウエオカキクケコサシスセソタチツテトナニヌネノハヒフヘホマミムメモヤユヨラリルレロワヲンガギグゲゴザジズゼゾダヂヅデドバビブベボパピプペポキャキュキョシャシュショチャチュチョニャニュニョミャミュミョヒャヒュヒョリャリョリュギャギュギョジャジュジョビャビュビョピャプオピュ";
        //   String character="そ";
//        for(Alphabet alp:alphabets){
//                character += alp.getJapansese();
//        }
        blendAlpList();
        System.out.println("character: "+character);
        parameterCont.characters=character;
        styleCont.font = generator.generateFont(parameterCont);

        jpnContent = new Label("0", styleCont);
        jpnContent.setPosition((float) (screenWidth * 0.2), (float) (screenHight * 0.45));

//init label score
        parameterScore.size=(int)(screenWidth*0.084);;
        styleScore.font=generator.generateFont(parameterScore);
        Score = new Label("0", styleScore);
        Score.setPosition((float) (screenWidth * 0.8), (float) (screenHight * 0.8));



//init label background image
//        case2IMG = new Image(new Texture(Gdx.files.internal("data/button.png")));
//        case2IMG.setWidth((float) (screenWidth * 0.45));
//        case2IMG.setHeight((float) (screenWidth * 0.45));
//        case2IMG.setX((float) (screenWidth * 0.5 + screenWidth * 0.025));
//        case2IMG.setY((float) (screenHight * 0.006));
//
//        case1IMG = new Image(new Texture(Gdx.files.internal("data/button.png")));
//        case1IMG.setWidth((float) (screenWidth * 0.45));
//        case1IMG.setHeight((float) (screenWidth * 0.45));
//        case1IMG.setX((float) (screenWidth * 0.025));
//        case1IMG.setY((float) (screenHight * 0.006));

        //button
        String character1 = "zxcvbnmasdfghjklqwertyuiop";
        for(Alphabet alp:alphabets){
            character1 += alp.getLatin();
        }

        btnCase1Style = new TextButton.TextButtonStyle(); //** Button properties **//
        btnCase1Style.up = btnSkin.getDrawable("ButtonUp");
        btnCase1Style.down = btnSkin.getDrawable("ButtonDown");
        parameterCase.characters=character1;
        parameterCase.size=(int)(screenWidth*0.27);
        parameterCase.color=Color.RED;
        btnCase1Style.font = generator.generateFont(parameterCase);
        btnCase1 = new TextButton("", btnCase1Style);

        btnCase2 = new TextButton("", btnCase1Style);

        btnCase1.setBounds((float) (screenWidth * 0.025), (float) (screenHight * 0.006), (float) (screenWidth * 0.45), (float) (screenWidth * 0.45));
        btnCase2.setBounds((float) (screenWidth * 0.5 + screenWidth * 0.025), (float) (screenHight * 0.006), (float) (screenWidth * 0.45), (float) (screenWidth * 0.45));
        getContent();
        btnCase1.addListener(new ClickListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                answareCase1();
                return super.touchDown(event, x, y, pointer, button);
            }

        });
        btnCase2.addListener(new ClickListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                answareCase2();
                return super.touchDown(event, x, y, pointer, button);
            }

        });
        rd = r.nextInt(3);
    }

    @Override
    public void update(float dt) {
        if (slider != null) {
            slider.update();
            if (slider.getIlong() <= 0) {
                slider.setRunning(false);
                nd = false;
            }
        }
        if (nd) {
            String text = alphabets.get(currentAlp).getJapansese();
            if(text.length()==1){
                text=" "+text+" ";
            }
            jpnContent.setText(text);
            btnCase1.setText(caseLT1);
            btnCase2.setText(caseLT2);
        } else{
            stage.clear();
            Music.play("gameover");
            if (HighScore > HightScoreData.getScoreData()) {
                HightScoreData.addScore(HighScore);
            }
            jpnContent.clear();
            parameterCase.characters="";
            parameterCont.characters="";
            btnSkin.dispose();
            styleCont.font.dispose();
            styleScore.font.dispose();
            btnCase1Style.font.dispose();
            buttonsAtlas.dispose();
            generator.dispose();
            gsm.pushState(2);
            stage.getRoot().getColor().a = 0;
            stage.getRoot().addAction(fadeIn(0.5f));
        }
        Score.setText("" + HighScore);
    }

    @Override
    public void draw() {
        if (rd == 0) {
            Gdx.gl.glClearColor(64/255f, 64/255f, 64/255f, 64/255f);
        }
        if (rd == 1) {
            Gdx.gl.glClearColor(1, 0, 0, 0);
        }
        if (rd == 2) {
            Gdx.gl.glClearColor(112/255f, 77/255f, 54/255f, 255/255f);
        }


        if (slider != null) {
            stage.addActor(slider.getSlider());
        }
        stage.addActor(btnCase1);
        stage.addActor(btnCase2);
        stage.addActor(jpnContent);
        stage.addActor(Score);
    }
    //get content of label case
    public void getContent() {

        trueLT=alphabets.get(currentAlp).getLatin();
        int index = r.nextInt(alphabets.size());
        while (alphabets.get(currentAlp).equals(alphabets.get(index))){
            index = r.nextInt(alphabets.size());
        }
        String falseLT = alphabets.get(index).getLatin();
        int random = r.nextInt(10)+1;
        if(random % 2 ==0){
            caseLT1=trueLT;
            caseLT2=falseLT;
        }else{
            caseLT1=falseLT;
            caseLT2=trueLT;
        }
        if(caseLT1.length()==1){
            caseLT1=" "+caseLT1+" ";
        }

        if(caseLT2.length()==1){
            caseLT2=" "+caseLT2+" ";
        }
    }

    public static int getHighScore() {
        return HighScore;
    }

    public static void setNum(int num) {
        Play.num = num;
    }

    //if chose case 1
    public void answareCase1() {
        if (slider == null) {
            slider = new Slider();
        }
        if(trueLT.equalsIgnoreCase(caseLT1.trim())){
            HighScore += 1;
            currentAlp++;
            if(currentAlp==alphabets.size()){
                currentAlp=0;
                blendAlpList();
            }
            slider.setIlong(screenWidth);
            Music.play("point");
            getContent();
        }else{
            slider=null;
            nd=false;
        }
    }

    //if chose case 2
    public void answareCase2() {
        if (slider == null) {
            slider = new Slider();
        }

        if(!trueLT.equalsIgnoreCase(caseLT1.trim())){
            HighScore += 1;
            currentAlp++;
            if(currentAlp==alphabets.size()){
                currentAlp=0;
                blendAlpList();
            }
            slider.setIlong(screenWidth);
            Music.play("point");
            getContent();
        }else{
            slider=null;
            nd=false;
        }
    }
    //blend japanese alphalbet list
    public void blendAlpList(){
        for(int i=0;i<500;i++){
            int j = r.nextInt(alphabets.size());
            int k = r.nextInt(alphabets.size());
            while (j==k){
                k = r.nextInt(alphabets.size());
            }
            Alphabet temp = alphabets.get(j);
            alphabets.set(j,alphabets.get(k));
            alphabets.set(k,temp);
        }
    }

}

