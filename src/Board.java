
import javax.swing.* ;
import java.awt.* ;


public class Board extends JPanel {

    private Image imageBoard ;
    private Dimension dimFrameApp ;

    public Board(Dimension dimFrameApp){
        initBoard();
        this.dimFrameApp = dimFrameApp ;
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
        
        g.drawImage(imageBoard, 0, 0, null) ;
        double w = dimFrameApp.getWidth() ;
        double h = dimFrameApp.getHeight() ;

        g.drawLine((int)(w / 10) , 0, (int)(w / 10) , (int)h) ;

    }

}