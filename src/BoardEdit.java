import javax.swing.* ;
import java.awt.* ;
import java.util.TimerTask;
import java.util.Timer;


public class BoardEdit extends Board {

    private Image imageBoard;

    public BoardEdit(){
        loadImage("src/ressources/bgd-peggle-img-1.jpg");
        int width = imageBoard.getWidth(this);
        int height = imageBoard.getHeight(this);
        setPreferredSize(new Dimension(width, height));
        setLayout(null);

                // timer : animation
                final int INITIAL_DELAY = 100;
                final int PERIOD_INTERVAL = 15;
                Timer timer = new Timer();
                timer.scheduleAtFixedRate(new ScheduleTask(), INITIAL_DELAY, PERIOD_INTERVAL);
    }

    private void loadImage(String path) {
        ImageIcon img = new ImageIcon(path);
        imageBoard = img.getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        g2d = (Graphics2D) g;

        g2d.drawImage(imageBoard, 0, 0, null);
    }

    private class ScheduleTask extends TimerTask {
        @Override
        public void run() {
            repaint();
        }
    }

}
