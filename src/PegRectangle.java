
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    protected Timer timer = new Timer(5000, null);

    PegRectangle(int x, int y, double lo, double la, double angle, String c) {
        super(x, y, c);
        longueur = (int) lo;
        largeur = (int) la;
        this.angle = angle;

        ActionListener task = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                destructed = true;
                delete();
                timer.stop();
            }

        };
        timer = new Timer(4000, task);
        timer.setRepeats(false);

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
        // System.out.println(" vect " + vecteurLargeurX + " " + vecteurLargeurY);
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
                (int) (Math.abs(Math.sin(angle) * largeur / 2) + longueur),
                (int) (Math.abs(Math.sin(angle) * largeur / 2) + longueur),
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

        // jlabel.setBounds(
        // (int) (pegX - Math.abs(Math.cos(angle) * longueur / 2) -
        // Math.abs(Math.sin(angle) * largeur / 2)),
        // (int) (pegY - Math.abs(Math.sin(angle) * largeur / 2) -
        // Math.abs(Math.cos(angle) * longueur / 2)),
        // (int) longueur,
        // (int) longueur);

        double carreLength = Math.sqrt(longueur * longueur + largeur * largeur);
        jlabel.setBounds(
                (int) (pegX - carreLength / 2),
                (int) (pegY - carreLength / 2),
                (int) carreLength,
                (int) carreLength);
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

    public boolean[] projectionBallOrigineVecteurs(double x, double y, double r, boolean started) {

        double vecteurX = x - origineVecteurX;
        double vecteurY = y - origineVecteurY;

        double normeVectLongueur = (Math
                .sqrt(Math.pow(vecteurLongueurX, 2) + Math.pow(vecteurLongueurY, 2)));
        double vectLongueurXNormalise = vecteurLongueurX / normeVectLongueur;
        double vectLongueurYNormalise = vecteurLongueurY / normeVectLongueur;

        double normeVectLargeur = (Math
                .sqrt(Math.pow(vecteurLargeurX, 2) + Math.pow(vecteurLargeurY, 2)));
        double vectLargeurXNormalise = vecteurLargeurX / normeVectLargeur;
        double vectLargeurYNormalise = vecteurLargeurY / normeVectLargeur;

        double produitScalaireLongueur = vecteurX * vectLongueurXNormalise + vecteurY * vectLongueurYNormalise;
        double produitScalaireLargeur = vecteurX * vectLargeurXNormalise + vecteurY * vectLargeurYNormalise;

        boolean[] conditions = new boolean[4];

        conditions[0] = (produitScalaireLargeur <= 0 || produitScalaireLargeur + r <= 0);// en haut
        conditions[1] = (produitScalaireLongueur >= normeVectLongueur
                || produitScalaireLongueur - r >= normeVectLongueur);// a droite
        conditions[2] = (produitScalaireLargeur >= normeVectLargeur
                || produitScalaireLargeur - r >= normeVectLargeur);// en bas
        conditions[3] = (produitScalaireLongueur <= 0 || produitScalaireLongueur + r <= 0);// a gauche

        return conditions;

    }

    public double produitScalaireLongueur(double x, double y) {
        double vecteurX = x;
        double vecteurY = y;

        double normeVectLongueur = (Math
                .sqrt(Math.pow(vecteurLongueurX, 2) + Math.pow(vecteurLongueurY, 2)));
        double vectLongueurXNormalise = vecteurLongueurX / normeVectLongueur;
        double vectLongueurYNormalise = vecteurLongueurY / normeVectLongueur;

        return vecteurX * vectLongueurXNormalise + vecteurY * vectLongueurYNormalise;
    }

    public double produitScalaireLargeur(double x, double y) {

        double vecteurX = x;
        double vecteurY = y;

        double normeVectLargeur = (Math
                .sqrt(Math.pow(vecteurLargeurX, 2) + Math.pow(vecteurLargeurY, 2)));
        double vectLargeurXNormalise = vecteurLargeurX / normeVectLargeur;
        double vectLargeurYNormalise = vecteurLargeurY / normeVectLargeur;

        return vecteurX * vectLargeurXNormalise + vecteurY * vectLargeurYNormalise;
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

        jlabel = new LabelPeg(imageIcon);
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

    public void touchTimeStart() {
        timer.start();
    }

}
