import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

public class BoardRight extends JPanel implements MouseInputListener {
    private JLabel labelNumberBall;
    private JLabel labelPeg;
    private JLabel labelButton;
    private JButton buttonEdit;
    private JLabel labelImgBall;
    private int rayon = 0;
    private double height;
    private double width;

    private ArrayList<JLabel> listLabelBall;
    private JLabel score = new JLabel();
    private int valScore = 0;
    private Image imageBoardRight;
    private ImageIcon imageIconBall;
    private Graphics2D g2d;
    private int nombreBall;
    private JLabel jlabel;
    // private ImageIcon imageIcon;
    private int ballY = 0;
    private int positionCurrentBall = 0;

    public BoardRight() {
        listLabelBall = new ArrayList<JLabel>();

        // try {
        // // imageBoardRight =
        // //
        // ImageIO.read(this.getClass().getResource("ressources/peggleBallsToFire.png"));

        // // imageBoardRight =
        // //
        // ImageIO.read(this.getClass().getResource("ressources/peggleBarreScoreWithZero.png"));
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        // ImageIcon imageIcon = new
        // ImageIcon(this.getClass().getResource("ressources/peggleBallsToFire.png"));
        // Image image = imageIcon.getImage(); // transform it
        // Image newimg = image.getScaledInstance((int) width, (int) height - 100,
        // java.awt.Image.SCALE_SMOOTH);
        // imageIcon = new ImageIcon(newimg); // transform it back
        // jlabel = new LabelPeg(imageIcon);

        setLayout(null);

        imageIconBall = new ImageIcon(this.getClass().getResource("ressources/ball.png"));

        score.setBounds(50, 500, 100, 100);
        score.setText("SCORE : " + valScore);

        add(score);

        System.out.println(getHeight());

        // add(new JLabel("BoardModelC"), BorderLayout.CENTER);
        // add(new JLabel("BoardModelN"), BorderLayout.NORTH);
        // add(new JLabel("BoardModelS"), BorderLayout.SOUTH);
        // add(new JLabel("BoardModelW"), BorderLayout.WEST);
        // add(new JLabel("BoardModelE"), BorderLayout.EAST);

        // int x = 0 ;
        // int y = 0 ;

        // for(int i = 0; i < number; i++){
        // //listLabelBall.get(i).setBounds(x - rayon, y - rayon, 2 * rayon, 2 * rayon);
        // listLabelBall.get(i).setLocation(x, y);
        // // y += 2 * rayon + 5 ;
        // }

        // jlabel.addMouseListener(this);
        // addMouseMotionListener(this);

    }

    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        // super.paintComponent(g);
        // add(labelImgBall);
        // labelImgBall.setLocation(new Point(50, 50));
        // setBackground(Color.WHITE);
        super.paintComponent(g);
        g2d = (Graphics2D) g;

        // imageBoardRight = imageBoardRight.getScaledInstance((int) width, (int) height
        // - 100,
        // java.awt.Image.SCALE_SMOOTH);

        // g2d.drawImage(imageBoardRight, 0, 0, (int) width, (int) height - 20,
        // null, null);

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
        setSize((int) width, (int) height);
        ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("ressources/peggleBallsToFire.png"));
        imageIcon.setImage(
                imageIcon.getImage().getScaledInstance((int) width, (int) height, java.awt.Image.SCALE_SMOOTH));
        jlabel = new LabelPeg(imageIcon);

        jlabel.setBounds(0, 0, (int) width, (int) height);
    }

    public void setWidth(double width) {
        this.width = width / 8.;

    }

    public void setRayon(int rayon) {
        this.rayon = rayon;
        Image image = imageIconBall.getImage(); // transform it
        Image newimg = image.getScaledInstance(2 * rayon, 2 * rayon, java.awt.Image.SCALE_SMOOTH);
        imageIconBall = new ImageIcon(newimg); // transform it back
    }

    public void setNombreBall(int nombreBall) {
        this.nombreBall = nombreBall;
    }

    public void initalisation() {

        ballY = 0;
        for (int i = 0; i < nombreBall; i++) {
            labelImgBall = new JLabel(imageIconBall);

            listLabelBall.add(0, labelImgBall);

            labelImgBall.setBounds((int) (width / 2 - rayon / 1.5), (int) (height * 2 / 3 - ballY), 2 * rayon,
                    2 * rayon);

            ballY += 2 * rayon + 5;

            add(labelImgBall);
            // labelImgBall.setLocation(new Point(50, 50));
        }
        add(jlabel);
    }

    public void ballUsed() {
        if (listLabelBall.size() == 0)
            return;
        // remove(listLabelBall.get(0));
        // listLabelBall.remove(0);
        listLabelBall.get(positionCurrentBall).setVisible(false);
        ++positionCurrentBall;
        ballY -= 2 * rayon + 5;
        repaint();
        // setBackground(Color.WHITE);

    }

    public void upgradeScore(int n) {
        score.setText("SCORE : " + n);
        repaint();
        // setBackground(Color.WHITE);
    }

    public void trouFall() {
        // try {
        // imageBoardRight =
        // ImageIO.read(this.getClass().getResource("ressources/peggleBallsToFireFreeBall.gif"));
        // // imageBoardRight =
        // //
        // ImageIO.read(this.getClass().getResource("ressources/peggleBarreScoreWithZero.png"));
        // } catch (IOException e) {
        // e.printStackTrace();
        // }

        // labelImgBall = new JLabel(imageIconBall);
        // listLabelBall.add(0, labelImgBall);
        // labelImgBall.setBounds((int) (width / 2 - rayon / 1.5), (int) (height * 2 / 3
        // - ballY), 2 * rayon,
        // 2 * rayon);
        --positionCurrentBall;
        listLabelBall.get(positionCurrentBall).setVisible(true);
        ballY += 2 * rayon + 5;

        ++nombreBall;
        ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("ressources/peggleBallsToFireFreeBall.gif"));
        imageIcon.setImage(
                imageIcon.getImage().getScaledInstance((int) width, (int) height, java.awt.Image.SCALE_DEFAULT));
        jlabel.setIcon(imageIcon);

        // jlabel.setBounds(0, 0, (int) width, (int) height);
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
