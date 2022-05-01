package entities;

import java.awt.image.BufferedImage;

public class Entity {
    public int x, y;
    public int speed;

    public BufferedImage downIdle, down1, down2;

    // For animation
    enum Direction {
        UP, DOWN, LEFT, RIGHT;
    }
    public Direction dir;

    public int spriteCounter = 0; 
    public int spriteNum = 1;
}
