import java.awt.Graphics;
import java.awt.Graphics2D;

public class Canon {

    private double orbitX;
    private double orbitY;
    private double orbitRayon;
    private double radian = -Math.PI / 2;
    private double canonX;
    private double canonY;

    Canon(double orbX, double orbY, double orbR) {
        orbitX = orbX;
        orbitY = orbY;
        orbitRayon = orbR;
        canonX = orbitX + orbitRayon * Math.cos(radian);
        canonY = orbitY - (orbitRayon * Math.sin(radian));
    }

    void radianChanged(double r, Graphics g) {
        radian = r;
        canonX = orbitX + orbitRayon * Math.cos(radian) - (3.0 / 4) * orbitRayon  ;
        canonY = orbitY - orbitRayon * Math.sin(radian);
        drawCanon(canonX, canonY, 50, g);
    }

    private void drawCanon(double drawX, double drawY, double sphereRadius, Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawOval((int) drawX, (int) drawY, (int) sphereRadius, (int) sphereRadius);
    }

    public void setOrbX(double d) {
        System.out.println(d);
        orbitX = d / 2;
    }

    public double getCanonX() {
        return canonX;
    }

    public double getCanonY() {
        return canonY;
    }

}
