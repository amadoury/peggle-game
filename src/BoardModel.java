
import java.util.ArrayList;

public class BoardModel {

    /* for Canon */
    private Canon canon;
    private double thetaCanon;

    /* for Ball */
    public Ball ball;
    private double xInitBall = 0;
    private double yInitBall = 0;
    private double angleChute = -60;
    PegGenerator generator;
    protected BoardMain board;
    private double resolutionScreen;
    public Trou trou;
    private double widthBoard;
    private double heightBoard;
    private boolean gameOver = false;
    protected BoardLeft left;
    int score1 = 0;
    int score2 = 0;
    private int nombreBall = 9;

    private Sound sound;
    protected BoardRight right;

    private int score;

    public BoardModel(int resolutionScreen, BoardMain board, BoardRight right, BoardLeft left, boolean multiPlayer) {
        this.resolutionScreen = resolutionScreen / 100.;
        this.board = board;
        this.right = right;
        this.left = left;
        initBoardModel();
    }

    /* BoardModel initialisation */
    public void initBoardModel() {
        canon = new Canon(0, 25, 70 / resolutionScreen);
        xInitBall = canon.getCanonX();
        yInitBall = canon.getCanonY();
        ball = new Ball(xInitBall, yInitBall, angleChute, (int) (20 / resolutionScreen), nombreBall, this, false);
        generator = new PegGenerator(resolutionScreen, 20);
        trou = new Trou(144, 12, this, resolutionScreen);// meilleure dimension : longeur = 12 x
                                                         // largeur

        board.add(trou.getJlabel());
        ArrayList<String> paths = new ArrayList<String>();
        paths.add("ressources/audio/shot.wav");
        paths.add("ressources/audio/rebond.wav") ;
        sound = new Sound(paths);
        right.setRayon((int) (20 / resolutionScreen));
        right.setNombreBall(nombreBall);
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

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
        if (gameOver) {
            board.requestFocus();
            left.restart();
        }
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

        if (ball.setStartBall(b) && b && !wasStarted) {
            right.ballUsed();
            int orange = 0;
            for (Peg p : generator.getPegListe()) {
                if (p.getColor().equals("orange"))
                    ++orange;
            }
            left.setTotalPegOrange(orange);
            left.startTimer();
        }
    }

    public boolean contact() {
        ArrayList<Peg> listPeg = generator.contact(ball);
        if (listPeg == null)
            return false;
        for (Peg p : listPeg) {
            ball.contactPeg(p);
            ball.setSound(sound);
        }
        return true;
    }

    public void retireAllTouched() {
        generator.retireAllTouched();
    }

    // public void scoreTouchPeg(Peg p) {
    // if (p.touched)
    // return;
    // if (p.color.equals("bleu")) {
    // score += 10;
    // }
    // if (p.color.equals("orange"))
    // score += 100;
    // right.upgradeScore(score);
    // }

    public void scoreTouchPegIA(Peg p) {
        if (p.touched)
            return;
        if (p.color.equals("orange") && ((BoardIA) board).getCurrentPlayer())
            score1++;
        if (p.color.equals("orange") && !((BoardIA) board).getCurrentPlayer())
            score2++;
        // left.updateScore(score1, score2);
    }

    public void ballRestart() {
        right.ballRestart();
    }

    public void touchPeg(Peg p, boolean b) {
        if (p.touched)
            return;
        right.pegTouched();
        if (p.color.equals("bleu")) {
            if (b)
                score1 += 10;
            else
                score2 += 10;
        }
        if (p.color.equals("orange")) {
            if (b)
                score1 += 100;
            else
                score2 += 100;
            left.pegOrangeTouched();
        }
        left.upgradeScore(score1);
    }

    public void setWidthBoard(double widthBoard) {
        this.widthBoard = widthBoard;

        ball.setWidthBoard(widthBoard);
        canon.setOrbX(widthBoard);
        trou.setWidthBoard(widthBoard);
        trou.setHeightBoard(heightBoard);

        // for (int i = 0; i < generator.getPegListe().size(); ++i) {
        // board.remove(generator.getPegListe().get(i).getLabelPeg());
        // }

        for (int i = 0; i < board.getComponentCount(); ++i) {
            if (((Object) board.getComponent(i)).getClass() == Peg.class)
                board.remove(i);
        }

        generator.setWidthBoard(widthBoard);

        // for (int i = 0; i < generator.getPegListe().size(); ++i) {
        // board.add(generator.getPegListe().get(i).getLabelPeg());
        // }

    }

    public void setHeightBoard(double heightBoard) {
        this.heightBoard = heightBoard;
        trou.setHeightBoard(heightBoard);
    }

    public void setPegGenerator(PegGenerator pegGen) {
        this.generator = pegGen;
    }

    public Sound getSound() {
        return sound;
    }

    public void trouFall() {
        right.trouFall();
        ++nombreBall;
    }
}
