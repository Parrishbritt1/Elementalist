package entities;

import main.KeyHandler;
import main.GamePanel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import java.io.IOException;

import javax.imageio.ImageIO;



public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX, screenY;

    /**
     * Constructor of Player
     * @param gp Game Panel 
     * @param keyH
     */
    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        this.setStartValues();
        this.getPlayerImage();
    }

    /**
     * Starting position of Player when Instantiated.
     */
    private void setStartValues() {
        // Set Players default position
        this.worldX = gp.tileSize * 40;
        this.worldY = gp.tileSize * 30;
        this.speed = 4;
        setDirection(Direction.DOWN);
    }

    /**
     * Retrieves all of the images of Player.
     */
    public void getPlayerImage() {
        try {
            downIdle = ImageIO.read(getClass().getResourceAsStream("../images/player/down-idle-32.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("../images/player/down-running1-32.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("../images/player/down-running2-32.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * UPDATES PLAYER INFORMATION. Part 2 OF GAME LOOP (MOVING & ABILITIES info updates)
     */
    public void update() {
        if (keyH.upPressed == true || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            this.isMoving = true;

            if (keyH.upPressed) {
                setDirection(Direction.UP);
                this.worldY -= this.speed;
            }
            if (keyH.downPressed) {
                setDirection(Direction.DOWN);
                this.worldY += this.speed;
            }
            if (keyH.leftPressed) {
                setDirection(Direction.LEFT);
                this.worldX -= this.speed;
            }
            if (keyH.rightPressed) {
                setDirection(Direction.RIGHT);
                this.worldX += this.speed;
            }
        
            spriteCounter++;
            
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }  
        if (!this.isMoving) spriteNum = 3;
        this.isMoving = false;
    }

    /**
     * DRAW IMAGES. PART 2 OF GAME LOOP.
     * @param g2 Graphics 2D object from Gamepanel paintComponent Method.
     */
    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        // Change image position depending on direction facing.
        switch(getDirection()) {
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

        // Draw Player Image.
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
