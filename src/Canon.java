import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.*;

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

    // public void drawOrbitingSphere(int timeInterval, Graphics g) {

    // // let's just choose a bunch of values that we'll need
    // double orbitX = 500; /* x-coordinate in orbit's center */
    // double orbitY = 50; /* y-coordinate in orbit's center */
    // double orbitRadius = 50;
    // double orbitSpeed = Math.PI / 16;
    // double sphereRadius = 10;

    // /*
    // * based on the current time interval, we'll calculate where the sphere
    // * is at on its orbit
    // */
    // double radian = orbitSpeed * timeInterval;
    // double drawX = orbitX + orbitRadius * Math.cos(radian);
    // double drawY = orbitY - (orbitRadius * Math.sin(radian));
    // // canonX = (int) drawX;
    // // canonY = (int) drawY;

    // // use whichever Draw method is provided by your API
    // drawCanon(drawX, drawY, sphereRadius, g);
    // }

    void radianChanged(double r, Graphics g) {
        radian = r;
        canonX = orbitX + orbitRayon * Math.cos(radian);
        canonY = orbitY - orbitRayon * Math.sin(radian);
        drawCanon(canonX, canonY, 50, g);
    }

    private void drawCanon(double drawX, double drawY, double sphereRadius, Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // g2d.drawImage(null, 0, 0, null, null);
        // Ellipse2D.Double c = new Ellipse2D.Double(100, 100, 50, 50);
        // g2d.fill(c);
        g2d.drawOval((int) drawX, (int) drawY, (int) sphereRadius, (int) sphereRadius);

    }

    public void setOrbX(double d) {
        System.out.println(d);
        orbitX = d / 2;
    }

}
