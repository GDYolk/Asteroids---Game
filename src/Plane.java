public class Plane {
    KeyHandler keyH;
    int x1=350,x2=330, x3=370, y1=250, y2=290, y3=290;
    Plane(KeyHandler keyH) {
        this.keyH = keyH;
    }
    public void update() {
        if (keyH.upPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                y1 -= 3; y2 -=3; y3-=3;
            }
            else if (keyH.leftPressed){
                x1-=3; x2-=3; x3-=3;
            }
            else {
                x1+=3; x2+=3; x3+=3;
            }
        }
    }
}
