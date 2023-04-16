
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

public class BoardLeft extends JPanel implements MouseInputListener {
    private JLabel labelNumberBall;
    private JLabel labelPeg;
    private JLabel labelButton;
    private JButton buttonEdit;
    private JLabel labelImgBall;
    private int rayon = 0;
    private double height;
    private double width;

    private JLabel score = new JLabel();
    private int valScore = 0;
    private JLabel jlabel;

    public BoardLeft() {

        setLayout(null);

        score.setText("" + valScore);
        Font font = new Font("Verdana", Font.BOLD, 25);
        score.setFont(font);
        score.setForeground(new Color(174, 222, 246));
        // score.setForeground(Color.WHITE);
        add(score);

        System.out.println(getHeight());

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public void setHeight(double height) {
        this.height = height;
        System.out.println(getWidth());
        setSize((int) width, (int) height);
        ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("ressources/peggleBarreScore.png"));
        imageIcon.setImage(
                imageIcon.getImage().getScaledInstance((int) width, (int) height, java.awt.Image.SCALE_SMOOTH));
        jlabel = new JLabel(imageIcon);

        jlabel.setBounds(0, 0, (int) width, (int) height);
        add(jlabel);
        score.setBounds((int) (width / 5 * 2), 3, 100, 25);
    }

    public void setWidth(double width) {
        this.width = width / 8.;

    }

    public void initalisation() {
        add(jlabel);
    }

    public void upgradeScore(int n) {
        score.setText("" + n);
        repaint();
        // setBackground(Color.WHITE);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println(e.getY());
        if (e.getY() > height * 2 / 3 + 50)
            System.out.println("DESXHDHUSIFEUHFHE");
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

}
