
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.TimerTask;
import java.util.ArrayList;
import java.util.Timer;

public class Board extends JPanel implements MouseInputListener {

    private Timer timer;
    private Dimension dimensionBoard;
    private Dimension DimensionFrame;
    private double width;
    private double height;
    // private Point[] listeTrajectoire;
    private ArrayList<Point> listeTrajectoire = new ArrayList<Point>();

    /* BoardModel */
    BoardModel boardModel;

    private double time = 0.015;

    Graphics2D g2d;

    private Image imageBoard;

    public Board() {
        initBoard();
    }

    private void initBoard() {

        double pixelWidth = 0;
        double pixelHeight = 0;

        try {
            imageBoard = ImageIO.read(this.getClass().getResource("ressources/bgd-peggle-img-1.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int width = imageBoard.getWidth(this);
        int height = imageBoard.getHeight(this);
        setPreferredSize(new Dimension(width, height));
        setLayout(null);

        /* Initialisation of boardModel */
        boardModel = new BoardModel(Toolkit.getDefaultToolkit().getScreenResolution(), this);

        add(boardModel.getCanon().getJlabel());

        // timer : animation
        final int INITIAL_DELAY = 100;
        final int PERIOD_INTERVAL = 15;
        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), INITIAL_DELAY, PERIOD_INTERVAL);

        boardModel.getBall().trajectoire();
        listeTrajectoire = boardModel.getBall().trajectoire();

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
        add(boardModel.getBall().getLabelImgBall());
        boardModel.contact();

        g2d.fillOval((int) boardModel.getTrou().getX() - boardModel.getTrou().getLongueur() / 2,
                (int) boardModel.getTrou().getY() - boardModel.getTrou().getLargeur() / 2,
                boardModel.getTrou().getLongueur(),
                boardModel.getTrou().getLargeur());

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

    public void setDimensionBoard(Dimension dim) {
        this.dimensionBoard = dim;
    }

    private class ScheduleTask extends TimerTask {
        @Override
        public void run() {
            if (boardModel.getBall().isBallStart())
                time += 0.015;
            else {
                time = 0.015;
            }
            /* new */
            boardModel.updateBoardModel(time);
            repaint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        boardModel.setBallStart(true);
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
        if (!boardModel.getBall().isBallStart()) {
            double normeVect = (Math
                    .sqrt(Math.pow(e.getX() - getBounds().getWidth() / 2, 2) + Math.pow(e.getY() - 50, 2)));
            double angle = Math.acos(Math.abs(e.getY() - 50)
                    / (Math.sqrt(Math.pow(e.getX() - getBounds().getWidth() / 2, 2) + Math.pow(e.getY() - 50, 2))));
            if (e.getX() < getBounds().getWidth() / 2)
                angle = -angle;
            double theta = angle - Math.PI / 2;

            boardModel.setThetaCanon(theta);
            // boardModel.setAngleChute(theta);
            boardModel.getBall().setVitesseX((e.getX() - getBounds().getWidth() / 2) / normeVect);
            boardModel.getBall().setVitesseY(e.getY() / normeVect);

            listeTrajectoire = boardModel.getBall().trajectoire();
        }
    }

    public void setWidthScreen(double w) {
        double var = w - (2.0 / 8.0) * w;
        width = var;
        boardModel.setWidthBoard(var);
    }

    public void setHeightScreen(double w) {
        boardModel.getBall().setHeightBoard(w);
        height = w;
        boardModel.setHeightBoard(w);
    }

    public void setDimensionFrame(Dimension w) {
        DimensionFrame = w;
    }

}