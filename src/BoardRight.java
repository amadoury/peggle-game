import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BoardRight extends JPanel {
    private JLabel labelNumberBall;
    private JLabel labelPeg;
    private JLabel labelButton;
    private JButton buttonEdit;
    private JLabel labelImgBall;
    int rayon = 12;
    private double height;
    private ArrayList<JLabel> listLabelBall;
    private JLabel score = new JLabel();
    private int valScore = 0;

    public BoardRight(int number) {
        listLabelBall = new ArrayList<JLabel>();

        setLayout(null);

        int x, y;
        x = 55;
        y = 55;

        for (int i = 0; i < number; i++) {
            ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("ressources/ball.png"));
            labelImgBall = new JLabel(imageIcon);

            Image image = imageIcon.getImage(); // transform it
            Image newimg = image.getScaledInstance(2 * rayon, 2 * rayon, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg); // transform it back
            labelImgBall.setIcon(imageIcon);

            listLabelBall.add(labelImgBall);

            labelImgBall.setBounds(x - rayon, y - rayon, 2 * rayon, 2 * rayon);

            y += 2 * rayon + 5;

            add(labelImgBall);
            // labelImgBall.setLocation(new Point(50, 50));
        }

        score.setBounds(50, 500, 100, 100);
        score.setText("SCORE : " + valScore);
        add(score);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public void updateLabelBall(int number) {
        labelNumberBall.setText(number + "");
    }

    public void setNumberBall(int number) {
        this.labelNumberBall.setText(number + " ");
    }

    public void setLabelBall(String s) {
        this.labelNumberBall.setText(s);
    }

    public void setHeight(double height) {
        this.height = height;
        System.out.println(getWidth());
        setSize(0, (int) height);
    }

    public void ballUsed() {
        if (listLabelBall.size() == 0)
            return;
        remove(listLabelBall.get(0));
        listLabelBall.remove(0);
        repaint();
    }

    public void upgradeScore(int n) {
        score.setText("SCORE : " + n);
        repaint();
    }
}