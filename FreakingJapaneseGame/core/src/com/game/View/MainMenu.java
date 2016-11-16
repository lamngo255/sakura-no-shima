package com.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.game.Controller.GameState;
import com.game.Controller.GameStateManager;
import com.game.View.Play;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;

public class MainMenu extends GameState {

	private Label freaking,japanese;
	private LabelStyle style;
	private Skin skin;
	private TextButton btnHira,btnKata, btnBoth;
	private FreeTypeFontGenerator generator;
	private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
	private TextureAtlas buttonsAtlas; //** image of buttons **//
	TextButton.TextButtonStyle btnHiraStyle, btnKataStyle,btnBothStyle;

	private int screenWidth = Gdx.graphics.getWidth();
	private int screenHight = Gdx.graphics.getHeight();

	public MainMenu(final GameStateManager gsm) {
		super(gsm);
		generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/japanese2.ttc"));
		parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

		buttonsAtlas = new TextureAtlas("skin/ButtonMenu.txt"); //** button atlas image **//
		skin = new Skin();
		skin.addRegions(buttonsAtlas);//** skins for on and off **//
		style = new LabelStyle();

		parameter.size=(int)(screenWidth*0.08);
		style.font= generator.generateFont(parameter);
		freaking = new Label("Freaking", style);
		freaking.setPosition((float) (screenWidth*0.35), (float) (screenHight*0.7));

		parameter.size=(int)(screenWidth*0.15);
		style.font=generator.generateFont(parameter);
		japanese = new Label("Japanese", style);
		japanese.setPosition((float) (screenWidth*0.2), (float) (screenHight*0.6));

		btnHiraStyle = new TextButton.TextButtonStyle(); //** Button properties **//
		parameter.size=(int)(screenWidth*0.08);
		btnHiraStyle.up = skin.getDrawable("ButtonUp");
		btnHiraStyle.down = skin.getDrawable("ButtonDown");
		btnHiraStyle.font = generator.generateFont(parameter);
		btnHira = new TextButton("Hiragana", btnHiraStyle);

		btnKataStyle = new TextButton.TextButtonStyle(); //** Button properties **//
		parameter.size=(int)(screenWidth*0.08);
		btnKataStyle.up = skin.getDrawable("ButtonUp");
		btnKataStyle.down = skin.getDrawable("ButtonDown");
		btnKataStyle.font = generator.generateFont(parameter);
		btnKata = new TextButton("Katakana", btnKataStyle);

		btnBothStyle = new TextButton.TextButtonStyle(); //** Button properties **//
		parameter.size=(int)(screenWidth*0.07);
		btnBothStyle.up = skin.getDrawable("ButtonUp");
		btnBothStyle.down = skin.getDrawable("ButtonDown");
		btnBothStyle.font = generator.generateFont(parameter);
		generator.dispose();

		btnBoth = new TextButton("Hira and Kata", btnBothStyle);
		btnHira.setBounds((float) (screenWidth*0.27), (float) (screenHight*0.4), (float) (screenWidth*0.5), (float) (screenHight*0.1));
		btnKata.setBounds((float) (screenWidth*0.27), (float) (screenHight*0.29), (float) (screenWidth*0.5), (float) (screenHight*0.1));
		btnBoth.setBounds((float) (screenWidth*0.27), (float) (screenHight*0.18), (float) (screenWidth*0.5), (float) (screenHight*0.1));

		btnHira.addListener(new ClickListener(){

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				com.game.View.Play.setNum(1);
				parameter.characters="";
				stage.clear();
				skin.dispose();
				buttonsAtlas.dispose();
				btnKataStyle.font.dispose();
				btnBothStyle.font.dispose();
				btnHiraStyle.font.dispose();
			//	generator.dispose();
				gsm.pusState(1);
				stage.getRoot().getColor().a = 0;
				stage.getRoot().addAction(fadeIn(0.5f));
			}
			
		});
		btnKata.addListener(new ClickListener(){

			@Override
			public void touchUp(InputEvent event, float x, float y,
								int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				com.game.View.Play.setNum(2);
				parameter.characters="";
				stage.clear();
				skin.dispose();
				buttonsAtlas.dispose();
				btnKataStyle.font.dispose();
				btnBothStyle.font.dispose();
				btnHiraStyle.font.dispose();
			//	generator.dispose();
				gsm.pusState(1);
				stage.getRoot().getColor().a = 0;
				stage.getRoot().addAction(fadeIn(0.5f));
			}

		});
		btnBoth.addListener(new ClickListener(){

			@Override
			public void touchUp(InputEvent event, float x, float y,
								int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				com.game.View.Play.setNum(0);
				parameter.characters="";
				stage.clear();
				skin.dispose();
				buttonsAtlas.dispose();
				btnKataStyle.font.dispose();
				btnBothStyle.font.dispose();
				btnHiraStyle.font.dispose();
			//	generator.dispose();
				gsm.pusState(1);
				stage.getRoot().getColor().a = 0;
				stage.getRoot().addAction(fadeIn(0.5f));
			}

		});
	}

	@Override
	public void update(float dt) {
	//	Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClearColor(64/255f, 64/255f, 64/255f, 64/255f);
	}

	@Override
	public void draw() {
		stage.addActor(freaking);
		stage.addActor(btnHira);
		stage.addActor(btnKata);
		stage.addActor(btnBoth);
		stage.addActor(japanese);
	}

}
