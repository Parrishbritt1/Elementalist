package abilities;


import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

import entities.Entity;
import main.GamePanel;

import java.awt.Graphics2D;


public class Fireball extends Entity implements Projectile {

    private GamePanel gp;
    private int damage;
    private BufferedImage N_IMAGE, NE_IMAGE, E_IMAGE, SE_IMAGE, S_IMAGE, SW_IMAGE, W_IMAGE, NW_IMAGE;


    
    public Fireball(GamePanel gp, int worldX, int worldY) {
        
        this.gp = gp;
        this.worldX = worldX;
        this.worldY = worldY;

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
        this.worldX += speed;
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(E_IMAGE, this.worldX, this.worldY, gp.tileSize, gp.tileSize, null);
    }
}
