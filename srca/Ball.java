import java.awt.*;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.*;

public class Ball {
    private double xt; // position x de la balle à chaque instant
    private double yt; // position y de la balle à chaque instant
    private double x_initial; // position x initial de la balle
    private double y_initial; // position y initial de la balle
    private double thetha; // angle
    private final double vInitial = 600; // vitesse initiale
    private double vitesseX_initial;
    private double vitesseY_initial;
    private double vitesseX;
    private double vitesseY;
    private final double g = 9.81 * 5; // acceleration de la pesanteur
    private final String path = "ressources/ball.png";
    private double widthBoard;
    private double heightBoard;
    private JLabel labelImgBall;
    private double gravity = 0.01;
    private double dt = 0.015;
    // private int widthBall = 45;
    // private int radiusBall = widthBall / 2;
    private BoardMain board;
    private boolean startBall = false;
    private int rayon;
    private BoardModel boardModel;
    private int nombreBall;

    public Ball(double x_initial, double y_initial, double thetha, int r, int nb, BoardModel bm) {
        this.x_initial = x_initial;
        this.y_initial = y_initial;
        xt = x_initial;
        yt = y_initial;
        rayon = r;
        boardModel = bm;
        nombreBall = nb;
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
        labelImgBall.setBounds((int) xt - rayon, (int) yt - rayon, 2 * rayon, 2 * rayon);
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
        vitesseX_initial = vitesseX;
        this.vitesseX = vitesseX;
    }

    public void setVitesseY(double vitesseY) {
        vitesseY_initial = vitesseY;
        this.vitesseY = vitesseY;
    }

    /* updating ball */
    public void updateBall(double dt) {
        if (!startBall || nombreBall == 0)
            return;
        updateCoordBall(dt);
        resetBall();
    }

    public ArrayList<Point> trajectoire() {
        if (startBall)
            return null;
        int rebond = 0;
        ArrayList<Point> l = new ArrayList<Point>();
        while (rebond < 2) {
            updateCoordBall(dt);
            if (yt + rayon >= heightBoard)
                break;
            if (boardModel.contact() || (xt + rayon) >= widthBoard || xt + rayon <= 0)
                ++rebond;
            l.add(new Point((int) xt, (int) yt));
        }
        vitesseX = vitesseX_initial;
        vitesseY = vitesseY_initial;
        xt = x_initial;
        yt = y_initial;
        return l;
    }

    // public Point[] trajectoire() {
    // if (startBall)
    // return null;
    // int rebond = 0;
    // Point[] tab = new Point[6];
    // ArrayList<Point> l = new ArrayList<Point>();
    // while (rebond < 2) {
    // updateCoordBall(dt);
    // if (yt + rayon >= heightBoard)
    // break;
    // if (boardModel.contact()) {
    // if (rebond == 0) {
    // tab[1] = l.get(l.size() / 2);
    // tab[2] = l.get(l.size() - 1);
    // }
    // if (rebond == 1) {
    // tab[3] = l.get(l.size() / 2);
    // tab[4] = l.get(l.size() - 1);
    // }
    // ++rebond;
    // l = new ArrayList<Point>();
    // }

    // l.add(new Point((int) xt, (int) yt));
    // }
    // vitesseX = vitesseX_initial;
    // vitesseY = vitesseY_initial;
    // xt = x_initial;
    // yt = y_initial;
    // return tab;
    // }

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
        boolean c = boardModel.getTrou().contactTrou(this);
        if (yt + rayon >= heightBoard || c) {
            xt = x_initial;
            yt = y_initial;
            startBall = false;
            boardModel.retireAllTouched();
            vitesseX = vitesseX_initial;
            vitesseY = vitesseY_initial;
            if (!c)
                --nombreBall;
        }
    }

    // calcule la position x de la balle à l'instant dt passé en argumant
    private double xt(double t) {
        return xt + vInitial * vitesseX * dt;
    }

    // calcule la position y de la balle à l'instant dt passé en argumant
    private double yt(double t) {
        vitesseY += gravity;
        return (vInitial * vitesseY * dt) + yt;
    }

    public boolean contactPeg(Peg p) {
        if (p instanceof PegCercle) {
            double normeVectOrtho = (Math
                    .sqrt(Math.pow(xt - p.getPegX(), 2) + Math.pow(yt - p.getPegY(), 2)));
            double vectOthogonalX = (xt - p.pegX) / normeVectOrtho;
            double vectOthogonalY = (yt - p.pegY) / normeVectOrtho;
            double produitScalaire = vitesseX * vectOthogonalX + vitesseY * vectOthogonalY;
            if (produitScalaire >= 0) {
                produitScalaire -= produitScalaire;
            }
            vitesseX -= 2 * produitScalaire * vectOthogonalX;
            vitesseY -= 2 * produitScalaire * vectOthogonalY;

            vitesseX *= 0.8;
            vitesseY *= 0.8;
            if (startBall)
                p.pegTouchdown();
            return true;
        }
        return false;
    }

}
