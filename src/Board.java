
import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.TimerTask;
import java.util.Timer;

public class Board extends JPanel implements MouseInputListener {

    private Timer timer;
    private Dimension dimensionBoard;
    private Dimension DimensionFrame;

    /* BoardModel */
    BoardModel boardModel;

    private double time = 1;

    Graphics2D g2d;

    private Image imageBoard;

    public Board() {
        initBoard();
    }

    private void initBoard() {
        loadImage("src/ressources/bgd-peggle-img-1.jpg");
        int width = imageBoard.getWidth(this);
        int height = imageBoard.getHeight(this);
        setPreferredSize(new Dimension(width, height));
        setLayout(null);

        boardModel = new BoardModel();

        add(boardModel.getCanon().getJlabel());

        PegGenerator generator = new PegGenerator();
        for (int i = 0; i < generator.getPegListe().size(); ++i) {
            add(generator.getPegListe().get(i).getJlabel());
        }

        // timer : animation
        final int INITIAL_DELAY = 100;
        final int PERIOD_INTERVAL = 15;
        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), INITIAL_DELAY, PERIOD_INTERVAL);

        addMouseListener(this);
        addMouseMotionListener(this);

    }

    private void loadImage(String path) {
        ImageIcon img = new ImageIcon(path);
        imageBoard = img.getImage();
    }

    @Override
    public void paintComponent(Graphics g) {

        g2d = (Graphics2D) g;

        g2d.drawImage(imageBoard, 0, 0, null);

        g2d.setColor(Color.BLUE);
        g2d.setStroke(new BasicStroke(8f));

        /* changing */
        boardModel.getCanon().radianChanged(boardModel.getThetaCanon(), g2d);

        boardModel.getBall().setXInitial(boardModel.getCanon().getCanonX());
        boardModel.getBall().setYInitial(boardModel.getCanon().getCanonY());

        /* updating the ball's image */
        boardModel.getBall().updateImgBall();
        add(boardModel.getBall().getLabelImgBall());

    }

    public void setDimensionBoard(Dimension dim) {
        this.dimensionBoard = dim;
    }

    private class ScheduleTask extends TimerTask {
        @Override
        public void run() {
            time += 0.015;
            // old
            // ball.move(time) ;

            /* new */
            boardModel.getBall().move(time);
            repaint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mousePressed(MouseEvent e) {

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
        double angle = Math.acos(Math.abs(e.getY() - 50)
                / (Math.sqrt(Math.pow(e.getX() - getBounds().getWidth() / 2, 2) + Math.pow(e.getY() - 50, 2))));
        if (e.getX() < getBounds().getWidth() / 2)
            angle = -angle;
        double theta = angle - Math.PI / 2;

        boardModel.setThetaCanon(theta);

        double hypothenuse = Math.sqrt(Math.pow(e.getX() - getBounds().getWidth() / 2, 2) + Math.pow(e.getY() - 50, 2));
        // angleChute = Math.acos((e.getY() - 50) / hypothenuse);
        // ball.setTheta(angleChute);
    }

    public void setWidthScreen(double w) {
        double var = w - (2.0 / 8.0) * w;

        /* New */
        boardModel.getCanon().setOrbX(var);

    }

    public void setDimensionFrame(Dimension w) {
        DimensionFrame = w;
    }

}