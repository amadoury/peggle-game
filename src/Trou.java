public class Trou{; 
    private int x ;
    private int y ;
    private int diametre ;
    private int dx ; //valeur ajoutée a x pour deplacer le trou horizontalement

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

    public void move(int width){
        if (x <= 0){
            x = 0;
        }
        else if (x + diametre + dx < width){
            x += dx ;
        }
        else{
            x -= dx ;
        }
    }

}
