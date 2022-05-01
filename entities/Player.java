package entities;

import main.KeyHandler;
import main.GamePanel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.io.File;

import javax.imageio.ImageIO;



public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        this.setStartValues();
        this.getPlayerImage();
    }

    private void setStartValues() {
        // Set Players default position
        x = 480;
        y = 384;
        speed = 4;
        dir = Direction.DOWN;
    }

    public void getPlayerImage() {
        try {
            downIdle = ImageIO.read(getClass().getResourceAsStream("../images/down-idle-32.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("../images/down-running1-32.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("../images/down-running2-32.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        boolean nothingBeingPressed = true;

        if (keyH.upPressed) {
            nothingBeingPressed = false;
            dir = Direction.UP;
            y -= this.speed;
        }
        if (keyH.downPressed) {
            nothingBeingPressed = false;
            dir = Direction.DOWN;
            y += this.speed;
        }
        if (keyH.leftPressed) {
            nothingBeingPressed = false;
            dir = Direction.LEFT;
            x -= this.speed;
        }
        if (keyH.rightPressed) {
            nothingBeingPressed = false;
            dir = Direction.RIGHT;
            x += this.speed;
        }
    
        
        spriteCounter++;
        if (nothingBeingPressed) {
            spriteNum = 3;

        } else if(spriteCounter > 10) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else {
                spriteNum = 1;
            }

            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch(dir) {
            case UP:
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                if (spriteNum == 3) {
                    image = downIdle;
                }
                break;

            case DOWN:
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                if (spriteNum == 3) {
                    image = downIdle;
                }
                break;

            case LEFT:
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                if (spriteNum == 3) {
                    image = downIdle;
                }
                break;

            case RIGHT:
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                if (spriteNum == 3) {
                    image = downIdle;
                }
                break;
        }

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
