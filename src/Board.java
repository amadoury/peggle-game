
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.TimerTask;
import java.util.Timer;

public class Board extends JPanel {

    private Image imageBoard;

    public Board() {
        initBoard();
    }

    private void initBoard() {
        loadImage("src/ressources/bgd-peggle-img-1.jpg");
        int width = imageBoard.getWidth(this);
        int height = imageBoard.getHeight(this);
        setPreferredSize(new Dimension(width, height));
    }

    private void loadImage(String path) {
        ImageIcon img = new ImageIcon(path);
        imageBoard = img.getImage();
    }

    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(imageBoard, 0, 0, null);

        g2d.setColor(Color.BLUE);
        g2d.setStroke(new BasicStroke(8f));
        g2d.drawLine(10, 10, 10, 700);

        g2d.drawOval(x, y, 200, 40);
    }

    private class ScheduleTask extends TimerTask {
        @Override
        public void run() {
            x += 10;
            y += 10;

            repaint();
        }
    }

}