import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BoardLeft extends JPanel {
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
        setLayout(null);
        score.setText("" + valScore);
        score.setFont(new Font("Verdana", Font.BOLD, 25));
        score.setForeground(new Color(174, 222, 246));
        add(score);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

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

        ArrayList<String> paths = new ArrayList<String>();
        paths.add("ressources/audio/scoreUp.wav");
        sound = new Sound(paths);

        ActionListener task = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (totalPegOrange == 0)
                    return;
                if (positionScore < pegOrangeTouched * 26 / totalPegOrange && positionScore < 26) {
                    ++positionScore;
                    ImageIcon imageIcon = new ImageIcon(this.getClass()
                            .getResource("ressources/barreScore/peggleBarreScore-" + positionScore + ".png"));
                    imageIcon.setImage(
                            imageIcon.getImage().getScaledInstance((int) width, (int) height,
                                    java.awt.Image.SCALE_SMOOTH));
                    jlabel.setIcon(imageIcon);
                    sound.setFile(0);
                    sound.play();
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

    public void setTotalPegOrange(int totalPegOrange) {
        this.totalPegOrange = totalPegOrange;
    }

    public void pegOrangeTouched() {
        ++pegOrangeTouched;
    }

    public void startTimer() {
        timer.start();
    }

    public boolean barreMax() {
        return positionScore == 26;
    }

}
