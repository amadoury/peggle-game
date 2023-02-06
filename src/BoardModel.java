public class BoardModel {

    /* for Canon */
    private Canon canon ;
    private double thetaCanon ;

    /* for Ball */
    private Ball ball ;
    private double xInitBall = 0 ;
    private double yInitBall = 0 ;
    private double angleChute = -60;

    private final double vInitial = 200 ; /* initial speed of the ball */


    public BoardModel () {
        initBoardModel() ;
    }   
    
    /* BoardModel initialisation */
    public void initBoardModel() {
        canon = new Canon(0, 0, 50);
        xInitBall  = canon.getCanonX() ;
        yInitBall = canon.getCanonY() ;
        ball = new Ball(xInitBall, yInitBall, angleChute) ;
    }

    public Canon getCanon(){
        return canon ;
    }

    public Ball getBall() {
        return ball ;
    }

    public void setThetaCanon(double theta){
        this.thetaCanon = theta ;
    }

    public double getThetaCanon() {
        return thetaCanon;
    }

}
