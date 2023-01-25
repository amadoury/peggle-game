
import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {

    private Image imageBoard;
    private JPanel top = new JPanel();
    private JPanel bottom = new JPanel();
    private JPanel center = new JPanel();

    public Board() {
        initBoard();
    }

    private void initBoard() {
        loadImage("src/ressources/bgd-peggle-img-1.jpg");

        int width = imageBoard.getWidth(this);
        int height = imageBoard.getHeight(this);
        setPreferredSize(new Dimension(width, height));

        setLayout(new GridBagLayout());

        // GridBagConstraints c = new GridBagConstraints();

        // c.fill = GridBagConstraints.VERTICAL;

        // c.weightx = 0.5;
        // c.gridx = 0;
        // c.gridy = 0;
        // c.ipadx = width;
        // add(top, c);

        // c.fill = GridBagConstraints.VERTICAL;
        // c.weightx = 3;
        // c.ipady = width;

        // c.gridx = 0;
        // c.gridy = 1;
        // c.ipadx = width;
        // add(center, c);

        // c.fill = GridBagConstraints.VERTICAL;
        // c.weightx = 0.5;
        // c.gridx = 0;
        // c.gridy = 2;
        // c.ipadx = width;
        // add(bottom, c);

    }

    private void loadImage(String path) {
        ImageIcon img = new ImageIcon(path);
        imageBoard = img.getImage();
    }

    public void paintComponent(Graphics g) {

        g.drawImage(imageBoard, 0, 0, null);

    }

}
