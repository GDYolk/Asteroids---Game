import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

public class RotateTriangle extends JPanel {
    private final int centerX;
    private final int centerY;
    private double angle;

    public RotateTriangle() {
        centerX = 200;
        centerY = 200;
        angle = 0;

        // Bind the key events to the rotation actions
        bindKeyEvents();
    }

    private void bindKeyEvents() {
        // Create an action for rotating to the left
        Action rotateLeftAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rotateTriangle(5.0); // Rotate 5 degrees to the left
            }
        };

        // Create an action for rotating to the right
        Action rotateRightAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rotateTriangle(-5.0); // Rotate 5 degrees to the right
            }
        };

        // Bind the left arrow key to the rotate left action
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "rotateLeft");
        getActionMap().put("rotateLeft", rotateLeftAction);

        // Bind the right arrow key to the rotate right action
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "rotateRight");
        getActionMap().put("rotateRight", rotateRightAction);

        // Set the panel focusable for key events
        setFocusable(true);
    }

    private void rotateTriangle(double rotation) {
        angle += rotation;

        // Repaint the panel to update the triangle's position
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Clear the panel
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Define the triangle's vertices
        int[] xPoints = { centerX - 50, centerX, centerX + 50 };
        int[] yPoints = { centerY + 50, centerY - 50, centerY + 50 };

        // Create a new Graphics2D object from the original Graphics object
        Graphics2D g2d = (Graphics2D) g.create();

        // Translate to the center of the triangle
        g2d.translate(centerX, centerY);

        // Rotate the triangle around its center
        g2d.rotate(Math.toRadians(angle));

        // Draw the triangle
        g2d.setColor(Color.BLUE);
        g2d.fillPolygon(xPoints, yPoints, 3);

        // Dispose the Graphics2D object
        g2d.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Rotate Triangle");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);
            frame.setLocationRelativeTo(null);

            RotateTriangle triangle = new RotateTriangle();
            frame.add(triangle);
            frame.setVisible(true);
        });
    }
}
