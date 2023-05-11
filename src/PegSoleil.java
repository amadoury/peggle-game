import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class PegSoleil extends PegCercle {

    private int etat;

    PegSoleil(int x, int y, int r, String c) {
        super(x, y, r, c);

        ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("ressources/peg-soleil.png"));
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance((int) (2 * rayon), (int) (2 * rayon), java.awt.Image.SCALE_SMOOTH); // scale
        // it the
        // smooth way
        imageIcon = new ImageIcon(newimg); // transform it back
        jlabel = new LabelPeg(imageIcon);
        jlabel.setBounds((int) pegX - rayon, (int) pegY - rayon, (int) 2 * rayon, (int) 2 * rayon);
    }

    @Override
    public void pegTouchdown() {
        // TODO Auto-generated method stub
        ++etat;
        if (etat == 1) {
            ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("ressources/peg-soleil1.png"));
            jlabel.setBounds((int) pegX - rayon, (int) pegY - rayon, (int) 2 * rayon, (int) 2 * rayon);
            imageIcon.setImage(imageIcon.getImage().getScaledInstance((int) (2 * rayon), (int) (2 * rayon),
                    java.awt.Image.SCALE_SMOOTH));
            jlabel.setIcon(imageIcon);

        } else if (etat == 2) {
            ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("ressources/peg-soleil2.png"));
            jlabel.setBounds((int) pegX - rayon, (int) pegY - rayon, (int) 2 * rayon, (int) 2 * rayon);
            imageIcon.setImage(imageIcon.getImage().getScaledInstance((int) (2 * rayon), (int) (2 * rayon),
                    java.awt.Image.SCALE_SMOOTH));
            jlabel.setIcon(imageIcon);
        } else if (etat == 3) {
            destructed = true;

            ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("ressources/peg-soleil3.png"));
            jlabel.setBounds((int) pegX - rayon, (int) pegY - rayon, (int) 2 * rayon, (int) 2 * rayon);
            imageIcon.setImage(imageIcon.getImage().getScaledInstance((int) (2 * rayon), (int) (2 * rayon),
                    java.awt.Image.SCALE_SMOOTH));
            jlabel.setIcon(imageIcon);
        } else
            return;
    }

}
