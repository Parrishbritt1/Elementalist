package tile;

import main.GamePanel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;


public class TileManager {
    GamePanel gp;
    Tile[] tiles;
    int[][] map;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tiles = new Tile[10];
        getTileImages();
    }

    public void getTileImages() {
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("../images/tiles/grass.png"));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("../images/tiles/walls.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads from txt File and adds integers to 2D map array.
     * @param mapName String of the file that contains a digit representation of the map.
     */
    public void setMap(String mapName) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(mapName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            int row = 0, col = 0;
            map = new int[gp.maxScreenRow][gp.maxScreenCol]; 

            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
                String[] line = bufferedReader.readLine().split(" ");
                for (String digit : line) {
                    int num = Integer.parseInt(digit);
                    map[row][col] = num;
                    col++;
                }
                col = 0;
                row++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Uses the digits in 2D map array to draw the tiles.
     * @param g2 Graphics2D object for drawing.
     */
    public void draw(Graphics2D g2) {
        int x = 0, y = 0;

        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                g2.drawImage(tiles[map[row][col]].image, x, y, gp.tileSize, gp.tileSize, null);
                x += gp.tileSize;
            }

            x = 0;
            y += gp.tileSize;
        }
    }
}
