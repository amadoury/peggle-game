import java.awt.Graphics2D;

import javax.swing.JLabel;

public abstract class Peg {

    protected int pegX;
    protected int pegY;
    protected boolean touched;
    protected boolean destructed;
    protected JLabel jlabel;
    protected String color;

    Peg(int x, int y, String c) {
        pegX = x;
        pegY = y;
        color = c;
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
        if (touched) {
            delete();
            destructed = true;
        }

    }

    public boolean isTouched() {
        return touched;
    }

    public boolean isDestructed() {
        return destructed;
    }

    public void delete() {
        jlabel.setIcon(null);
    }

    public abstract void pegTouchdown();

    public JLabel getJlabel() {
        return jlabel;
    }

}
