import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.InputStream;
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
    private JLabel jlabel = new JLabel();

    Timer timer;
    private int totalPegOrange;
    private int positionScore;
    private int pegOrangeTouched;

    private Sound sound;

    public BoardLeft(double width, double height) {
        this.width = width / 8.;
        this.height = height;

        String path_font = "ressources/font_style/font.ttf";
        InputStream is;
        Font font;

        try {
            is = LevelMenu.class.getResourceAsStream(path_font);
            font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(25f);
            score.setFont(font);
        } catch (Exception e) {
            e.printStackTrace();
        }

        score.setText("" + valScore);
        // Font font = new Font("Verdana", Font.BOLD, 25);
        // score.setFont(font);
        score.setForeground(new Color(174, 222, 246));
        // score.setForeground(Color.WHITE);
        add(score);

    }

    // public BoardLeft() {
    // this.setLayout(null);
    // labelScorePlayer1.setText("Player 1 : 0");
    // labelScorePlayer2.setText("Player 2 : 0");
    // this.add(labelScorePlayer1);
    // this.add(labelScorePlayer2) ;
    // labelScorePlayer1.setBounds(10, 100, 200, 50);
    // labelScorePlayer2.setBounds(10, 250, 200, 50);
    // }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    // public void updateScore(int scorePlayer1, int scorePlayer2){
    // labelScorePlayer1.setText("Player 1 : " + scorePlayer1);
    // labelScorePlayer2.setText("Player 2 : " + scorePlayer2);

    public void setHeight(double height) {
        this.height = height;
        setSize((int) width, (int) height);
        ImageIcon imageIcon = new ImageIcon(
                this.getClass().getResource("ressources/barreScore/peggleBarreScore-0.png"));
        imageIcon.setImage(
                imageIcon.getImage().getScaledInstance((int) width, (int) height, java.awt.Image.SCALE_SMOOTH));
        jlabel.setIcon(imageIcon);

        jlabel.setBounds(0, 0, (int) width, (int) height);
        add(jlabel);
        score.setBounds((int) (width / 5 * 2), 3, 100, 25);

        ActionListener task = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (positionScore < pegOrangeTouched * 26 / totalPegOrange && positionScore < 26) {
                    ++positionScore;
                    ImageIcon imageIcon = new ImageIcon(this.getClass()
                            .getResource("ressources/barreScore/peggleBarreScore-" + positionScore + ".png"));
                    imageIcon.setImage(
                            imageIcon.getImage().getScaledInstance((int) width, (int) height,
                                    java.awt.Image.SCALE_SMOOTH));
                    jlabel.setIcon(imageIcon);
                    System.out.println("POSITION SCORE &&&&&&&&&&" + positionScore + " " + width + " " + height);
                    // jlabel.setBounds(0, 0, (int) width, (int) height);
                }
            }

        };
        timer = new Timer(50, task);
        timer.setRepeats(true);
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

    public void setTotalPegOrange(int totalPegOrange) {
        this.totalPegOrange = totalPegOrange;
    }

    public void pegOrangeTouched() {
        ++pegOrangeTouched;
    }

    public void restart() {
        positionScore = 0;
        timer.stop();
    }

    public void startTimer() {
        timer.start();
    }

}
