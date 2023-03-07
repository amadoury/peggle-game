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
    private double widthBoard ;
    private double heightBoard ;
    private boolean gameOver = false;


    public BoardModel(int resolutionScreen) {
        initBoardModel(resolutionScreen);
    }

    /* BoardModel initialisation */
    public void initBoardModel(int resolutionScreen) {
        canon = new Canon(0, 25, 50);
        xInitBall = canon.getCanonX();
        yInitBall = canon.getCanonY();
        ball = new Ball(xInitBall, yInitBall, angleChute, 12, this);
        generator = new PegGenerator(resolutionScreen);
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

    public void updateBoardModel(double dt) {
        ball.updateBall(dt);
    }

    public void setBallStart(boolean b) {
        ball.setStartBall(b);
    }

    public void contact() {
        ball.contactPeg(generator.contact(ball));
    }

    public void retireAllTouched() {
        generator.retireAllTouched();
    }

    public void setWidthBoard(double widthBoard) {
        this.widthBoard = widthBoard;

        ball.setWidthBoard(widthBoard);
        canon.setOrbX(widthBoard);

    }

    public void setHeightBoard(double heightBoard) {
        this.heightBoard = heightBoard;
    }
}
