public abstract class Peg {

    protected int pegX;
    protected int pegY;
    protected String color;
    protected boolean touched;
    protected boolean destructed;
    protected LabelPeg jlabel;
    protected String player = "";

    Peg(int x, int y, String c) {
        pegX = x;
        pegY = y;
        color = c;
    }

    public String toString() {
        return "pegX = " + pegX + "; pegY = " + pegY + "; color = " + color + "; touched = " + touched
                + "; destructed = " + destructed;
    }

    public double getPegX() {
        return pegX;
    }

    public double getPegY() {
        return pegY;
    }

    public String getColor() {
        return color;
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

    public LabelPeg getLabelPeg() {
        return jlabel;
    }

    public void actualisePeg() {
    }

    public void touchTimeStart() {
    }
}