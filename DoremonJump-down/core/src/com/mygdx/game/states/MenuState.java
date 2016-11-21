package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.gfx.Assets;
import com.mygdx.game.main.Handler;


public class MenuState extends State {
    TextureRegion playButton;
    TextureRegion background;
    TextureRegion homeButton;

    public MenuState(Handler handler, GameStateManager gsm) {
        super(handler, gsm);
        playButton = Assets.playButton[0];
        background = Assets.background;
        homeButton = Assets.playButton[2];

    }

    public void handleInput() {
        if(Gdx.input.justTouched()){
            if(Gdx.input.getX()>=Handler.GAME_WIDTH * 0.056f&&
                    Gdx.input.getX()<=Handler.GAME_WIDTH*0.53f&&
                    Gdx.input.getY()>=Handler.GAME_HEIGHT *0.6f&&
                    Gdx.input.getY()<=Handler.GAME_HEIGHT*0.7f){
                gsm.set(new PlayingGuide(handler, gsm));
            }
            else if(Gdx.input.getX()>=Handler.GAME_WIDTH * 0.056f&&
                    Gdx.input.getX()<=Handler.GAME_WIDTH*0.53f&&
                    Gdx.input.getY()>=Handler.GAME_HEIGHT *0.73f&&
                    Gdx.input.getY()<=Handler.GAME_HEIGHT*0.83f){
                System.exit(0);
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
        batch.draw(background, 0, 0, Handler.GAME_WIDTH, Handler.GAME_HEIGHT);
        batch.draw(playButton, Handler.GAME_WIDTH * 0.056f, Handler.GAME_HEIGHT *0.6f,Handler.GAME_WIDTH * 0.47f, Handler.GAME_HEIGHT * 0.09f);
        batch.draw(homeButton, Handler.GAME_WIDTH * 0.056f, Handler.GAME_HEIGHT * 0.73f,Handler.GAME_WIDTH * 0.47f, Handler.GAME_HEIGHT * 0.09f);
        batch.end();
    }

    @Override
    public void dispose() {
    }
}
