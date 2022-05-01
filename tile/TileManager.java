package tile;

import main.GamePanel;

import java.io.IOException;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;


public class TileManager {
    GamePanel gp;
    Tile[] tiles;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tiles = new Tile[10];
        getTileImage();
    }

    public void getTileImage() {
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("../images/tiles/grass.png"));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("../images/tiles/walls.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int x = 0;
        int y = 0;
        for (int row = 0; row < gp.maxScreenRow; row++) {
            for (int col = 0; col < gp.maxScreenCol; col++) {
                // Draw walls along the edge.
                if (row == 0 || row == gp.maxScreenRow-1 || col == 0 || col == gp.maxScreenCol-1) {
                    g2.drawImage(tiles[1].image, x, y, gp.tileSize, gp.tileSize, null);
                } else {
                    g2.drawImage(tiles[0].image, x, y, gp.tileSize, gp.tileSize, null);
                }
                x += gp.tileSize;
            }

            x = 0;
            y += gp.tileSize;
        }
    }
}
