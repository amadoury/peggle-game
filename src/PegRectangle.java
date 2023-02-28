import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.*;

public class PegRectangle extends Peg {

    private int longueur;
    private int largeur;

    PegRectangle(int x, int y, int lo, int la, String c) {
        super(x, y, c);
        longueur = lo;
        largeur = la;

        ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("ressources/peg-" + color + "-rectangle.png"));
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance((int) (longueur), (int) (longueur), java.awt.Image.SCALE_SMOOTH); // scale

        imageIcon = new ImageIcon(newimg);
        jlabel = new JLabel(imageIcon);
        // jlabel.setSize(rayon * 2, rayon * 2);
        jlabel.setBounds((int) pegX - longueur / 2, (int) pegY - largeur / 2, (int) longueur, (int) largeur);
    }

    @Override
    public void pegTouchdown() {
        // TODO Auto-generated method stub

    }

}
