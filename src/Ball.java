import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
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
    private double dtVal = 0.015;
    private double nombreUpdatesRepeted = 8;
    private int nombreRebondPointilee = 1;
    // private int widthBall = 45;
    // private int radiusBall = widthBall / 2;
    private BoardMain board;
    private BoardIA boardIA;
    private boolean startBall = false;
    private int rayon;
    private BoardModel boardModel;
    private int nombreBall;
    private Sound sound;
    private Peg lastPegTouched;
    BufferedImage imageCurrent;

    public Ball(double x_initial, double y_initial, double thetha, int r, int nb, BoardModel bm) {
        this.x_initial = x_initial;
        this.y_initial = y_initial;
        xt = x_initial;
        yt = y_initial;
        rayon = r;
        boardModel = bm;
        nombreBall = nb;
        // this.thetha = Math.toRadians(thetha) ;
        try {
            imageCurrent = ImageIO.read(this.getClass().getResource("ressources/ball.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageIcon imageIcon = new ImageIcon(imageCurrent);
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
        // double spaceWithTrou = boardModel.getTrou().getY() -
        // boardModel.getTrou().getLargeur() / 2 - yt + rayon;
        // if (spaceWithTrou < 2 * rayon) {
        // BufferedImage newImg = imageCurrent.getSubimage(0, 0,
        // labelImgBall.getWidth(), (int) spaceWithTrou);
        // ImageIcon subImageIcon = new ImageIcon(newImg);
        // labelImgBall = new JLabel(subImageIcon);
        // labelImgBall.setBounds((int) xt - rayon, (int) yt - rayon, 2 * rayon, (int)
        // spaceWithTrou);

        // Image image = subImageIcon.getImage(); // transform it
        // Image newimg = image.getScaledInstance(2 * rayon, 2 * rayon,
        // java.awt.Image.SCALE_SMOOTH); // scale it the
        // // smooth way
        // subImageIcon = new ImageIcon(newimg); // transform it back
        // labelImgBall.setIcon(subImageIcon);
        // System.out.println("sjfjrkjgkr");

        // } else
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

    public boolean setStartBall(boolean start) {
        startBall = start;
        return nombreBall != 0;
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
    public void updateBall() {
        if (!startBall || nombreBall == 0)
            return;
        for (int i = 0; i < nombreUpdatesRepeted; ++i) {
            updateCoordBall(dtVal / nombreUpdatesRepeted);
        }

        resetBall(false);
    }

    public ArrayList<Point> trajectoire() {
        if (startBall)
            return null;
        int rebond = 0;
        ArrayList<Point> l = new ArrayList<Point>();
        while (rebond <= nombreRebondPointilee) {
            for (int i = 0; i < nombreUpdatesRepeted; ++i) {
                updateCoordBall(dtVal / nombreUpdatesRepeted);
                if ((xt + rayon) >= widthBoard || xt + rayon <= 0) {
                    lastPegTouched = null;
                    ++rebond;
                }
            }
            if (yt + rayon >= heightBoard) {
                lastPegTouched = null;
                break;
            }
            if (boardModel.contact())
                ++rebond;
            l.add(new Point((int) xt, (int) yt));
        }
        vitesseX = vitesseX_initial;
        vitesseY = vitesseY_initial;
        xt = x_initial;
        yt = y_initial;
        return l;
    }

    // met à jour les coordonnées de la balle à l'instant dt
    public void updateCoordBall(double dt) {

        if ((xt + rayon) >= widthBoard || xt + rayon <= 0) {
            vitesseX = -vitesseX;
            lastPegTouched = null;
        }
        if (yt <= 0) {
            vitesseY = -vitesseY;
            lastPegTouched = null;
        }

        xt = xt(dt);
        yt = yt(dt);
    }

    public void resetBall(boolean needToReset) {
        boolean c = boardModel.getTrou().contactTrou(this);
        if (c) {
            boardModel.trouFall();
        }
        if (yt + rayon >= heightBoard || c || needToReset) {
            xt = x_initial;
            yt = y_initial;
            startBall = false;
            boardModel.retireAllTouched();
            vitesseX = vitesseX_initial;
            vitesseY = vitesseY_initial;
            lastPegTouched = null;
            if (!c)
                --nombreBall;
            boardModel.ballRestart();
        }
    }

    // calcule la position x de la balle à l'instant dt passé en argumant
    private double xt(double dt) {
        return xt + vInitial * vitesseX * dt;
    }

    // calcule la position y de la balle à l'instant dt passé en argumant
    private double yt(double dt) {
        vitesseY += gravity / nombreUpdatesRepeted;
        return (vInitial * vitesseY * dt) + yt;
    }

    private void rebondCercle(double x, double y) {
        double normeVectOrtho = (Math
                .sqrt(Math.pow(xt - x, 2) + Math.pow(yt - y, 2)));
        double vectOthogonalX = (xt - x) / normeVectOrtho;
        double vectOthogonalY = (yt - y) / normeVectOrtho;
        double produitScalaire = vitesseX * vectOthogonalX + vitesseY * vectOthogonalY;
        if (produitScalaire >= 0) {
            produitScalaire -= produitScalaire;
        }
        vitesseX -= 2 * produitScalaire * vectOthogonalX;
        vitesseY -= 2 * produitScalaire * vectOthogonalY;
    }

    public boolean contactPeg(Peg p) {
        if (p instanceof PegSoleil) {
            if (startBall) {
                if (p.isDestructed() != true) {
                    resetBall(true);
                    p.pegTouchdown();
                }

            }
            lastPegTouched = p;
            return true;
        }
        if (p instanceof PegRebond) {
            rebondCercle(p.getPegX(), p.getPegY());

            if (startBall) {
                vitesseX *= 1.3;
                vitesseY *= 1.3;
                p.pegTouchdown();

            }
            lastPegTouched = p;
            return true;
        }
        if (p instanceof PegCercle) {

            rebondCercle(p.getPegX(), p.getPegY());

            vitesseX *= 0.9;
            vitesseY *= 0.9;

            if (startBall) {
                boardModel.scoreTouchPeg(p, true);
                p.pegTouchdown();
                sound.setFile(1);
                sound.play();
            }
            lastPegTouched = p;
            return true;
        }
        if (p instanceof PegRectangle) {
            PegRectangle r = (PegRectangle) p;
            double normeVectLongueur = (Math
                    .sqrt(Math.pow(r.getVecteurLongueurX(), 2) + Math.pow(r.getVecteurLongueurY(), 2)));
            double vectLongueurXNormalise = r.getVecteurLongueurX() / normeVectLongueur;
            double vectLongueurYNormalise = r.getVecteurLongueurY() / normeVectLongueur;

            double normeVectLargeur = (Math
                    .sqrt(Math.pow(r.getVecteurLargeurX(), 2) + Math.pow(r.getVecteurLargeurY(), 2)));
            double vectLargeurXNormalise = r.getVecteurLargeurX() / normeVectLargeur;
            double vectLargeurYNormalise = r.getVecteurLargeurY() / normeVectLargeur;

            boolean[] conditions = r.projectionBallOrigineVecteurs(xt, yt, rayon, startBall);

            double vecteurX = xt - r.getOrigineVecteurX();
            double vecteurY = yt - r.getOrigineVecteurY();

            double produitScalaireLongueurV = r.produitScalaireLongueur(vitesseX, vitesseY);
            double produitScalaireLargeurV = r.produitScalaireLargeur(vitesseX, vitesseY);

            double produitScalaireLongueur = r.produitScalaireLongueur(vecteurX, vecteurY);
            double produitScalaireLargeur = r.produitScalaireLargeur(vecteurX, vecteurY);

            if (startBall) {
                System.out.println(produitScalaireLongueurV * vectLargeurXNormalise + " X Y "
                        + produitScalaireLongueurV * vectLargeurYNormalise);
                System.out.println("REBOND " + produitScalaireLargeurV + " " + produitScalaireLongueurV);
                System.out
                        .println("CONDITIONS " + conditions[0] + " " + conditions[1] + " " + conditions[2] + " "
                                + conditions[3]);
            }

            if (conditions[0]) {
                // if (vectLargeurXNormalise > 0) {
                // vectLargeurXNormalise -= vectLargeurXNormalise;
                // }
                if (conditions[1]) {
                    rebondCercle(p.getPegX() + r.getVecteurLongueurX() / 2 - r.getVecteurLargeurX() / 2,
                            p.getPegY() - r.getVecteurLargeurY() / 2 + r.getVecteurLongueurY() / 2);
                } else if (conditions[3]) {
                    rebondCercle(p.getPegX() - r.getVecteurLongueurX() / 2 + r.getVecteurLargeurX() / 2,
                            p.getPegY() + r.getVecteurLargeurY() / 2 - r.getVecteurLongueurY() / 2);
                } else {
                    // if (produitScalaireLargeurV>= 0) {
                    // produitScalaireLargeurV-= produitScalaireLargeur;
                    // }
                    if (produitScalaireLargeurV > 0) {
                        vitesseX -= 2 * produitScalaireLargeurV * vectLargeurXNormalise;
                        vitesseY -= 2 * produitScalaireLargeurV * vectLargeurYNormalise;
                    }
                }
            }

            else if (conditions[2]) {
                // if (vectLargeurXNormalise > 0) {
                // vectLargeurXNormalise -= vectLargeurXNormalise;
                // }
                if (conditions[1]) {
                    rebondCercle(p.getPegX() - r.getVecteurLongueurX() / 2 - r.getVecteurLargeurX() / 2,
                            p.getPegY() - r.getVecteurLargeurY() / 2 - r.getVecteurLongueurY() / 2);
                } else if (conditions[3]) {
                    rebondCercle(p.getPegX() + r.getVecteurLongueurX() / 2 - r.getVecteurLargeurX() / 2,
                            p.getPegY() - r.getVecteurLargeurY() / 2 + r.getVecteurLongueurY() / 2);
                } else {
                    // if (produitScalaireLargeurV<= 0) {
                    // produitScalaireLargeurV-= produitScalaireLargeur;
                    // }
                    if (produitScalaireLargeurV < 0) {
                        vitesseX -= 2 * produitScalaireLargeurV * vectLargeurXNormalise;
                        vitesseY -= 2 * produitScalaireLargeurV * vectLargeurYNormalise;
                    }
                }
            }

            else if (conditions[1]) {
                // if (produitScalaireLargeurV > 0) {
                // xt = xt + produitScalaireLongueur * vectLongueurXNormalise -
                // r.getVecteurLongueurX();
                // yt = yt + produitScalaireLongueur * vectLongueurYNormalise -
                // r.getVecteurLongueurY();
                vitesseX -= 2 * produitScalaireLongueurV * vectLongueurXNormalise;
                vitesseY -= 2 * produitScalaireLongueurV * vectLongueurYNormalise;
            } else if (conditions[3]) {
                // if (produitScalaireLargeurV >= 0) {
                // produitScalaireLargeurV -= produitScalaireLargeurV;
                // }
                // if (produitScalaireLargeurV < 0) {
                // xt = xt + produitScalaireLongueur * vectLongueurXNormalise;
                // yt = yt + produitScalaireLongueur * vectLongueurYNormalise;
                vitesseX -= 2 * produitScalaireLongueurV * vectLongueurXNormalise;
                vitesseY -= 2 * produitScalaireLongueurV * vectLongueurYNormalise;
            }

            if (lastPegTouched != p) {
                vitesseX *= 0.9;
                vitesseY *= 0.9;
            }
            lastPegTouched = p;

            if (startBall) {
                boardModel.scoreTouchPeg(p, true);
                p.pegTouchdown();
            }

            return true;
        }

        return false;
    }

    public void setSound(Sound sound) {
        this.sound = sound;
    }

    public void setBoardIA(BoardIA bia) {
        this.boardIA = bia;
    }
}
