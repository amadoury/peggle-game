
import javax.swing.* ;
import java.awt.* ;
import java.awt.geom.Line2D ;
import java.util.TimerTask;
import java.util.Timer ;

public class Board extends JPanel {

    private Timer timer ;
    private int x = 25 ;
    private int y = 25 ;
    private Trou trou ;
    private Dimension dimensionBoard ;

    private Image imageBoard ;

    public Board(){
        trou = new Trou(0, 700, 200) ;
        initBoard();
    }

    private void initBoard(){
        loadImage("src/ressources/bgd-peggle-img-1.jpg");
        int width = imageBoard.getWidth(this) ;
        int height = imageBoard.getHeight(this);
        setPreferredSize(new Dimension(width, height));

        //timer : animation 
        final int INTIAL_DELAY = 100 ;
        final int PERIO_INTERVAL = 15 ;
        timer = new Timer() ;
        timer.scheduleAtFixedRate(new ScheduleTask(), INTIAL_DELAY, PERIO_INTERVAL) ;
    }

    private void loadImage(String path){  
        ImageIcon img = new ImageIcon(path) ;
        imageBoard = img.getImage() ;
    }

    @Override
    public void paintComponent(Graphics g){
        
        Graphics2D g2d = (Graphics2D)g ;
        //g2d.drawOval(trou.getX(), trou.getY(), trou.getDiametre(), 40);


        g2d.drawOval(10,  700 ,  100 , 40);


        g2d.drawImage(imageBoard, 0, 0, null) ;

        g2d.setColor(Color.BLUE);
        g2d.setStroke(new BasicStroke(8f)) ;
        g2d.drawLine(x, 10, x, 700) ;

    }

    public void setDimensionBoard(Dimension dim ){
        this.dimensionBoard = dim ;
    }

    private class ScheduleTask extends TimerTask {
        @Override
        public void run(){
            x += 10 ;
            //y += 10 ;

           trou.move((int)dimensionBoard.getWidth()) ;
        
           repaint() ;
        }
    }

}