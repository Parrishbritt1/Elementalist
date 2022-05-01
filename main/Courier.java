package main;

import javax.swing.JFrame;


public class Courier {
    public static void main(String[] args) {
        JFrame window = new JFrame("Elementalist");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack(); // sets window to preffered size and layouts of subcomponents

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}
