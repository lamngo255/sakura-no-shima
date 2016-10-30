package dev.tetris.input;

import dev.tetris.Handler;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    private boolean keys[];
    public boolean left, right;
    public Handler handler;

    public KeyManager() {
        keys = new boolean[256];
        left = false;
        right = false;
    }

    public void setHandler(Handler handler) {
	this.handler = handler;
    }

    public void tick() {
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
	switch (e.getKeyCode()) {
	    case KeyEvent.VK_LEFT:
		handler.getWorld().getPlayer().playerMove(-1);
		break;
	    case KeyEvent.VK_RIGHT:
		handler.getWorld().getPlayer().playerMove(1);
		break;
	    case KeyEvent.VK_DOWN:
		handler.getWorld().getPlayer().playerDrop();
		break;
	    case KeyEvent.VK_CONTROL:
		handler.getWorld().getPlayer().playerRotate(-1);
		break;
	    case KeyEvent.VK_SPACE:
		handler.getWorld().getPlayer().playerRotate(1);
		break;
	    default:
		break;
	}
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

}
