package entities;

import java.awt.image.BufferedImage;

public abstract class Entity {
    /**
     * The X and Y positions on screen. (0, 0) top left.
     */
    public int x, y;
    
    /**
     * The amount of pixels this entity travels when moved.
     */
    public int speed;

    /**
     * Boolean to determine if entity is moving.
     */
    public boolean isMoving;


    /**
     * Image Positions of entities. (One Idle for each side, 2 in motion.)
     */
    public BufferedImage downIdle, down1, down2;

    // ================================ For animation ==================================
    enum Direction {
        UP, DOWN, LEFT, RIGHT;
    }
    private Direction dir;

    /**
     * Direction Getters/Setters.
     */
    public Direction getDirection() {
        return this.dir;
    }
    public void setDirection(Direction dir) {
        this.dir = dir;
    }

    public int spriteCounter = 0; 
    public int spriteNum = 1;
}
