import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class PegRebond extends PegCercle {

    PegRebond(int x, int y, int r, String c) {
        super(x, y, r, c);
        ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("ressources/peg-rebond.png"));
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance((int) (2 * rayon), (int) (2 * rayon), java.awt.Image.SCALE_SMOOTH); // scale
        // it the
        // smooth way
        imageIcon = new ImageIcon(newimg); // transform it back
        jlabel = new LabelPeg(imageIcon);
        jlabel.setBounds((int) pegX - rayon, (int) pegY - rayon, (int) 2 * rayon, (int) 2 * rayon);
        ArrayList<String> l = new ArrayList<String>();
        l.add("ressources/audio/pegRebond.wav");
        sound = new Sound(l);
        sound.setFile(0);
    }

    public void pegTouchdown() {
        ImageIcon imageIcon = new ImageIcon(
                this.getClass().getResource("ressources/peg-rebond-animation.gif"));
        // Image image = imageIcon.getImage(); // transform it
        // Image newimg = image.getScaledInstance(rayon * 2, rayon * 2,
        // java.awt.Image.SCALE_SMOOTH); // scale it the
        // // smooth way
        // imageIcon = new ImageIcon(newimg); // transform it back
        jlabel.setBounds((int) (pegX - 1.75 * rayon), (int) (pegY - 1.75 * rayon),
                (int) (3.5 * rayon), (int) (3.5 * rayon));
        imageIcon.setImage(
                imageIcon.getImage().getScaledInstance((int) (3.5 * rayon), (int) (3.5 * rayon), Image.SCALE_DEFAULT));
        jlabel.setIcon(imageIcon);
        sound.setFile(0);
        sound.play();
    }

}
