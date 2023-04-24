import javax.swing.event.MouseInputListener;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Board extends JPanel implements MouseInputListener {
    Graphics2D g2d;
    protected Image imageBoard;
    public double width;
    public double height;
    protected double resolutionScreen = Toolkit.getDefaultToolkit().getScreenResolution();

    public Board() {

        try {
            imageBoard = ImageIO.read(this.getClass().getResource("ressources/bgd-peggle-img-1.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        width = imageBoard.getWidth(this);
        height = imageBoard.getHeight(this);

        System.out.println("image " + width + "height " + height);

        // width = dim.getWidth() ;
        // height = dim.getHeight();

        setPreferredSize(new Dimension((int) width, (int) height));
        setLayout(null);

        addMouseListener(this);
        addMouseMotionListener(this);
    }

    // public void setWidthScreen(double w) {
    // double var = w - (2.0 / 8.0) * w;
    // width = var;
    // }

    // public void setHeightScreen(double w) {
    // height = w;
    // }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub

    }
}
