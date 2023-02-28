import java.awt.Graphics2D;

import javax.swing.JLabel;

public abstract class Peg {

    protected int pegX;
    protected int pegY;
    protected boolean touched;
    protected boolean destructed;
    protected String color ;
    protected JLabel jlabel;

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

    public String toString(){
        return "Peg : " + color + " PegX : " + pegX + " PegY : "+ pegY ; 
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

    public abstract void delete();

    public abstract void drawPeg(Graphics2D g);

    public abstract JLabel getJlabel();

    public abstract void pegTouchdown();
}
