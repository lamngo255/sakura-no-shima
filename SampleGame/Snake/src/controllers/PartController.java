package controllers;

import modules.PixelModule;
import utils.Utils;
import views.GameDrawer;
import views.SingleDrawer;

/**
 * Created by Nghia on 10/27/2016.
 */
public class PartController extends HeadController {


    public PartController(Direction currentDirection, PixelModule gameObject, GameDrawer gameDrawer) {
        super(currentDirection, gameObject, gameDrawer);
        CollisionPool.instance.register(this);
    }

    public static PartController createNextPart(HeadController headController){
        return new PartController( headController.getCurrentDirection(),
                                    headController.getTailPart(),
                                    new SingleDrawer(Utils.loadImageFromRes("snake.png"))
                                    );

    }


}
