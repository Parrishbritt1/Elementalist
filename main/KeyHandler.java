package main;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;


public class KeyHandler implements KeyListener {
    
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean primaryPressed, specialPressed, mobilityPressed;

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
        if (keyCode == KeyEvent.VK_J) {
            primaryPressed = true;
        }
        if (keyCode == KeyEvent.VK_K) {
            specialPressed = true;
        }
        if (keyCode == KeyEvent.VK_L) {
            mobilityPressed = true;
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
        if (keyCode == KeyEvent.VK_J) {
            primaryPressed = false;
        }
        if (keyCode == KeyEvent.VK_K) {
            specialPressed = false;
        }
        if (keyCode == KeyEvent.VK_L) {
            mobilityPressed = false;
        }
    }
}
