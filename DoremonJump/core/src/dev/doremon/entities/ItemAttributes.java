package dev.doremon.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Nghia on 11/12/2016.
 */

public class ItemAttributes {
    private static final String FOLDER_NAME = "itemResource";

    private String name ;
    private static final int FILE_EXTENSION_LENGTH = 4;//.png
    private TextureRegion itemTextureRegion;

    public ItemAttributes(String fileName){
        this.name = fileName.substring(0,fileName.length()-FILE_EXTENSION_LENGTH);
        itemTextureRegion = new TextureRegion(new Texture(FOLDER_NAME + "/" + fileName));
        itemTextureRegion.flip(false,true);
    }

    public ItemAttributes(String name, TextureRegion itemTextureRegion) {
        this.name = name;
        this.itemTextureRegion = itemTextureRegion;
    }

    public String getName() {
        return name;
    }

    public TextureRegion getItemTextureRegion() {
        return itemTextureRegion;
    }

    @Override
    public String toString() {
        return name;
    }
}
