
import javax.swing.*;
import java.awt.*;

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

    public void paintComponent(Graphics g) {

        g.drawImage(imageBoard, 0, 0, null);

    }

}
