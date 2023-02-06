import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;

import javax.swing.*;

public class PegCercle extends Peg {

    private double rayon;
    private BufferedImage image;
    private JLabel jlabel;
    private String color;

    PegCercle(int x, int y, double r, String c) {// couleur avec majuscue
        super(x, y);
        rayon = r;
        color = c;
        ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("ressources/peg-" + color + "@1x.png"));
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance((int) rayon, (int) rayon, java.awt.Image.SCALE_SMOOTH); // scale it the
                                                                                                       // smooth way
        imageIcon = new ImageIcon(newimg); // transform it back
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
        g.drawOval(pegX, pegY, jlabel.getWidth(), jlabel.getWidth());
        // g.drawImage(image, pegX, pegY, null);
    }

    public JLabel getJlabel() {
        return jlabel;
    }

    @Override
    public void pegTouchdown() {
        ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("ressources/peg-" + color + "-glow@1x.png"));
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg); // transform it back
        jlabel.setIcon(imageIcon);
    }

}
