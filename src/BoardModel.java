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
    private BoardMain board;
    private double resolutionScreen;
    private Trou trou;
    private double widthBoard ;
    private double heightBoard ;
    private boolean gameOver = false;
    
    private Sound sound ;

    public BoardModel(int resolutionScreen, BoardMain board) {
        this.resolutionScreen = resolutionScreen / 100.;
        this.board = board;
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

        board.add(trou.getJlabel());
        ArrayList<String> paths = new ArrayList<String>() ;
        paths.add("ressources/audio/shot.wav") ;
        paths.add("ressources/audio/rebond.wav") ;
        sound = new Sound(paths) ;
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

    public boolean isGameOver(){
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
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
        ball.setStartBall(b);
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

    public void setWidthBoard(double widthBoard) {
        this.widthBoard = widthBoard;

        ball.setWidthBoard(widthBoard);
        canon.setOrbX(widthBoard);
        trou.setWidthBoard(widthBoard);
        trou.setHeightBoard(heightBoard);

        // for (int i = 0; i < generator.getPegListe().size(); ++i) {
        //     board.remove(generator.getPegListe().get(i).getLabelPeg());
        // }

        // generator.setWidthBoard(widthBoard);

        // for (int i = 0; i < generator.getPegListe().size(); ++i) {
        //     board.add(generator.getPegListe().get(i).getLabelPeg());
        // }

    }

    public void setHeightBoard(double heightBoard) {
        this.heightBoard = heightBoard;
        trou.setHeightBoard(heightBoard);
    }

    public void setPegGenerator(PegGenerator pegGen){
        this.generator = pegGen;
    }

    public Sound getSound(){
        return sound ;
    }

    
}
