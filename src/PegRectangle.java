import java.awt.Graphics2D;

import javax.swing.JLabel;

public class PegRectangle extends Peg {

    private int longueur;
    private int largeur;

    PegRectangle(int x, int y, int lo, int la, String color) {
        super(x, y, color);
        longueur = lo;
        largeur = la;
        // TODO Auto-generated constructor stub
    }

    @Override
    public void delete() {
        // TODO Auto-generated method stub

    }

    @Override
    public void drawPeg(Graphics2D g) {
        // TODO Auto-generated method stub

    }

    @Override
    public JLabel getJlabel() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void pegTouchdown() {
        // TODO Auto-generated method stub

    }

}
