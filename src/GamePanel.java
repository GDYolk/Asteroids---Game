import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    public static final int screenWidth = 700;
    public static final int screenHeight = 500;
    final int FPS = 30; // frames per second

    Thread gameThread;
    public KeyHandler keyH = new KeyHandler();
    Plane plane = new Plane(keyH);
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
            if (timer >= 1000000000) {
                timer = 0;
            }
        }
    }

    public void update(){
        plane.update();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // COORDINATE
        g.drawLine(screenWidth/2, 0, screenWidth/2, screenHeight);
        g.drawLine(0, screenHeight/2, screenWidth, screenHeight/2);

        plane.draw(g);
    }
}
