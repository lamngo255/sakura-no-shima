package controllers;

import modules.GameObject;
import other.GameConfig;
import views.GameDrawer;
import java.awt.*;

/**
 * Created by apple on 10/11/16.
 */
public class SingleController implements BaseController {
    protected GameDrawer gameDrawer;
    protected GameObject gameObject;

    public SingleController(GameObject gameObject, GameDrawer gameDrawer) {
        this.gameDrawer = gameDrawer;
        this.gameObject = gameObject;
    }

    public void draw(Graphics g) {
        gameDrawer.drawImage(g, gameObject);
    }

    public void run() {

    }

    public void destroy() {
        gameObject.setAlive(false);
    }

    public GameObject getGameObject() {
        return gameObject;
    }

    public void setGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
    }
}
