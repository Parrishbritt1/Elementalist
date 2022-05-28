package entities;

import main.KeyHandler;
import main.GamePanel;

import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.event.KeyEvent;

import java.io.IOException;
import javax.imageio.ImageIO;


import abilities.*;


public class Player extends Entity {
    GamePanel gp;

    public final int screenX, screenY;

    private PrimaryAttack primary;

    /**
     * Constructor of Player
     * @param gp Game Panel 
     * @param keyH Key Handler
     */
    public Player(GamePanel gp) {
        this.gp = gp;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        primary = new PrimaryAttack(this.gp, Ability.FIREBALL);

        this.setStartValues();
        this.getPlayerImages();
    }

    /**
     * Starting position of Player when Instantiated.
     */
    private void setStartValues() {
        // Set Players default position
        this.worldX = gp.tileSize * 5;
        this.worldY = gp.tileSize * 5;
        this.speed = 4;
        setDirection(Direction.S);
        collisionRect = new Rectangle(8, 32, 32, 32);
    }

    public void setPrimary(Ability a) {
        this.primary.setCurrentAbility(a);
    }

    /**
     * Retrieves all of the images of Player.
     */
    public void getPlayerImages() {
        try {
            downIdle = ImageIO.read(getClass().getResourceAsStream("../images/player/down-idle-32.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("../images/player/down-running1-32.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("../images/player/down-running2-32.png"));
            
            leftIdle = ImageIO.read(getClass().getResourceAsStream("../images/player/left-idle-32.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("../images/player/left-running1-32.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("../images/player/left-running2-32.png"));
            leftShooting = ImageIO.read(getClass().getResourceAsStream("../images/player/left-shooting-32.png"));
            
            rightIdle = ImageIO.read(getClass().getResourceAsStream("../images/player/right-idle-32.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("../images/player/right-running1-32.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("../images/player/right-running2-32.png"));
            rightShooting = ImageIO.read(getClass().getResourceAsStream("../images/player/right-shooting-32.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates player movement based on any collisions with tiles.
     */
    private void moveBasedOnCollision() {
        switch(getDirection()) {
            case N:
                if (upColliding) {
                    break;
                } else {
                    this.worldY -= this.speed;
                    break;
                }

            case NE:
                if (upColliding) {
                    this.worldX += this.speed;
                    break;
                } else if (rightColliding) {
                    this.worldY -= this.speed;
                    break;
                } else {
                    this.worldX += this.speed;
                    this.worldY -= this.speed;
                    break;
                }

            case E:
                if (rightColliding) {
                    break;
                } else {
                    this.worldX += this.speed;
                    break;
                }
                
            case SE:
                if (downColliding) {
                    this.worldX += this.speed;
                    break;
                } else if (rightColliding) {
                    this.worldY += this.speed;
                    break;
                } else {
                    this.worldX += this.speed;
                    this.worldY += this.speed;
                    break;
                }

            case S:
                if (downColliding) {
                    break;
                } else {
                    this.worldY += this.speed;
                    break;
                }

            case SW:
                if (downColliding) {
                    this.worldX -= this.speed;
                    break;
                } else if (leftColliding) {
                    this.worldY += this.speed;
                    break;
                } else {
                    this.worldX -= this.speed;
                    this.worldY += this.speed;
                    break;
                }

            case W:
                if (leftColliding) {
                    break;
                } else {
                    this.worldX -= this.speed;
                    break;
                }

            case NW:
                if (upColliding) {
                    this.worldX -= this.speed;
                    break;
                } else if (leftColliding) {
                    this.worldY -= this.speed;
                    break;
                } else {
                    this.worldX -= this.speed;
                    this.worldY -= this.speed;
                    break;
                }
        }
    }


    /**
     * UPDATES PLAYER INFORMATION. Part 2 OF GAME LOOP (MOVING & ABILITIES info updates)
     */
    public void update() {
        // MOVEMENT
        if (KeyHandler.keySet.contains(KeyEvent.VK_W) || KeyHandler.keySet.contains(KeyEvent.VK_S) ||
            KeyHandler.keySet.contains(KeyEvent.VK_A) || KeyHandler.keySet.contains(KeyEvent.VK_D)) {
            this.isMoving = true;
            
            if (KeyHandler.keySet.contains(KeyEvent.VK_W) && KeyHandler.keySet.contains(KeyEvent.VK_A)) {
                System.out.println("upLeftPressed");
                setDirection(Direction.NW);
            } else if (KeyHandler.keySet.contains(KeyEvent.VK_W) && KeyHandler.keySet.contains(KeyEvent.VK_D)) {
                System.out.println("upRightPressed");
                setDirection(Direction.NE);
            } else if (KeyHandler.keySet.contains(KeyEvent.VK_S) && KeyHandler.keySet.contains(KeyEvent.VK_A)) {
                System.out.println("downLeftPressed");
                setDirection(Direction.SW);
            } else if (KeyHandler.keySet.contains(KeyEvent.VK_S) && KeyHandler.keySet.contains(KeyEvent.VK_D)) {
                System.out.println("downRightPressed");
                setDirection(Direction.SE);

            } else if (KeyHandler.keySet.contains(KeyEvent.VK_W)) {
                System.out.println("upPressed");
                setDirection(Direction.N);
            } else if (KeyHandler.keySet.contains(KeyEvent.VK_S)) {
                System.out.println("downPressed");
                setDirection(Direction.S);
            } else if (KeyHandler.keySet.contains(KeyEvent.VK_A)) {
                System.out.println("leftPressed");
                setDirection(Direction.W);
            } else if (KeyHandler.keySet.contains(KeyEvent.VK_D)) {
                System.out.println("rightPressed");
                setDirection(Direction.E);
            }
            
            // Check Tile collision
            gp.cChecker.checkTileCollision(this);
            moveBasedOnCollision();
            
        // ANIMATION COUNTER
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

        if (KeyHandler.keySet.contains(KeyEvent.VK_J)) {
            gp.projectileList.add(primary.triggerAbility());
        }
    }

    /**
     * DRAW IMAGES. PART 2 OF GAME LOOP.
     * @param g2 Graphics 2D object from Gamepanel paintComponent Method.
     */
    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        // Change image position depending on direction facing.
        switch(getDirection()) {
            case N:
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                if (spriteNum == 3) {
                    image = downIdle;
                }
                if (KeyHandler.keySet.contains(KeyEvent.VK_J)) {
                    image = rightShooting;
                }
                break;

            case S:
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                if (spriteNum == 3) {
                    image = downIdle;
                }
                if (KeyHandler.keySet.contains(KeyEvent.VK_J)) {
                    image = rightShooting;
                }
                break;

            case W:
            case SW:
            case NW:
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                if (spriteNum == 3) {
                    image = leftIdle;
                }
                if (KeyHandler.keySet.contains(KeyEvent.VK_J)) {
                    image = leftShooting;
                }
                break;

            case E:
            case SE:
            case NE:
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                if (spriteNum == 3) {
                    image = rightIdle;
                }
                if (KeyHandler.keySet.contains(KeyEvent.VK_J)) {
                    image = rightShooting;
                }
                break;
        }

        // Draw Player Image.
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

        // ==== DEBUG FOR SHOWING COLLISION BOX ====
        // Color myColor = new Color(255, 255, 255, 127);
        // g2.setColor(myColor);
        // g2.drawRect(this.collisionRect.x, this.collisionRect.y, this.collisionRect.width, this.collisionRect.height);
    }
}
