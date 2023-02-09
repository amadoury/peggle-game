import java.awt.Graphics2D;

import javax.swing.JLabel;

public abstract class Peg {

    protected int pegX;
    protected int pegY;
    protected boolean touched;

    Peg(int x, int y) {
        pegX = x;
        pegY = y;
    }

    public double getPegX() {
        return pegX;
    }

    public double getPegY() {
        return pegY;
    }

    public void setPegX(int pegX) {
        this.pegX = pegX;
    }

    public void setPegY(int pegY) {
        this.pegY = pegY;
    }

    public void updatePeg() {
        if (touched)
            delete();
    }

    public abstract void delete();

    public abstract void drawPeg(Graphics2D g);

    public abstract JLabel getJlabel();

    public abstract void pegTouchdown();

}
