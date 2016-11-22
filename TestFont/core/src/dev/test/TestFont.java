package dev.test;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

public class TestFont extends ApplicationAdapter {
    private Stage stage;
    private ShapeRenderer shape;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        SmartFontGenerator fontGen = new SmartFontGenerator();
        FileHandle exoFile = Gdx.files.internal("Mamelon.otf");
        BitmapFont fontSmall = fontGen.createFont(exoFile, "exo-small", 50);
        BitmapFont fontMedium = fontGen.createFont(exoFile, "exo-medium", 100);
        BitmapFont fontLarge = fontGen.createFont(exoFile, "exo-large", 150);

        stage = new Stage();

        Label.LabelStyle smallStyle = new Label.LabelStyle();
        smallStyle.font = fontSmall;
        Label.LabelStyle mediumStyle = new Label.LabelStyle();
        mediumStyle.font = fontMedium;
        Label.LabelStyle largeStyle = new Label.LabelStyle();
        largeStyle.font = fontLarge;

        Label small = new Label("さくら", smallStyle);
        Label medium = new Label("さくら", mediumStyle);
        Label large = new Label("さくら", largeStyle);

        Table table = new Table();
        table.setFillParent(true);
        table.align(Align.center);
        stage.addActor(table);

        table.defaults().size(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 6);

        table.add(small).row();
        table.add(medium).row();
        table.add(large).row();
    }

    @Override
    public void render() {
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {
    }
}
