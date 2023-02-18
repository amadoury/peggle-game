import java.awt.*;
import javax.swing.*;

public class PegCercle extends Peg {

    private int rayon;
    private LabelPeg jlabel ;
    private String color;

    PegCercle(int x, int y, int r, String c) {// couleur avec majuscue
        super(x, y);
        rayon = r;
        color = c;
        ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("ressources/peg-" + color + ".png"));
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance((int)(2 * rayon) , (int) (2 * rayon), Image.SCALE_SMOOTH); // scale
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

    public void setRayon(int rayon) {
        this.rayon = rayon;
    }

    @Override
    public void drawPeg(Graphics2D g) {
        // g.drawOval(pegX, pegY, (int) rayon, (int) rayon);
        g.drawOval(pegX, pegY, jlabel.getWidth(), jlabel.getWidth());
        // g.drawImage(image, pegX, pegY, null);
    }

    public LabelPeg getJlabel() {
        return jlabel;
    }

    @Override
    public void pegTouchdown() {
        if (touched)
            return;
        touched = true;
        ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("ressources/peg-" + color + "-glow.png"));
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(rayon * 2, rayon * 2,Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg); // transform it back
        jlabel.setIcon(imageIcon);
    }

    @Override
    public void delete() {
        jlabel.setIcon(null);
    }

}
