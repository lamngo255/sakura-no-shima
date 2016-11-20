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

//    public void handleInput() {
//        if(Gdx.input.justTouched()){
//            if(Gdx.input.getX()>=handler.getWidth() * 0.056f&&
//                    Gdx.input.getX()<=handler.getWidth()*0.53f&&
//                    Gdx.input.getY()>=handler.getHeight() *0.6f&&
//                    Gdx.input.getY()<=handler.getHeight()*0.7f){
//                gsm.set(new GameState(handler, gsm));
//            }
//            else if(Gdx.input.getX()>=handler.getWidth() * 0.056f&&
//                    Gdx.input.getX()<=handler.getWidth()*0.53f&&
//                    Gdx.input.getY()>=handler.getHeight() *0.73f&&
//                    Gdx.input.getY()<=handler.getHeight()*0.83f){
//                System.exit(0);
//            }
//        }
//        if(Gdx.input.isTouched()){
//            gsm.set(new GameState(handler, gsm));
//        }
//    }


    public void handleInput() {
        if(Gdx.input.justTouched()){
            if(Gdx.input.getX()>=handler.getWidth() * 0.056f&&
                    Gdx.input.getX()<=handler.getWidth()*0.53f&&
                    Gdx.input.getY()>=handler.getHeight() *0.6f&&
                    Gdx.input.getY()<=handler.getHeight()*0.7f){
                gsm.set(new GameState(handler, gsm));
                System.out.println("ahihi");
            }
            else if(Gdx.input.getX()>=handler.getWidth() * 0.056f&&
                    Gdx.input.getX()<=handler.getWidth()*0.53f&&
                    Gdx.input.getY()>=handler.getHeight() *0.73f&&
                    Gdx.input.getY()<=handler.getHeight()*0.83f){
                System.exit(0);
            }
            System.out.println(Gdx.input.getX() + " " + Gdx.input.getY() + " " +
                                handler.getWidth() * 0.53f + " " + handler.getHeight() * 0.6f);
        }
    }

    @Override
    public void tick() {
        handleInput();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(background, 0, 0, handler.getWidth(), handler.getHeight());
//        batch.draw(playButton, handler.getWidth() / 2 - playButton.getRegionWidth() / 2 * 1.5f,
//                                handler.getHeight() / 2 - playButton.getRegionHeight() / 2 * 1.5f,
//                                playButton.getRegionWidth() * 1.5f,
//                                playButton.getRegionHeight() * 1.5f);
        batch.draw(playButton, handler.getWidth() * 0.056f, handler.getHeight() *0.6f,handler.getWidth() * 0.47f, handler.getHeight() * 0.09f)
        ;
        batch.draw(homeButton, handler.getWidth() * 0.056f, handler.getHeight() * 0.73f,handler.getWidth() * 0.47f, handler.getHeight() * 0.09f);

        batch.end();
    }

    @Override
    public void dispose() {
    }
}
