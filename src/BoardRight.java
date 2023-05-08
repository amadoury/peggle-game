import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class BoardRight extends JPanel {
    private JLabel labelNumberBall;
    private JLabel labelImgBall;
    private int rayon = 0;
    private double height;
    private double width;

    private ArrayList<JLabel> listLabelBall;
    private int valScore = 0;
    private ImageIcon imageIconBall;
    private Graphics2D g2d;
    private int nombreBall;
    private JLabel jlabel;
    // private ImageIcon imageIcon;
    private int ballY = 0;
    private int positionCurrentBall;
    private int pegTouchedNumber;

    public BoardRight(double width, double height, CardLayout cdLayMenu, JPanel mainPanel, MenuLevel menuLevel) {
        this.width = width / 8.;
        this.height = height;

        listLabelBall = new ArrayList<JLabel>();

        setLayout(null);

        imageIconBall = new ImageIcon(this.getClass().getResource("ressources/ball.png"));

        jlabel = new JLabel();
        jlabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println(e.getY());
                if (e.getY() > height - 100){
                    
                    int result = JOptionPane.showConfirmDialog(null,"Do you want to go to Menu Level ?", "Warning !", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION){
                        menuLevel.moveCdLToPage1();
                        cdLayMenu.show(mainPanel, "menuLevel") ;
                    }
                }
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

        });
        // addMouseMotionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2d = (Graphics2D) g;
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
        setSize((int) width, (int) height);
        ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("ressources/peggleBallsToFire.png"));
        imageIcon.setImage(
                imageIcon.getImage().getScaledInstance((int) width, (int) height, java.awt.Image.SCALE_SMOOTH));
        jlabel.setIcon(imageIcon);

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
        positionCurrentBall = nombreBall;
    }

    public void initalisation() {

        ballY = 0;
        for (int i = 0; i < nombreBall + 1; i++) {
            labelImgBall = new JLabel(imageIconBall);

            listLabelBall.add(labelImgBall);

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
        --positionCurrentBall;
        ballY -= 2 * rayon + 5;
        repaint();
        // setBackground(Color.WHITE);

    }

    public void upgradeScore(int n) {
        valScore = n;
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
        ++positionCurrentBall;
        listLabelBall.get(positionCurrentBall).setVisible(true);
        ballY += 2 * rayon + 5;

        ++nombreBall;
        ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("ressources/peggleBallsToFireFreeBall.gif"));
        imageIcon.setImage(
                imageIcon.getImage().getScaledInstance((int) width, (int) height, java.awt.Image.SCALE_DEFAULT));
        jlabel.setIcon(imageIcon);
    }

    public void pegTouched() {
        if (pegTouchedNumber == 8)
            return;
        pegTouchedNumber = (pegTouchedNumber + 1);
        ImageIcon imageIcon = new ImageIcon(
                this.getClass().getResource("ressources/peggleBallsToFire-" + pegTouchedNumber + ".png"));
        imageIcon.setImage(
                imageIcon.getImage().getScaledInstance((int) width, (int) height, java.awt.Image.SCALE_DEFAULT));
        jlabel.setIcon(imageIcon);
        if (pegTouchedNumber == 8)
            trouFall();
    }

    public void ballRestart() {
        pegTouchedNumber = -1;
        pegTouched();
    }

}