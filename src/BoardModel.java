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
    private Board board;
    private double resolutionScreen;
    private Trou trou;

    public BoardModel(int resolutionScreen, Board board) {
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

    public void updateBoardModel(double dt) {
        ball.updateBall(dt);
        trou.move();
    }

    public void setBallStart(boolean b) {
        ball.setStartBall(b);
    }

    public boolean contact() {
        return ball.contactPeg(generator.contact(ball));
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

        for (int i = 0; i < generator.getPegListe().size(); ++i) {
            board.remove(generator.getPegListe().get(i).getJlabel());
        }

        generator.setWidthBoard(widthBoard);

        for (int i = 0; i < generator.getPegListe().size(); ++i) {
            board.add(generator.getPegListe().get(i).getJlabel());
        }

        board.add(trou.getJlabel());
    }

    public void setHeightBoard(double heightBoard) {
        this.heightBoard = heightBoard;
        trou.setHeightBoard(heightBoard);
    }

}
