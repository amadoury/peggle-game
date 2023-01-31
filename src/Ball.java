public class Bille {
    private double xt ; //position x de la bille à chaque instant 
    private double yt ; //position y de la bille à chaque instant
    private double x_initial ; // position x initial de la bille
    private double y_initial ; //position y initial de la bille
    private double thetha ; //angle 
    private double vInitial ; //vitesse initiale
    private final double g = 9.81 ; //acceleration de la pesanteur

    public Bille(double x_initial, double y_initial, double vInitial, double thetha) {
        this.x_initial = x_initial ;
        this.y_initial = y_initial ;
        this.vInitial = vInitial ;
        this.thetha = thetha ;
    }

    public double XInitial(){
        return x_initial; 
    }

    public double YInitial(){
        return y_initial;
    }

    public void setXt(double xt){
        this.xt = xt;
    }

    public void setYt(double yt){
        this.yt = yt ;
    }

    public double getXt() {
        return xt;
    }

    public double getYt() {
        return yt;
    }

    public void setTheta(double thetha){
        this.thetha = thetha ;
    }

    //met à jour les coordonnées de la bille à l'instant dt
    public void move(int dt){
        xt = xt(dt); 
        yt = yt(dt) ;
    }

    //calcule la position x de la bille à l'instant dt passé en argumant
    private double xt(int dt){
        return vInitial * Math.sin(thetha) * dt ;
    }

    //calcule la position y de la bille à l'instant dt passé en argumant
    private double yt(int dt){
        return 0.5 * g * (dt * dt) + (vInitial * Math.cos(thetha) * dt) ;
    }

}
