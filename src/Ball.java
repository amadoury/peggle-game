import java.awt.Graphics2D ;
import java.awt.Rectangle ;

public class Ball {
    private double xt; // position x de la balle à chaque instant
    private double yt; // position y de la balle à chaque instant
    private double x_initial; // position x initial de la balle
    private double y_initial; // position y initial de la balle
    private double thetha; // angle
    private final double vInitial = 300; // vitesse initiale
    private final double g = 9.81; // acceleration de la pesanteur
    private final String path = "src/ressources/ball.png" ;
    private Rectangle rectBoard ;

    public Ball(double x_initial, double y_initial, double thetha,Rectangle  rect) {
        this.x_initial = x_initial;
        this.y_initial = y_initial;
        this.thetha = Math.toRadians(thetha) ;
        this.rectBoard = rect ;
    }

    public double XInitial() {
        return x_initial;
    }

    public double YInitial() {
        return y_initial;
    }

    public void setXt(double xt) {
        this.xt = xt;
    }

    public void setYt(double yt) {
        this.yt = yt;
    }

    public double getXt() {
        return xt;
    }

    public double getYt() {
        return yt;
    }

    public void setTheta(double thetha) {
        this.thetha = thetha;
    }

    // met à jour les coordonnées de la balle à l'instant dt
    public void move(double dt) {
        xt = xt(dt);
        yt = yt(dt);
    }

    // calcule la position x de la balle à l'instant dt passé en argumant
    private double xt(double dt) {
        return vInitial * Math.sin(thetha) * dt + x_initial;
    }

    // calcule la position y de la balle à l'instant dt passé en argumant
    private double yt(double dt) {
        return 0.5 * g * (dt * dt) + (vInitial * Math.cos(thetha) * dt) + y_initial ;
    }

    public void drawBall(Graphics2D g){
       g.drawOval((int)xt, (int)yt, 20, 20) ;
       //g.drawOval(500, 200, 20, 20) ;
    }
}
