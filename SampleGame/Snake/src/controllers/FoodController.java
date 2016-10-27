package controllers;

import modules.PixelModule;
import other.GameConfig;
import utils.Utils;
import views.GameDrawer;
import views.SingleDrawer;

/**
 * Created by Nghia on 10/27/2016.
 */
public class FoodController extends SingleController implements Contactable{


    private static final int GROW_LEVEL = 4;

    public static final FoodController instance = new FoodController(
            new PixelModule(0,0),
            new SingleDrawer(Utils.loadImageFromRes("food.png")));

    static {
        init();
    }
    public FoodController(PixelModule pixelModule, GameDrawer gameDrawer) {
        super(pixelModule, gameDrawer);
        CollisionPool.instance.register(this);
    }


    public static void init(){
        instance.changeLocation();
    }





    public void changeLocation(){
        int newX;
        int newY;
        do {
            newX = GameConfig.instance.getRandomPixelX();
            newY = GameConfig.instance.getRandomPixelY();
        }
        while (SnakeController.instance.checkIfLocationAvailable(newX, newY)==false);
        instance.setGameObject(new PixelModule(newX,newY));
    }



    @Override
    public void onCollide(Contactable contactable) {
        if (contactable instanceof HeadController){
            Utils.playSoundInNewThreadFromResources("food_eaten.wav",false);
            changeLocation();
            for (int i = 0; i < GROW_LEVEL; i++) {
                SnakeController.instance.grow();
            }
        }
    }

    @Override
    public boolean collidesWith(Contactable contactable) {
        return false;
    }
}
