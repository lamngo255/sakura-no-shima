package controllers;


import modules.GameObject;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by apple on 10/16/16.
 */
public class CollisionPool implements BaseController {
    private Vector<Contactable> contactableVector;
    HeadController headController;
    private CollisionPool(HeadController headController) {
        contactableVector = new Vector<>();
        this.headController = headController;
    }

    @Override
    public void run() {
        for (int i=0; i<contactableVector.size(); i++){
            Contactable contactable = contactableVector.get(i);
            if (contactable instanceof SingleController){
                if (headController.collidesWith(contactable)
                        && HeadController.running
                        ){
                    headController.onCollide(contactable);
                    contactable.onCollide(headController);
                }
            }
        }


    }

    @Override
    public void draw(Graphics g) {

    }

    public void register(Contactable contactable) {
        contactableVector.add(contactable);
    }

    public static final CollisionPool instance = new CollisionPool(HeadController.instance);
}
