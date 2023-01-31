import javax.swing.*;
import java.awt.*;

public class BoardCenter extends JPanel {

    private Image imageBoardCenter;

    public BoardCenter() {
        initBoardCenter();
    }

    private void initBoardCenter() {

        loadImage("src/ressources/bgd-peggle-img-1.jpg");
        int width = imageBoardCenter.getWidth(this);
        int height = imageBoardCenter.getHeight(this);
        setPreferredSize(new Dimension(width, height));
    }

    private void loadImage(String path) {
        ImageIcon img = new ImageIcon(path);
        imageBoardCenter = img.getImage();
    }

    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(imageBoardCenter, 0, 100, null);

        g2d.setColor(Color.BLUE);
        g2d.setStroke(new BasicStroke(8f));
        // g2d.drawLine(10, 10, 10, 700);

        // g2d.drawOval(x, y, 200, 40);
    }
}
