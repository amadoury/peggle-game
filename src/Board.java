
import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.TimerTask;
import java.util.Timer;

public class Board extends JPanel implements MouseInputListener {

    private Timer timer;
    private JPanel boardCenter = new BoardCenter();
    private int x = 25;
    private int y = getHeight() - 50;
    private Dimension dimensionBoard;

    private Ball ball ;
    private final double vInitial = 200 ; /* initial speed of the ball */
    private double angleChute = -60;
    private double time = 1 ;
    private double x_peg = 500 ; 
    private double y_peg = 500 ; 

    private int centreXCanon = 500;
    private int centreYCanon = 500;
    private int timeCanon;
    private Canon canon;
    private double theta = -Math.PI / 2;
    private double drawX ; // x de la balle sur l'orbit 
    private double drawY ; // y 

    Graphics2D g2d;

    private double sourisX;
    private double sourisY;

    private Image imageBoard;

    private double widthScreen;

    public Board() {
        initBoard();
    }

    private void initBoard() {
        loadImage("src/ressources/bgd-peggle-img-1.jpg");
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
        ball = new Ball(canon.getCanonX(), canon.getCanonY(),angleChute,getBounds()) ;

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

        ball.drawBall(g2d);
        //g2d.drawOval((int)x_peg, (int)y_peg, 30, 30);
        System.out.println("peg : " + x_peg + " " + y_peg);

    }

    public void setDimensionBoard(Dimension dim) {
        this.dimensionBoard = dim;
    }

    private class ScheduleTask extends TimerTask {
        @Override
        public void run() {
            x += 0.555;
            time += 0.015; 
            ball.move(time) ;
            //x_peg = vInitial * Math.sin(Math.toRadians(angleChute)) * time + canon.getCanonX();
            //y_peg =   0.5 * 9.81 * (time * time) + (vInitial * Math.cos(Math.toRadians(angleChute)) * time) + canon.getCanonY();
            repaint();        
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

        System.out.println("mouseClicked");
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
        theta = angle - Math.PI / 2;
        sourisX = e.getX();
        sourisY = e.getY();

        double hypothenuse = Math.sqrt(Math.pow(e.getX() - getBounds().getWidth() / 2, 2) + Math.pow(e.getY() - 50, 2)) ;
        //angleChute = Math.acos((e.getY() - 50) / hypothenuse);
        //ball.setTheta(angleChute);
    }

    public void setWidthScreen(double w) {
        canon.setOrbX(getBounds().getWidth());
    }

}