package main;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;


public class KeyHandler implements KeyListener {
    
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    public void keyTyped(KeyEvent e) {} // Not used.

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_W) { // W Key
            upPressed = true;
        }
        if (keyCode == KeyEvent.VK_S) { // S Key
            downPressed = true;
        }
        if (keyCode == KeyEvent.VK_A) { // A Key
            leftPressed = true;
        }
        if (keyCode == KeyEvent.VK_D) { // D Key
            rightPressed = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_W) { 
            upPressed = false;
        }
        if (keyCode == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (keyCode == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (keyCode == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
}
