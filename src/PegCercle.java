import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;

import javax.swing.*;

public class PegCercle extends Peg {

    private double rayon;
    private BufferedImage image;
    private JLabel jlabel;

    PegCercle(int x, int y, double r, String color) {// couleur avec majuscue
        super(x, y);
        rayon = r;
        Icon imageIcon = new ImageIcon(this.getClass().getResource("ressources/peggle" + color + ".png"));
        jlabel = new JLabel(imageIcon);
        jlabel.setSize(50, 50);
        jlabel.setBounds((int) pegX, (int) pegY, (int) rayon, (int) rayon);
    }

    public double getRayon() {
        return rayon;
    }

    public void setRayon(double rayon) {
        this.rayon = rayon;
    }

    @Override
    public void drawPeg(Graphics2D g) {
        // TODO Auto-generated method stub
        // g.drawOval(pegX, pegY, (int) rayon, (int) rayon);
        g.drawImage(image, pegX, pegY, null);
    }

    public JLabel getJlabel() {
        return jlabel;
    }

}
