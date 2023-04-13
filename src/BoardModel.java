import java.rmi.server.RemoteServer;
import java.util.ArrayList;

public class BoardModel {

    /* for Canon */
    private Canon canon;
    private double thetaCanon;

    /* for Ball */
    private Ball ball;
    private double xInitBall = 0;
    private double yInitBall = 0;
    private double angleChute = -60;
    PegGenerator generator;
    private double widthBoard;
    private double heightBoard;
    private BoardMain board;
    private double resolutionScreen;
    private Trou trou;
    private BoardRight right;

    private int score;

    public BoardModel(int resolutionScreen, BoardMain board, BoardRight right) {
        this.resolutionScreen = resolutionScreen / 100.;
        this.board = board;
        this.right = right;
        initBoardModel();
    }

    /* BoardModel initialisation */
    public void initBoardModel() {
        canon = new Canon(0, 25, 70 / resolutionScreen);
        xInitBall = canon.getCanonX();
        yInitBall = canon.getCanonY();
        ball = new Ball(xInitBall, yInitBall, angleChute, (int) (20 / resolutionScreen), 15, this);
        generator = new PegGenerator(resolutionScreen, 20);
        trou = new Trou(144, 12, this, resolutionScreen);// meilleure dimension : longeur = 12 x
                                                         // largeur
        right.setRayon((int) (20 / resolutionScreen));
    }

    public Canon getCanon() {
        return canon;
    }

    public Ball getBall() {
        return ball;
    }

    public void setThetaCanon(double theta) {
        this.thetaCanon = theta;
    }

    public void setAngleChute(double angleChute) {
        this.angleChute = angleChute;
        ball.setTheta(angleChute);
    }

    public double getThetaCanon() {
        return thetaCanon;
    }

    public PegGenerator getGenerator() {
        return generator;
    }

    public Trou getTrou() {
        return trou;
    }

    public void updateBoardModel() {
        ball.updateBall();
        contact();
        trou.move();
    }

    public void setBallStart(boolean b) {
        boolean wasStarted = ball.isBallStart();
        ball.setStartBall(b);
        if (b && !wasStarted)
            right.ballUsed();
    }

    public boolean contact() {
        ArrayList<Peg> listPeg = generator.contact(ball);
        if (listPeg == null)
            return false;
        for (Peg p : listPeg) {
            ball.contactPeg(p);
        }
        return true;
    }

    public void retireAllTouched() {
        generator.retireAllTouched();
    }

    public void scoreTouchPeg(Peg p) {
        if (p.touched)
            return;
        if (p.color.equals("bleu")) {
            score += 10;
            return;
        }
        if (p.color.equals("orange"))
            score += 100;
        right.upgradeScore(score);
    }

    public void setWidthBoard(double widthBoard) {
        this.widthBoard = widthBoard;

        ball.setWidthBoard(widthBoard);
        canon.setOrbX(widthBoard);
        trou.setWidthBoard(widthBoard);
        trou.setHeightBoard(heightBoard);

        for (int i = 0; i < generator.getPegListe().size(); ++i) {
            board.remove(generator.getPegListe().get(i).getLabelPeg());
        }

        for (int i = 0; i < board.getComponentCount(); ++i) {
            if (((Object) board.getComponent(i)).getClass() == Peg.class)
                board.remove(i);
        }

        generator.setWidthBoard(widthBoard);

        for (int i = 0; i < generator.getPegListe().size(); ++i) {
            board.add(generator.getPegListe().get(i).getLabelPeg());
        }

        board.add(trou.getJlabel());
    }

    public void setHeightBoard(double heightBoard) {
        this.heightBoard = heightBoard;
        trou.setHeightBoard(heightBoard);
    }

}
