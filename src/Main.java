import javax.swing.*;

public class Main{
    public static void main(String[] args) {
        JFrame jframe = new JFrame();
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setResizable(false);
        jframe.setTitle("Asteroids");

        GamePanel gamePanel = new GamePanel();
        jframe.add(gamePanel);
        jframe.pack();

        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);

        gamePanel.startGameThread();
    }
}
