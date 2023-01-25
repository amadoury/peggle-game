public class Trou{; 
    private int x ;
    private int y ;
    private int diametre ;

    public Trou(int x, int y, int d){
        this.x = x ;
        this.y = y ;
        this.diametre = d ;
    }

    public int getX(){
        return x ;
    }

    public int getY(){
        return y;
    }

    public int getDiametre(){
        return diametre ;
    }

    public void setX(int x){
        this.x = x ;
    }

    public void setY(int y){
        this.y = y ;
    }

    public void setDiametre(int d){
        this.diametre = d ;
    } 
}
