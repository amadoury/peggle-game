
import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.TimerTask;
import java.util.Timer;

public class Board extends JPanel implements MouseInputListener {

    private Timer timer;
    private JPanel top = new JPanel();
    private JPanel bottom = new JPanel();
    private JPanel boardCenter = new BoardCenter();
    private int x = 25;
    private int y = getHeight() - 50;

    private int centreXCanon = 500;
    private int centreYCanon = 500;
    // private int canonX;
    // private int canonY;
    // private double deltaCanon;
    private int timeCanon;
    private Canon canon;
    private double theta = -Math.PI / 2;

    Graphics2D g2d;

    private double sourisX;
    private double sourisY;

    private Image imageBoard;

    private double widthScreen;

    public Board() {
        initBoard();
    }

    private void initBoard() {
        loadImage("ressources/bgd-peggle-img-1.jpg");
        int width = imageBoard.getWidth(this);
        int height = imageBoard.getHeight(this);
        setPreferredSize(new Dimension(width, height));

        // GridBagConstraints c = new GridBagConstraints();

        // c.fill = GridBagConstraints.VERTICAL;

        // // c.weighty = 0.5;
        // c.gridx = 0;
        // c.gridy = 0;
        // top.setVisible(true);
        // top.setBackground(Color.BLACK);
        // c.ipadx = 9999;
        // // top.setPreferredSize(new Dimension(9999, 9999));
        // add(top, c);

        // c.fill = GridBagConstraints.VERTICAL;
        // // c.weighty = 3;
        // c.gridx = 0;
        // c.gridy = 1;
        // c.ipady = 1000;
        // add(boardCenter, c);

        // c.fill = GridBagConstraints.VERTICAL;
        // // c.weighty = 0.5;
        // c.gridx = 0;
        // c.gridy = 2;
        // bottom.setVisible(true);
        // bottom.setBackground(Color.RED);
        // add(bottom, c);
        canon = new Canon(getBounds().getWidth() / 2, 0, 50);

        // timer : animation
        final int INTIAL_DELAY = 100;
        final int PERIO_INTERVAL = 15;
        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), INTIAL_DELAY, PERIO_INTERVAL);

        addMouseListener(this);
        addMouseMotionListener(this);

    }

    private void loadImage(String path) {
        ImageIcon img = new ImageIcon(path);
        imageBoard = img.getImage();
    }

    public void drawOrbitingSphere(int timeInterval, Graphics g) {

        // let's just choose a bunch of values that we'll need
        double orbitX = centreXCanon; /* x-coordinate in orbit's center */
        double orbitY = centreYCanon; /* y-coordinate in orbit's center */
        double orbitRadius = 50;
        double orbitSpeed = Math.PI / 16;
        double sphereRadius = 10;

        /*
         * based on the current time interval, we'll calculate where the sphere
         * is at on its orbit
         */
        double radian = orbitSpeed * timeInterval;
        double drawX = orbitX + orbitRadius * Math.cos(radian);
        double drawY = orbitY + orbitRadius * Math.sin(radian);
        // canonX = (int) drawX;
        // canonY = (int) drawY;

        // use whichever Draw method is provided by your API
        drawSphere(drawX, drawY, sphereRadius, g);
    }

    private void drawSphere(double drawX, double drawY, double sphereRadius, Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawOval((int) drawX, (int) drawY, (int) sphereRadius, (int) sphereRadius);
    }

    @Override
    public void paintComponent(Graphics g) {

        g2d = (Graphics2D) g;
        // super.paintComponent(g);

        g2d.drawImage(imageBoard, 0, 0, null);

        g2d.setColor(Color.BLUE);
        g2d.setStroke(new BasicStroke(8f));
        // g2d.drawLine(10, 10, 10, 700);

        timeCanon += 1;
        // drawOrbitingSphere(timeCanon, g);
        canon.radianChanged(theta, g2d);

        g2d.drawLine((int) sourisX, (int) sourisY, (int) getBounds().getWidth() / 2, 0);

        g2d.drawOval(x, y, 200, 40);

    }

    private class ScheduleTask extends TimerTask {
        @Override
        public void run() {
            x += 0.555;

            repaint();
            boardCenter.repaint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

        System.out.println("mouseClicked");

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

        // canon.radianChanged(Math.acos(theta), g2d);
        System.out.println(theta);
        System.out.println(Math.abs(e.getY() - 50)
                / (Math.sqrt(Math.pow(e.getX() - getBounds().getWidth() / 2, 2) + Math.pow(e.getY() - 50, 2))));
        System.out.println(e.getY() - 50);
        System.out.println(e.getX() - getBounds().getWidth() / 2);

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
        double angle = Math.acos(Math.abs(e.getY() - 50)
                / (Math.sqrt(Math.pow(e.getX() - getBounds().getWidth() / 2, 2) + Math.pow(e.getY() - 50, 2))));
        if (e.getX() < getBounds().getWidth() / 2)
            angle = -angle;
        theta = angle - Math.PI / 2;
        sourisX = e.getX();
        sourisY = e.getY();
    }

    public void setWidthScreen(double w) {

        canon.setOrbX(getBounds().getWidth());
    }

}