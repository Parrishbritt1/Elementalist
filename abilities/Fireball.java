package abilities;


import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

import entities.Entity;
import main.GamePanel;

import java.awt.Graphics2D;


public class Fireball extends Entity implements Projectile {

    private GamePanel gp;
    private int screenX, screenY, worldX, worldY;
    private int damage;
    private BufferedImage N_IMAGE, NE_IMAGE, E_IMAGE, SE_IMAGE, S_IMAGE, SW_IMAGE, W_IMAGE, NW_IMAGE;


    
    public Fireball(GamePanel gp) {
        this.gp = gp;
        
        this.screenX = gp.player.screenX;
        this.screenY = gp.player.screenY;
        this.worldX = gp.player.worldX;
        this.worldY = gp.player.worldY;

        this.setDirection(gp.player.getDirection());

        this.speed = 10;
        this.damage = 5;
        getImages();
    }

    private void getImages() {
        try {
            N_IMAGE = ImageIO.read(getClass().getResourceAsStream("../images/effects/fireball-N.png"));
            NE_IMAGE = ImageIO.read(getClass().getResourceAsStream("../images/effects/fireball-NE.png"));
            E_IMAGE = ImageIO.read(getClass().getResourceAsStream("../images/effects/fireball-E.png"));
            SE_IMAGE = ImageIO.read(getClass().getResourceAsStream("../images/effects/fireball-SE.png"));
            S_IMAGE = ImageIO.read(getClass().getResourceAsStream("../images/effects/fireball-S.png"));
            SW_IMAGE = ImageIO.read(getClass().getResourceAsStream("../images/effects/fireball-SW.png"));
            W_IMAGE = ImageIO.read(getClass().getResourceAsStream("../images/effects/fireball-W.png"));
            NW_IMAGE = ImageIO.read(getClass().getResourceAsStream("../images/effects/fireball-NW.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        switch(this.getDirection()) {
            case N:
                this.worldY -= this.speed;
                break;
            case NE:
                this.worldY -= this.speed;
                this.worldX += this.speed;
                break;
            case E:
                this.worldX += this.speed;
                break;
            case SE:
                this.worldX += this.speed;
                this.worldY += this.speed;
                break;
            case S:
                this.worldY += this.speed;
                break;
            case SW:
                this.worldX -= this.speed;
                this.worldY += this.speed;
                break;
            case W:
                this.worldX -= this.speed;
                break;
            case NW:
                this.worldY -= this.speed;
                this.worldX -= this.speed;
                break;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image;
        switch(this.getDirection()) {
            case N:
                image = N_IMAGE;
                break;
            case NE:
                image = NE_IMAGE;
                break;
            case E:
                image = E_IMAGE;
                break;
            case SE:
                image = SE_IMAGE;
                break;
            case S:
                image = S_IMAGE;
                break;
            case SW:
                image = SW_IMAGE;
                break;
            case W:
                image = W_IMAGE;
                break;
            case NW:
                image = NW_IMAGE;
                break;
            default:
                image = N_IMAGE;
                break;
        }
        g2.drawImage(image, this.screenX, this.screenY, gp.tileSize, gp.tileSize, null);

        this.screenX = this.worldX - gp.player.worldX + this.screenX;
        this.screenY = this.worldY - gp.player.worldY + this.screenY;
        
        this.worldX = gp.player.worldX;
        this.worldY = gp.player.worldY;
    }
}
