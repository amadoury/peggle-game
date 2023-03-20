
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener ;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.TimerTask;
import java.util.ArrayList;
import java.awt.geom.Rectangle2D;
import java.io.File; 


import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;

public class BoardMain extends Board implements KeyListener{

    private Timer timer;
    private Dimension dimensionBoard;
    private Dimension DimensionFrame;

    private ArrayList<Point> listeTrajectoire = new ArrayList<Point>();

    private Graphics2D g2d  ;


    private int commandKey = 0 ;

    /* BoardModel */
    BoardModel boardModel;

    // private double time = 0.015;

    public BoardMain(String filePath) {
        initBoard(filePath); 
        width = super.width ;
        height = super.height ;
    }

    private void initBoard(String filePath) {

        /* Initialisation of boardModel */
        boardModel = new BoardModel((int) resolutionScreen, this); 

        // add(boardModel.getCanon().getJlabel());
        // for (int i = 0; i < boardModel.getGenerator().getPegListe().size(); ++i) {
        //     add(boardModel.getGenerator().getPegListe().get(i).getJlabel());
        // }

        if (filePath == null){
            loadPegOnBoard(boardModel.getGenerator());
        }else{
            loadPegOnBoardWithFile(filePath) ;
        }

        // timer : animation
        final int INITIAL_DELAY = 100;
        final int PERIOD_INTERVAL = 15;
        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), INITIAL_DELAY, PERIOD_INTERVAL);

        boardModel.getBall().trajectoire(); 
        listeTrajectoire = boardModel.getBall().trajectoire(); 


        this.addKeyListener(this) ;
        this.setFocusable(true);
    }


    public void loadPegOnBoardWithFile(String path){
        try{
            ArrayList<Peg> listPeg = new ArrayList<Peg>() ;
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("\n");



            while(scanner.hasNext()){
                String [] tabRows = scanner.next().split("/");
                System.out.println(tabRows[0]);
                listPeg.add(new PegCercle(Integer.parseInt(tabRows[0]), Integer.parseInt(tabRows[1]), boardModel.getGenerator().getRadius(), tabRows[2])) ;
            }

            scanner.close();

            System.out.println("la taille de la liste listPeg " + listPeg.size());
            boardModel.getGenerator().setPegListe(listPeg);

            loadPegOnBoard(boardModel.getGenerator());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void loadPegOnBoard(PegGenerator pegGen){
        add(boardModel.getCanon().getJlabel());
        System.out.println(" affiche listePeg" + pegGen.getPegListe().size());
        for (int i = 0; i < pegGen.getPegListe().size(); ++i) {
            System.out.println("test " + pegGen.getPegListe().get(i));
            add(pegGen.getPegListe().get(i).getLabelPeg());
        }
    }

    @Override
    public void paintComponent(Graphics g) {

        g2d = (Graphics2D)g ;

        g2d.drawImage(imageBoard, 0, 0, (int) width, (int) height,
                null, null);

        /* changing */
        boardModel.getCanon().radianChanged(boardModel.getThetaCanon(), g2d);

        boardModel.getBall().setXInitial(boardModel.getCanon().getCanonX());
        boardModel.getBall().setYInitial(boardModel.getCanon().getCanonY());

        /* updating the ball's image */
        boardModel.getBall().updateImgBall();
        add(boardModel.getBall().getLabelImgBall());
        // boardModel.contact();

        if (listeTrajectoire == null || listeTrajectoire.size() == 0) {
            //System.out.println("test");
            return;
        }

        if (boardModel.getBall().isBallStart() == false) {
            listeTrajectoire = boardModel.getBall().trajectoire();
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
            // if (boardModel.getBall().isBallStart())
            // time += 0.015;
            // else {
            // time = 0.015;
            // }
            /* new */
            boardModel.updateBoardModel();
            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        boardModel.setBallStart(true);
        boardModel.getSound().setFile(0);
        boardModel.getSound().play();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (!boardModel.getBall().isBallStart() && !boardModel.isGameOver()) {
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

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode() ;

        if(key == KeyEvent.VK_UP){
            commandKey = 0 ;
        }

        if (key ==  KeyEvent.VK_DOWN){
            commandKey = 1 ;
        }

        if (key == KeyEvent.VK_ENTER){
            if(commandKey == 0 ){
                System.out.println("retry");

                for(int i = 0; i < boardModel.getGenerator().getPegListe().size() ; i++){
                    this.remove(boardModel.getGenerator().getPegListe().get(i).getLabelPeg()) ;
                }

                //boardModel.setPegGenerator(new PegGenerator(Toolkit.getDefaultToolkit().getScreenResolution(), 20));
                //loadPegOnBoard(boardModel.getGenerator());
                boardModel.setGameOver(false);
            }
            if (commandKey == 1 ){
                JFrame frame = (JFrame)SwingUtilities.getWindowAncestor(this);
                frame.dispose();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE) ;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    public BoardModel getBoardModel(){
        return boardModel;
    }

 }