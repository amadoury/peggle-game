import java.awt.*;

import javax.swing.*;

public class Trou {

    private double x;
    private double y;
    private int longueur;// meilleure dimension : longeur = 12 x largeur
    private int largeur;
    private double longueurImage;
    private double largeurImage;
    private double dx = 2; // valeur ajoutée a x pour deplacer le trou horizontalement
    private JLabel labelImgBall;
    private BoardModel boardModel;
    private double widthBoard;
    // private double heightBoard;
    private JLabel jlabel;
    private double vitesse = 1;
    private double resolutionScreen;

    public Trou(int lo, int la, BoardModel bm, double res) {
        longueur = lo;
        largeur = la;
        boardModel = bm;
        longueurImage = longueur * 1.5;
        largeurImage = largeur * 5;
        x = longueurImage / 2;
        resolutionScreen = res;

        ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("ressources/trou.png"));
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance((int) (longueurImage), (int) (largeurImage),
                java.awt.Image.SCALE_SMOOTH); // scale
        imageIcon = new ImageIcon(newimg);
        jlabel = new JLabel(imageIcon);
        jlabel.setBounds((int) (x - longueurImage / 2), (int) (y - largeurImage / 2), (int) longueurImage,
                (int) largeurImage);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getLargeur() {
        return largeur;
    }

    public int getLongueur() {
        return longueur;
    }

    public double getLongueurImage() {
        return longueurImage;
    }

    public double getLargeurImage() {
        return largeurImage;
    }

    public JLabel getJlabel() {
        return jlabel;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setHeightBoard(double heightBoard) {
        // this.heightBoard = heightBoard;
        y = (int) heightBoard - 96 * resolutionScreen;
    }

    public void setWidthBoard(double widthBoard) {
        this.widthBoard = widthBoard;
        x = widthBoard / 2;
    }

    public void move() {
        if (x + longueurImage / 2 + dx > widthBoard || x - longueurImage / 2 < 0) {
            dx = -dx;
        }
        if (x > widthBoard / 2 && dx > 0)
            vitesse -= 0.001;
        if (x > widthBoard / 2 && dx < 0)
            vitesse += 0.001;
        if (x < widthBoard / 2 && dx > 0)
            vitesse += 0.001;
        if (x < widthBoard / 2 && dx < 0)
            vitesse -= 0.001;
        x += vitesse * vitesse * dx;

        jlabel.setBounds((int) (x - longueurImage / 2), (int) (y - largeurImage / 2), (int) longueurImage,
                (int) largeurImage);
    }

    public boolean contactTrou(Ball b) {
        return b.getYt() >= y - largeur / 2 && b.getXt() < x + longueur / 2 && b.getXt() > x - longueur / 2;
    }

}
