
import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.TimerTask;
import java.util.Timer;

public class Board extends JPanel implements MouseInputListener {

    private Timer timer;
    private int x = 25;
    private Dimension dimensionBoard;
    private Dimension DimensionFrame ;


    /* BoardModel */
    BoardModel boardModel ;

    private double time = 1 ;


    Graphics2D g2d;

    private double sourisX;
    private double sourisY;

    private Image imageBoard;
    private PegCercle pc;

    private double widthScreen;

    public Board() {
        initBoard();
    }

    private void initBoard() {
        loadImage("src/ressources/bgd-peggle-img-1.jpg");
        int width = imageBoard.getWidth(this);
        int height = imageBoard.getHeight(this);
        setPreferredSize(new Dimension(width, height));
        setLayout(null);

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
        add(canon.getJlabel());
        pc = new PegCercle(500, 500, 50, "Bleu");
        // add(pc.getJlabel());
        PegGenerator generator = new PegGenerator();
        for (int i = 0; i < generator.getPegListe().size(); ++i) {
            add(generator.getPegListe().get(i).getJlabel());
        }
        // pc.getJlabel().setBounds((int) pc.getPegX(), (int) pc.getPegY(), (int)
        // pc.getRayon(), (int) pc.getRayon());

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

        /* changing  */
        boardModel.getCanon().radianChanged(boardModel.getThetaCanon(), g2d);

        g2d.drawLine((int) sourisX, (int) sourisY, (int) getBounds().getWidth() / 2, 0);

        boardModel.getBall().setXInitial(boardModel.getCanon().getCanonX());
        boardModel.getBall().setYInitial(boardModel.getCanon().getCanonY());

        System.out.println(boardModel.getCanon().getCanonX() + " " +boardModel.getCanon().getCanonY() );

        /* updating the ball's image */
        boardModel.getBall().updateImgBall();
        add(boardModel.getBall().getLabelImgBall()) ;

    }

    public void setDimensionBoard(Dimension dim) {
        this.dimensionBoard = dim;
    }

    private class ScheduleTask extends TimerTask {
        @Override
        public void run() {
            x += 0.555;
            time += 0.015; 
            //old
            //ball.move(time) ;

            /* new */
            boardModel.getBall().move(time);
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
        double theta = angle - Math.PI / 2;

        boardModel.setThetaCanon(theta) ;

        sourisX = e.getX();
        sourisY = e.getY();

        double hypothenuse = Math.sqrt(Math.pow(e.getX() - getBounds().getWidth() / 2, 2) + Math.pow(e.getY() - 50, 2)) ;
        //angleChute = Math.acos((e.getY() - 50) / hypothenuse);
        //ball.setTheta(angleChute);
    }

    public void setWidthScreen(double w) {
        double var = w - (2.0 / 8.0) * w ;
        // /* old */
        // canon.setOrbX(var);

        /* New */
        boardModel.getCanon().setOrbX(var);

        System.out.println(" var " + var + " w = " + w + " calcul = " + var);
    }

    public void setDimensionFrame(Dimension w){
        DimensionFrame = w ;
    }

}