import java.awt.*;

public class Plane {
    KeyHandler keyH;
    Point p1 = new Point(350, 250);
    Point p2 = new Point(330, 290);
    Point p3 = new Point(370, 290);
    Plane(KeyHandler keyH) {
        this.keyH = keyH;
    }
    public void update() {
        if (keyH.upPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                p1.y -= 3; p2.y -=3; p3.y-=3;
            }
            else if (keyH.leftPressed){
                rotateTriangle(p1, p2, p3, -6);
            }
            else {
                rotateTriangle(p1, p2, p3, 6);
            }
        }
    }

    public static void rotateTriangle(Point p1, Point p2, Point p3, double angle) {
        // Convert angle to radians
        double radianAngle = Math.toRadians(angle);

        // Get the center point of the triangle
        Point center = calculateTriangleCenter(p1, p2, p3);

        // Translate the triangle to the origin
        translateTriangleToOrigin(p1, p2, p3, center);

        // Perform the rotation using the rotation matrix
        double cos = Math.cos(radianAngle);
        double sin = Math.sin(radianAngle);
        double newX, newY;

        newX = p1.x * cos - p1.y * sin;
        newY = p1.x * sin + p1.y * cos;
        p1.setLocation(newX, newY);

        newX = p2.x * cos - p2.y * sin;
        newY = p2.x * sin + p2.y * cos;
        p2.setLocation(newX, newY);

        newX = p3.x * cos - p3.y * sin;
        newY = p3.x * sin + p3.y * cos;
        p3.setLocation(newX, newY);

        // Translate the triangle back to its original position
        translateTriangleFromOrigin(p1, p2, p3, center);
    }

        public static Point calculateTriangleCenter(Point p1, Point p2, Point p3) {
            int centerX = (p1.x + p2.x + p3.x) / 3;
            int centerY = (p1.y + p2.y + p3.y) / 3;
            return new Point(centerX, centerY);
        }

        public static void translateTriangleToOrigin(Point p1, Point p2, Point p3, Point center) {
            p1.translate(-center.x, -center.y);
            p2.translate(-center.x, -center.y);
            p3.translate(-center.x, -center.y);
        }

        public static void translateTriangleFromOrigin(Point p1, Point p2, Point p3, Point center) {
            p1.translate(center.x, center.y);
            p2.translate(center.x, center.y);
            p3.translate(center.x, center.y);
        }
}
