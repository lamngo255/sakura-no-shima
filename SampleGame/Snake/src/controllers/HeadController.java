package controllers;

import modules.GameObject;
import modules.PixelModule;
import other.GameConfig;
import other.GameWindow;
import utils.Utils;
import views.GameDrawer;
import views.SingleDrawer;

/**
 * Created by Nghia on 10/27/2016.
 */
public class HeadController extends SingleController implements Contactable{

    private static final int STARTING_LOCATION_X = GameConfig.instance.getScreenWidth() / 2;
    private static final int STARTING_LOCATION_Y = GameConfig.instance.getScreenHeight() / 2;

    public static final int VELOCITY = GameConfig.instance.getPixel();
    private static final Direction DEFAULT_DIRECTION = Direction.Up;
    protected Direction currentDirection;
    protected static boolean running;
    protected Direction backUpDirection;


    public HeadController(Direction currentDirection, PixelModule gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        this.currentDirection = currentDirection;
        running = true;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }
    public Direction getCurrentDirection() {
        return currentDirection;
    }

    @Override
    public void run() {
        super.run();
        this.backUpDirection = this.currentDirection;
        if (running==false){
            return;
        }
        if (
                gameObject.move(currentDirection,VELOCITY)
                        ==false ){
            Utils.playSoundInNewThreadFromResources("border_reach.wav",false);
            System.out.println("BorderCollision");
            System.out.println("You Lost!!!");
            running = false;
            GameWindow.setRunning(false);
        }
    }

    public boolean checkBorderCollision(){
        switch (currentDirection){
            case Up:
                if (gameObject.getY()==0){
                    return true;
                }
                break;
            case Down:
                if (gameObject.getY()==GameConfig.instance.getScreenHeight()-GameConfig.instance.getPixel()){
                    return true;
                }
            case Left:
                if (gameObject.getX()==0){
                    return true;
                }
                break;
            case Right:
                if (gameObject.getX()==GameConfig.instance.getScreenWidth()-GameConfig.instance.getPixel()){
                    return true;
                }
        }
        return false;
    }

    public void reverse(){
        this.gameObject=this.getTailPart(backUpDirection);
    }
    public PixelModule getTailPart(){
        return getTailPart(this.getCurrentDirection());
    }

    public PixelModule getTailPart(Direction direction){
        GameObject headPart = this.getGameObject();
        Direction headDirection = direction;
        int xDiff = 0;
        int yDiff = 0;
        switch (headDirection){
            case Up:
                yDiff+= PixelModule.HEIGHT;
                break;
            case Down:
                yDiff-= PixelModule.HEIGHT;
                break;
            case Left:
                xDiff+= PixelModule.WIDTH;
                break;
            case Right:
                xDiff-= PixelModule.WIDTH;
                break;
        }
        return new PixelModule(headPart.getX()+xDiff,headPart.getY()+yDiff);
    }

    public PixelModule getHeadPart(){
        GameObject headPart = this.getGameObject();
        Direction headDirection = this.getCurrentDirection();
        int xDiff = 0;
        int yDiff = 0;
        switch (headDirection){
            case Up:
                yDiff-= PixelModule.HEIGHT;
                break;
            case Down:
                yDiff+= PixelModule.HEIGHT;
                break;
            case Left:
                xDiff-= PixelModule.WIDTH;
                break;
            case Right:
                xDiff+= PixelModule.WIDTH;
                break;
        }
        return new PixelModule(headPart.getX()+xDiff,headPart.getY()+yDiff);
    }

    public static final HeadController instance = new HeadController( DEFAULT_DIRECTION,
                                                new PixelModule(STARTING_LOCATION_X,STARTING_LOCATION_X),
                                                new SingleDrawer(Utils.loadImageFromRes("food.png")));

    @Override
    public void onCollide(Contactable contactable) {
        if (contactable instanceof PartController){
            Utils.playSoundInNewThreadFromResources("body_collide.wav",false);
            System.out.println("You Lost!!!!");
            SnakeController.instance.reverse();
//            running=false;
            GameWindow.setRunning(false);
        }
    }

    @Override
    public boolean collidesWith(Contactable contactable) {
        if (contactable instanceof PartController){
            GameObject gameObject1 = this.getGameObject();
            GameObject gameObject2 = ((SingleController)contactable).getGameObject();
            return gameObject1.equals(gameObject2);
        }
        if (contactable instanceof FoodController){
            GameObject gameObject1 = this.getGameObject();
            GameObject gameObject2 = ((SingleController)contactable).getGameObject();
            return gameObject1.equals(gameObject2);
        }
        return false;
    }
}
