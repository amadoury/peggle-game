
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class PegRectangle extends Peg {

    private double longueur;
    private double largeur;
    private double angle;
    private double vecteurLongueurX;
    private double vecteurLongueurY;
    private double vecteurLargeurX;
    private double vecteurLargeurY;
    private double determinant;
    private double origineVecteurX;
    private double origineVecteurY;
    private BufferedImage imageLabel;

    PegRectangle(int x, int y, double lo, double la, double angle, String c) {
        super(x, y, c);
        longueur = (int) lo;
        largeur = (int) la;
        this.angle = angle;

        vecteurLongueurX = Math.cos(angle) * longueur;
        vecteurLongueurY = Math.sin(angle) * longueur;

        vecteurLargeurX = vecteurLongueurY;
        vecteurLargeurY = -vecteurLongueurX;
        double normeVectLargeur = (Math
                .sqrt(Math.pow(vecteurLargeurX, 2) + Math.pow(vecteurLargeurY, 2)));
        vecteurLargeurX = vecteurLargeurX / normeVectLargeur * largeur;
        vecteurLargeurY = vecteurLargeurY / normeVectLargeur * largeur;
        // System.out.println("vecteurLongueur " + vecteurLongueurX + " " +
        // vecteurLongueurY + "longueur" + longueur);

        determinant = vecteurLongueurX * vecteurLargeurY - vecteurLargeurX * vecteurLongueurY;

        origineVecteurX = pegX - vecteurLongueurX / 2 - vecteurLargeurX / 2;
        origineVecteurY = pegY - vecteurLongueurY / 2 - vecteurLargeurY / 2;

        // System.out.println(" vect " + vecteurLongueurX + " " + vecteurLongueurY);
        System.out.println(" vect " + vecteurLargeurX + " " + vecteurLargeurY);
        // System.out.println(" vectOrigin " + origineVecteurX + " " + origineVecteurY);

        BufferedImage image;
        try {
            image = ImageIO.read(this.getClass().getResource("ressources/peg-" + color + "-rectangle.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block

            e.printStackTrace();
            return;
        }

        final int w = (int) Math.floor(image.getWidth());
        final int h = (int) Math.floor(image.getHeight());
        final BufferedImage rotatedImage = new BufferedImage(w, h, image.getType());
        final AffineTransform at = new AffineTransform();
        at.translate(w / 2, h / 2);
        at.rotate(angle, 0, 0);
        at.translate(-image.getWidth() / 2, -image.getHeight() / 2);
        final AffineTransformOp rotateOp = new AffineTransformOp(at,
                AffineTransformOp.TYPE_BILINEAR);
        rotateOp.filter(image, rotatedImage);
        image = rotatedImage;
        Image newimg = image.getScaledInstance(
                (int) (Math.sin(angle) * largeur / 2 + longueur),
                (int) (Math.sin(angle) * largeur / 2 + longueur),
                java.awt.Image.SCALE_SMOOTH); // scale

        imageLabel = image;
        ImageIcon imageIcon = new ImageIcon(newimg); // transform it back
        // jlabel.setIcon(imageIcon);

        // Image image = imageIcon.getImage();
        // Image newimg = image.getScaledInstance((int) (longueur), (int) (longueur),
        // java.awt.Image.SCALE_SMOOTH); // scale

        // imageIcon = new ImageIcon(newimg);
        jlabel = new LabelPeg(imageIcon);
        // jlabel.setSize(rayon * 2, rayon * 2);
        jlabel.setBounds((int) (pegX - Math.cos(angle) * longueur / 2 - Math.sin(angle) * largeur / 2),
                (int) (pegY - Math.sin(angle) * largeur / 2 - Math.cos(angle) * longueur / 2),
                (int) longueur,
                (int) longueur);
    }

    public double getLargeur() {
        return largeur;
    }

    public double getLongueur() {
        return longueur;
    }

    public double getAngle() {
        return angle;
    }

    public double getVecteurLongueurX() {
        return vecteurLongueurX;
    }

    public double getVecteurLongueurY() {
        return vecteurLongueurY;
    }

    public double getVecteurLargeurX() {
        return vecteurLargeurX;
    }

    public double getVecteurLargeurY() {
        return vecteurLargeurY;
    }

    public double getDeterminant() {
        return determinant;
    }

    public double getOrigineVecteurX() {
        return origineVecteurX;
    }

    public double getOrigineVecteurY() {
        return origineVecteurY;
    }

    private double produitScalaire(double x1, double x2, double y1, double y2) {
        return x1 * y1 + x2 * y2;
    }

    public boolean[] projectionBallOrigineVecteurs(double x, double y, double r) {
        // projection : tab[0] longueurX, tab[1] longueurY,
        // tab[2] largeurX, tab[3] largeurY
        double vecteurX = x - origineVecteurX;
        double vecteurY = y - origineVecteurY;

        double produitScalaireLongueur = vecteurX * vecteurLongueurX + vecteurY * vecteurLongueurY;
        double produitScalaireLargeur = vecteurX * vecteurLargeurX + vecteurY * vecteurLargeurY;

        double[] tab = new double[4];

        tab[0] = produitScalaireLongueur * vecteurLongueurX / longueur;
        tab[1] = produitScalaireLongueur * vecteurLongueurY / longueur;
        tab[2] = produitScalaireLargeur * vecteurLargeurX / largeur;
        tab[3] = produitScalaireLargeur * vecteurLargeurY / largeur;
        System.out.println("prdo vect long " + produitScalaireLargeur + " " + vecteurLargeurX + " " + largeur);
        boolean[] conditions = new boolean[4];

        boolean insideLongueur = tab[0] >= 0 && tab[0] <= vecteurLongueurX && tab[1] >= 0 && tab[1] <= vecteurLongueurY;
        boolean insideLargeur = tab[2] >= 0 && tab[2] <= vecteurLargeurX && tab[3] >= 0 && tab[3] <= vecteurLargeurY;

        // conditions[0] = insideLongueur && tab[2] <= 0 && tab[3] <= 0;
        // conditions[1] = insideLongueur && tab[2] >= vecteurLargeurX && tab[3] >=
        // vecteurLargeurY;
        // conditions[2] = insideLargeur && tab[0] <= 0 && tab[1] <= 0;
        // conditions[3] = insideLargeur && tab[0] >= vecteurLongueurX && tab[1] >=
        // vecteurLongueurY;

        // conditions[0] = tab[2] + r <= 0 && tab[3] + r <= 0;// en haut
        // conditions[1] = tab[0] - r >= vecteurLongueurX && tab[1] - r >=
        // vecteurLongueurY;// a droite
        // conditions[2] = tab[2] - r >= vecteurLargeurX && tab[3] - r >=
        // vecteurLargeurY;// en bas
        // conditions[3] = tab[0] + r <= 0 && tab[1] + r <= 0;// a gauche
        System.out.println("RAYON " + r);
        System.out.println("tab[2] " + tab[2]);
        conditions[0] = (tab[2] <= 0 || tab[2] + r <= 0) && (tab[3] <= 0 || tab[3] + r <= 0);// en haut
        conditions[1] = (tab[0] >= vecteurLongueurX || tab[0] + r >= vecteurLongueurX)
                && (tab[1] >= vecteurLongueurY || tab[1] + r >= vecteurLongueurY);// a
        // droite
        conditions[2] = (tab[2] >= vecteurLargeurX || tab[2] + r >= vecteurLargeurX)
                && (tab[3] >= vecteurLargeurY || tab[3] + r >= vecteurLargeurY);// en bas
        conditions[3] = (tab[0] <= 0 || tab[0] + r <= 0) && (tab[1] <= 0 || tab[1] + r <= 0);// a gauche

        return conditions;

    }

    public double produitScalaireLongueur(double x, double y) {
        double vecteurX = x - origineVecteurX;
        double vecteurY = y - origineVecteurY;
        return vecteurX * vecteurLongueurX + vecteurY * vecteurLongueurY;
    }

    public double produitScalaireLargeur(double x, double y) {

        double vecteurX = x - origineVecteurX;
        double vecteurY = y - origineVecteurY;

        double normeVectPoint = (Math
                .sqrt(Math.pow(vecteurX, 2) + Math.pow(vecteurY, 2)));

        double normeVectLargeur = (Math
                .sqrt(Math.pow(vecteurLargeurX, 2) + Math.pow(vecteurLargeurY, 2)));
        double vectLargeurXNormalise = vecteurLargeurX / normeVectLargeur;
        double vectLargeurYNormalise = vecteurLargeurY / normeVectLargeur;
        // System.out.println("vectX vect Y vectLargX vectLargY " + vecteurX + " " +
        // vecteurY + " " + vecteurLargeurX + " "
        // + vecteurLargeurY);
        return vecteurX / normeVectPoint * vectLargeurXNormalise + vecteurY / normeVectPoint * vectLargeurYNormalise;
    }

    public void rotationPegRectangle(double angle) {
        this.angle = angle;
        BufferedImage image = imageLabel;

        final int w = (int) Math.floor(image.getWidth());
        final int h = (int) Math.floor(image.getHeight());
        final BufferedImage rotatedImage = new BufferedImage(w, h, image.getType());
        final AffineTransform at = new AffineTransform();
        at.translate(w / 2, h / 2);
        at.rotate(angle, 0, 0);
        at.translate(-image.getWidth() / 2, -image.getHeight() / 2);
        final AffineTransformOp rotateOp = new AffineTransformOp(at,
                AffineTransformOp.TYPE_BILINEAR);
        rotateOp.filter(image, rotatedImage);
        image = rotatedImage;
        Image newimg = image.getScaledInstance((int) (longueur), (int) (longueur),
                java.awt.Image.SCALE_SMOOTH); // scale
        ImageIcon imageIcon = new ImageIcon(newimg); // transform it back
        // jlabel.setIcon(imageIcon);

        // Image image = imageIcon.getImage();
        // Image newimg = image.getScaledInstance((int) (longueur), (int) (longueur),
        // java.awt.Image.SCALE_SMOOTH); // scale

        // imageIcon = new ImageIcon(newimg);
        jlabel = new LabelPeg(imageIcon);
        // jlabel.setSize(rayon * 2, rayon * 2);
        jlabel.setBounds((int) (pegX - Math.cos(angle) * longueur / 2), (int) (pegY - Math.sin(angle) * largeur / 2),
                (int) longueur,
                (int) longueur);
    }

    @Override
    public void pegTouchdown() {
        if (touched)
            return;
        touched = true;
        // ImageIcon imageIcon = new ImageIcon(
        // PegRectangle.this.getClass().getResource("ressources/peg-" + color +
        // "-animation.gif"));

    }

}
