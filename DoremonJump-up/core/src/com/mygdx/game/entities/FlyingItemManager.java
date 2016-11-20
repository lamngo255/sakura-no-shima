package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.main.Handler;

import java.util.ArrayList;

/**
 * Created by Nghia on 11/20/2016.
 */

public class FlyingItemManager {
    ArrayList<FlyingItem> flyingItemArrayList;
    public static Sound pickUpSound ;


    private FlyingItemManager() {
        flyingItemArrayList = new ArrayList<FlyingItem>();

    }

    public void spawn(Handler handler, Item itemTriggered, Entity target) {
        boolean reuseOldFlyingItem = false;
        for (int i = 0; i < flyingItemArrayList.size(); i++) {
            FlyingItem flyingItem = flyingItemArrayList.get(i);
            if (!flyingItem.isAlive()) {
                flyingItemArrayList.remove(i);
                flyingItemArrayList.add(FlyingItem.create(handler, itemTriggered, target));
                reuseOldFlyingItem = true;
                break;
            }
        }
        if (!reuseOldFlyingItem) {
            flyingItemArrayList.add(FlyingItem.create(handler, itemTriggered, target));
        }

    }

    public void tick() {
        for (int i = 0; i < flyingItemArrayList.size(); i++) {
            FlyingItem flyingItem = flyingItemArrayList.get(i);
            if (flyingItem.isAlive()) {
                flyingItem.tick();
            }

        }

    }

    public void remove(int i) {
        if (flyingItemArrayList.size() > i) {
            flyingItemArrayList.get(i).dispose();
            flyingItemArrayList.remove(i);
        }
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < flyingItemArrayList.size(); i++) {
            FlyingItem flyingItem = flyingItemArrayList.get(i);
            flyingItem.render(batch);
        }

    }

    public void refresh() {
        flyingItemArrayList.clear();
    }

    public void dispose() {
        pickUpSound.dispose();
        for (int i = 0; i < flyingItemArrayList.size(); i++) {
            FlyingItem flyingItem = flyingItemArrayList.get(i);
            flyingItem.dispose();
        }
    }

    public static final FlyingItemManager instance = new FlyingItemManager();

    public void init() {
        pickUpSound = Gdx.audio.newSound(Gdx.files.internal("Pickup_Coin.wav"));
    }
}
