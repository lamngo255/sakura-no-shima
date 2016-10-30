package modules;

import other.GameConfig;

/**
 * Created by Nghia on 10/27/2016.
 */
public class PixelModule extends GameObject {

    public static final int WIDTH = GameConfig.instance.getPixel();
    public static final int HEIGHT = GameConfig.instance.getPixel();


    public PixelModule(int x, int y) {
        super(x, y, WIDTH, HEIGHT);
    }
    public PixelModule(PixelModule pixelModule){
        super(pixelModule.getX(), pixelModule.getY(),WIDTH,HEIGHT);
    }



}
