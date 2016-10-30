package controllers;

import modules.GameObject;

import java.awt.event.KeyEvent;
import java.util.Iterator;

/**
 * Created by Nghia on 10/27/2016.
 */
public class SnakeController extends ControllerManager {
    private static final int STARTING_PARTS = 20;
    HeadController headController;
    Direction storedDirection;


    private SnakeController(HeadController headController) {
        super();
        this.headController= headController;
        storedDirection=headController.getCurrentDirection();
        this.add(this.headController);
        for (int i = 0; i < STARTING_PARTS; i++) {
            this.grow();
        }
    }


    @Override
    public void run() {
        synchronized (baseControllers){
            Iterator<BaseController> iterator = baseControllers.iterator();
            while(iterator.hasNext()) {
                BaseController baseController = iterator.next();
                baseController.run();
                if (baseController instanceof PartController){
                    //swap Directions
                    Direction tempDirection = ((PartController) baseController).getCurrentDirection();
                    ((PartController) baseController).setCurrentDirection(storedDirection);
                    storedDirection = tempDirection;
                }
                    else
                        if (baseController instanceof HeadController){
                        storedDirection = headController.getCurrentDirection();
                        }

            }
        }
    }

    @Override
    public void add(BaseController baseController) {
        if (baseController instanceof HeadController)
            super.add(baseController);
    }

    public void keyPressed(KeyEvent e) {
        if (!checkValidKey(e)) return;
        storedDirection= headController.getCurrentDirection();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                headController.setCurrentDirection(Direction.Right);
                break;
            case KeyEvent.VK_LEFT:
                headController.setCurrentDirection(Direction.Left);
                break;
            case KeyEvent.VK_UP:
                headController.setCurrentDirection(Direction.Up);
                break;
            case KeyEvent.VK_DOWN:
                headController.setCurrentDirection(Direction.Down);
                break;
        }
    }

    public boolean checkValidKey(KeyEvent e){
        Direction currentDirection = headController.getCurrentDirection();
        boolean result=true;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                result = !(currentDirection == Direction.Left);
                break;
            case KeyEvent.VK_LEFT:
                result = !(currentDirection == Direction.Right);
                break;
            case KeyEvent.VK_UP:
                result = !(currentDirection == Direction.Down);
                break;
            case KeyEvent.VK_DOWN:
                result = !(currentDirection == Direction.Up);
                break;
        }
        return result;

    }

    public boolean checkIfLocationAvailable (int x, int y){
        for (BaseController headController: baseControllers ) {
            if (headController instanceof HeadController){
               GameObject part = ((HeadController) headController).getGameObject();
                if (part.getX() == x && part.getY()==y){
                    return false;
                }
            }
        }
        return true;
    }

    public void reverse(){
        System.out.println("reverse");
        for (BaseController headController: baseControllers ) {
            if (headController instanceof HeadController){
                ((HeadController) headController).reverse();
            }
        }
    }

    public void grow(){
        PartController partController = PartController.createNextPart(
                        (HeadController) baseControllers.get(baseControllers.size()-1));
        this.add(partController);
    }
    public static final SnakeController instance = new SnakeController(
                            HeadController.instance);



}
