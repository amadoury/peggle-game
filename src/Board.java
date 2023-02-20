import javax.swing.event.MouseInputListener;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.TimerTask;
import java.util.ArrayList;
import java.util.Timer;

public class Board extends JPanel implements MouseInputListener {
    Graphics2D g2d;
    protected Image imageBoard;
    public double width;
    public double height;
    private ArrayList<Point> listeTrajectoire = new ArrayList<Point>();

    public Board() {

        try {
            imageBoard = ImageIO.read(this.getClass().getResource("ressources/bgd-peggle-img-1.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        width = imageBoard.getWidth(this);
        height = imageBoard.getHeight(this);
        setPreferredSize(new Dimension((int) width, (int) height));
        setLayout(null);

        /* Initialisation of boardModel */
        boardModel = new BoardModel(Toolkit.getDefaultToolkit().getScreenResolution());

        add(boardModel.getCanon().getJlabel());
        for (int i = 0; i < boardModel.getGenerator().getPegListe().size(); ++i) {
            add(boardModel.getGenerator().getPegListe().get(i).getJlabel());
        }

        // timer : animation
        final int INITIAL_DELAY = 100;
        final int PERIOD_INTERVAL = 15;
        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), INITIAL_DELAY, PERIOD_INTERVAL);

        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {

        g2d = (Graphics2D) g;

        g2d.drawImage(imageBoard, 0, 0, (int) width, (int) height,
                null, null);

        /* changing */
        boardModel.getCanon().radianChanged(boardModel.getThetaCanon(), g2d);

        boardModel.getBall().setXInitial(boardModel.getCanon().getCanonX());
        boardModel.getBall().setYInitial(boardModel.getCanon().getCanonY());

        /* updating the ball's image */
        boardModel.getBall().updateImgBall();

        boardModel.contact();

        if (listeTrajectoire == null || listeTrajectoire.size() == 0) {
            System.out.println("test");
            return;
        }

        Point p = listeTrajectoire.get(0);
        g2d.setStroke(new BasicStroke(5));
        int[] rTab = { 34, 15 };
        int[] vTab = { 27, 38 };
        int[] bTab = { 40, 20 };

        for (int i = 1; i < listeTrajectoire.size(); ++i) {
            Point point = listeTrajectoire.get(i);
            // g2d.drawOval((int) point.getX(), (int) point.getY(), 5, 5);
            if (i % 3 == 0)
                p = point;
            if (i % 3 == 1) {
                changeValue(rTab);
                changeValue(vTab);
                changeValue(bTab);
                Color color = new Color(rTab[0], vTab[0], bTab[0]);

                g2d.setColor(color);
                g2d.drawLine((int) p.getX(), (int) p.getY(), (int) point.getX(), (int) point.getY());
            }
        }

        // float dash1[] = { 10.0f };
        // BasicStroke dashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT,
        // BasicStroke.JOIN_MITER, 10.0f,
        // dash1, 0.0f);
        // g2.setStroke(dashed);

    }

    private void changeValue(int[] tab) {
        if (tab[0] + Math.abs(tab[1]) > 255 - Math.abs(tab[1]) || tab[0] + tab[1] < Math.abs(tab[1]))
            tab[1] = -tab[1];
        tab[0] += tab[1];
    }

    public void setHeightScreen(double w) {
        height = w;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub

    }
}
