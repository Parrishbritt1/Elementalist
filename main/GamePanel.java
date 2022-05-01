package main;

import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;

import entities.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
    // Screen Settings
    final int originalTileSize = 32; // 32x32 tile size
    final int scale = 2;

    public final int tileSize = originalTileSize * scale; // 64x64
    public final int maxScreenRow = 12; 
    public final int maxScreenCol = 16;
    final int screenWidth = tileSize * maxScreenCol; // 1,024
    final int screenHeight = tileSize * maxScreenRow; // 768

    int FPS = 60;

    TileManager tileManager = new TileManager(this);
    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;

    Player player = new Player(this, keyHandler);


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // Faster rendering

        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1_000_000_000/FPS; // 16,666,666.666666666666666666666667
        double delta = 0;
        long lastTime = System.nanoTime(); // Current Nanotime which is a long number ex: 5232424000
        
        tileManager.setMap("../maps/map01.txt");
        
        while (gameThread != null) {
            
            // Update and draw only 60 times per second (whatever FPS is set to)
            long currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
        
            if (delta >= 1) {
                // UPDATE Information
                update();
    
                // DRAW the screen with updated information
                repaint(); // calls the paintComponent method 

                delta--;
            }
        }
    }

    public void update() {
        player.update();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g; // Convert g to a Graphics2D object becuase it has better functions and what not for geometry

        tileManager.draw(g2);
        player.draw(g2);

        g2.dispose(); // Releases system resources
    }
}
