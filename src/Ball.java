import java.awt.*;
import javax.swing.*;

public class Ball {
    private double xt; // position x de la balle à chaque instant
    private double yt; // position y de la balle à chaque instant
    private double x_initial; // position x initial de la balle
    private double y_initial; // position y initial de la balle
    private double thetha; // angle
    private final double vInitial = 300; // vitesse initiale
    private double vitesseX;
    private double vitesseY;
    private final double g = 9.81 * 5; // acceleration de la pesanteur
    private final String path = "ressources/ball.png";
    private double widthBoard;
    private double heightBoard;
    private JLabel labelImgBall;
    // private int widthBall = 45;
    // private int radiusBall = widthBall / 2;
    private Board board;
    private boolean startBall = false;
    private int rayon;
    private BoardModel boardModel;

    public Ball(double x_initial, double y_initial, double thetha, int r, BoardModel bm) {
        this.x_initial = x_initial;
        this.y_initial = y_initial;
        xt = x_initial;
        yt = y_initial;
        rayon = r;
        boardModel = bm;
        // this.thetha = Math.toRadians(thetha) ;
        ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(path));
        labelImgBall = new JLabel(imageIcon);
        labelImgBall.setBounds((int) (x_initial) - rayon, (int) y_initial - rayon, 2 * rayon, 2 *
                rayon);

        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(2 * rayon, 2 * rayon, java.awt.Image.SCALE_SMOOTH); // scale it the
                                                                                                   // smooth way
        imageIcon = new ImageIcon(newimg); // transform it back
        labelImgBall.setIcon(imageIcon);
    }

    public JLabel getLabelImgBall() {
        return labelImgBall;
    }

    public void updateImgBall() {
        if (!startBall) {
            labelImgBall.setBounds((int) x_initial - rayon, (int) y_initial - rayon, 2 * rayon, 2 * rayon);
        } else {
            labelImgBall.setBounds((int) xt - rayon, (int) yt - rayon, 2 * rayon, 2 * rayon);
        }
        // labelImgBall.setBounds((int)xt, (int)yt, widthBall, widthBall);
    }

    public double XInitial() {
        return x_initial;
    }

    public double YInitial() {
        return y_initial;
    }

    public void setXInitial(double x_initial) {
        this.x_initial = x_initial;
        if (!startBall)
            xt = x_initial;
    }

    public void setYInitial(double y_initial) {
        this.y_initial = y_initial;
        if (!startBall)
            yt = y_initial;
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

    public int getRayon() {
        return rayon;
    }

    public void setRayon(int rayon) {
        this.rayon = rayon;
    }

    public boolean isBallStart() {
        return this.startBall;
    }

    public void setStartBall(boolean start) {
        startBall = start;
    }

    public void setWidthBoard(double widthBoard) {
        this.widthBoard = widthBoard;
    }

    public void setHeightBoard(double heightBoard) {
        this.heightBoard = heightBoard;
    }

    public void setTheta(double thetha) {
        this.thetha = thetha + Math.PI / 2;
    }

    public void setVitesseX(double vitesseX) {
        this.vitesseX = vitesseX;
    }

    public void setVitesseY(double vitesseY) {
        this.vitesseY = vitesseY;
    }

    /* updating ball */
    public void updateBall(double dt) {
        updateCoordBall(dt);
        resetBall();
    }

    // met à jour les coordonnées de la balle à l'instant dt
    public void updateCoordBall(double dt) {

        if ((xt + rayon) >= widthBoard || xt + rayon <= 0)
            vitesseX = -vitesseX;
        if (yt <= 0)
            vitesseY = -vitesseY;

        xt = xt(dt);
        yt = yt(dt);
    }

    public void resetBall() {
        if (yt + rayon >= heightBoard) {
            xt = x_initial;
            yt = y_initial;
            startBall = false;
            boardModel.retireAllTouched();
        }
    }

    // calcule la position x de la balle à l'instant dt passé en argumant
    private double xt(double dt) {
        // return vInitial * Math.sin(thetha) * dt + x_initial;
        // return vInitial * vitesseX * dt + x_initial;
        return xt + vInitial * vitesseX * 0.015;
    }

    // calcule la position y de la balle à l'instant dt passé en argumant
    private double yt(double dt) {
        // return 0.5 * g * (dt * dt) + (vInitial * Math.cos(thetha) * dt) + y_initial;
        return 0.5 * g * (0.015 * 0.015) + (vInitial * vitesseY * 0.015) + yt;
    }

    public void drawBall(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawOval((int) (xt + rayon), (int) yt, 20, 20);
    }

    public void contactPeg(Peg p) {
        if (p instanceof PegCercle) {
            double normeVectOrtho = (Math
                    .sqrt(Math.pow(xt - p.getPegX(), 2) + Math.pow(yt - p.getPegY(), 2)));
            double vectOthogonalX = (xt - p.pegX) / normeVectOrtho;
            double vectOthogonalY = (yt - p.pegY) / normeVectOrtho;
            double produitScalaire = vitesseX * vectOthogonalX + vitesseY * vectOthogonalY;
            vitesseX -= 2 * produitScalaire * vectOthogonalX;
            vitesseY -= 2 * produitScalaire * vectOthogonalY;
            // vitesseX *= 0.8;
            // vitesseY *= 0.8;
            p.pegTouchdown();
        }
    }

}
