import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;


public class GamePanel extends JPanel implements Runnable {
    // Screen Settings
    final int originalTileSize = 16; // 16x16 tile size
    final int scale = 3;

    final int tileSize = originalTileSize * scale; // 48x48
    final int maxScreenRow = 16; 
    final int maxScreenCol = 20;
    final int screenWidth = tileSize * maxScreenCol; // 960
    final int screenHeight = tileSize * maxScreenRow; // 768

    int FPS = 60;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;


    // Set Players default position
    int playerX = 480, playerY = 384, playerSpeed = 4;

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
        if (keyHandler.upPressed) {
            playerY -= playerSpeed;
        }
        if (keyHandler.downPressed) {
            playerY += playerSpeed;
        }
        if (keyHandler.leftPressed) {
            playerX -= playerSpeed;
        }
        if (keyHandler.rightPressed) {
            playerX += playerSpeed;
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g; // Convert g to a Graphics2D object becuase it has better functions and what not for geometry

        g2.setColor(Color.cyan);
        g2.fillRect(playerX, playerY, tileSize, tileSize);

        g2.dispose(); // Releases system resources
    }
}
