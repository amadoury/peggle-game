import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PegCercle extends Peg {

    protected int rayon;
    protected Timer timer;

    PegCercle(int x, int y, int r, String c) {// couleur avec majuscue
        super(x, y, c);
        rayon = r;
        color = c;

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

        ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("ressources/peg-" + color + ".png"));
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance((int) (2 * rayon), (int) (2 * rayon), java.awt.Image.SCALE_SMOOTH); // scale
        // it the
        // smooth way
        imageIcon = new ImageIcon(newimg); // transform it back
        jlabel = new LabelPeg(imageIcon);
        // jlabel.setSize(rayon * 2, rayon * 2);
        jlabel.setBounds((int) pegX - rayon, (int) pegY - rayon, (int) 2 * rayon, (int) 2 * rayon);
    }

    public int getRayon() {
        return rayon;
    }

    public String getColor() {
        return this.color;
    }

    public void setRayon(int rayon) {
        this.rayon = rayon;
    }

    @Override
    public void pegTouchdown() {
        if (touched)
            return;
        touched = true;
        ImageIcon imageIcon = new ImageIcon(
                PegCercle.this.getClass().getResource("ressources/peg-" + color + "-animation.gif"));
        // Image image = imageIcon.getImage(); // transform it
        // Image newimg = image.getScaledInstance(rayon * 2, rayon * 2,
        // java.awt.Image.SCALE_SMOOTH); // scale it the
        // // smooth way
        // imageIcon = new ImageIcon(newimg); // transform it back
        jlabel.setBounds((int) (pegX - 1.5 * rayon), (int) (pegY - 1.5 * rayon), (int) 3 * rayon, (int) 3 * rayon);
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(3 * rayon, 3 * rayon, Image.SCALE_DEFAULT));
        jlabel.setIcon(imageIcon);
    }

    public void touchTimeStart() {
        timer.start();
    }

}
