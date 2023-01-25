
import javax.swing.* ;
import java.awt.* ;
import java.awt.geom.Line2D ;

public class Board extends JPanel {

    private Image imageBoard ;

    public Board(){
        initBoard();
    }

    private void initBoard(){
        loadImage("src/ressources/bgd-peggle-img-1.jpg");
        int width = imageBoard.getWidth(this) ;
        int height = imageBoard.getHeight(this);
        setPreferredSize(new Dimension(width, height));
    }

    private void loadImage(String path){  
        ImageIcon img = new ImageIcon(path) ;
        imageBoard = img.getImage() ;
    }

    public void paintComponent(Graphics g){
        
        Graphics2D g2d = (Graphics2D)g ;

        g2d.drawImage(imageBoard, 0, 0, null) ;
        // double w = dimFrameApp.getWidth() ;
        // double h = dimFrameApp.getHeight() ;
        // System.out.println(w + " " + h);

        // g2d.setColor(Color.BLUE);
        // g2d.setStroke(new BasicStroke(2f)) ;
        // g2d.draw(new Line2D.Double(w / 10, 0 ,w / 10, h)) ;

        g2d.setColor(Color.BLUE);
        g2d.setStroke(new BasicStroke(8f)) ;
        g2d.drawLine(10, 10, 10, 700) ;

        g2d.drawOval(100, 100, 200, 40);
    }

}