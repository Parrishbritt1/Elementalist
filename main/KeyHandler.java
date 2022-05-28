package main;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.util.HashSet;


public class KeyHandler implements KeyListener {
    
    public boolean primaryPressed, specialPressed, mobilityPressed;
    public static HashSet<Integer> keySet = new HashSet<>();

    public void keyTyped(KeyEvent e) {} // Not used.

    public void keyPressed(KeyEvent e) {
        keySet.add(e.getKeyCode());
    }

    public void keyReleased(KeyEvent e) {
        keySet.remove(e.getKeyCode());
    }
}
