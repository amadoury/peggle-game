import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.TimerTask;
import java.util.ArrayList;
import java.awt.geom.Rectangle2D;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import java.util.Scanner;
import java.util.Timer;

import javax.swing.*;

public class BoardMain extends Board implements KeyListener {

    private Timer timer;
    private Dimension dimensionBoard;
    private Dimension DimensionFrame;
    protected ArrayList<Point> listeTrajectoire = new ArrayList<Point>();

    private App app;

    protected int commandKey = 0;

    /* BoardModel */
    protected BoardModel boardModel;

    public BoardRight right;
    public BoardLeft left;

    // private double time = 0.015;
    private boolean multiPlayer ;

    public BoardMain(String filePath,BoardRight right, BoardLeft left, boolean multiPlayer) {
        //super(dim);
        //System.out.println(Toolkit.getDefaultToolkit());
        this.right = right ;
        this.left = left ;
        this.multiPlayer = multiPlayer;
        initBoard(filePath); 

        // width = dim.getWidth();
        // height = dim.getHeight() ;

        this.addKeyListener(this);
        this.setFocusable(true);
    }

    private void initBoard(String filePath) {

        /* Initialisation of boardModel */
        boardModel = new BoardModel((int) resolutionScreen, this, right, left, multiPlayer); 

        if (filePath == null) {
            loadPegOnBoard(boardModel.getGenerator());
        } else {
            loadPegOnBoardWithFile(filePath);
        }

        // timer : animation
        final int INITIAL_DELAY = 100;
        final int PERIOD_INTERVAL = 15;
        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), INITIAL_DELAY, PERIOD_INTERVAL);

        boardModel.getBall().trajectoire();
        listeTrajectoire = boardModel.getBall().trajectoire();
    }

    public void loadPegOnBoardWithFile(String path) {
        try {
            ArrayList<Peg> listPeg = new ArrayList<Peg>();
            File file = new File(this.getClass().getResource(path).toURI());
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("\n");

            while(scanner.hasNext()){
                String [] tabRows = scanner.next().split("/");
                System.out.println(tabRows[0]);
                switch(tabRows[3]){
                    case "PegCercle" :
                        listPeg.add(new PegCercle((int)(Double.parseDouble(tabRows[0]) * width) ,
                        (int)(Double.parseDouble(tabRows[1]) * height),
                        boardModel.getGenerator().getRadius(), tabRows[2])) ;
                        break;
                    // case "PegRectangle" :
                    //     listPeg.add(new PegRectangle((int)(Double.parseDouble(tabRows[0]) * width) ,
                    //     (int)(Double.parseDouble(tabRows[1]) * height), 60, 30, tabRows[2])) ;
                    //     break;
                }
            }

            // while (scanner.hasNext()) {
            //     String[] tabRows = scanner.next().split("/");
            //     listPeg.add(new PegCercle(Integer.parseInt(tabRows[0]), Integer.parseInt(tabRows[1]),
            //             boardModel.getGenerator().getRadius(), tabRows[2]));
            // }

            scanner.close();

            boardModel.getGenerator().setPegListe(listPeg);

            loadPegOnBoard(boardModel.getGenerator());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadPegOnBoard(PegGenerator pegGen) {
        add(boardModel.getBall().getLabelImgBall());
        add(boardModel.getCanon().getJlabel());
        for (int i = 0; i < pegGen.getPegListe().size(); ++i) {
            add(pegGen.getPegListe().get(i).getLabelPeg());
        }
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

        // boardModel.contact();

        if (listeTrajectoire == null || listeTrajectoire.size() == 0) {
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

        showFireWorksOnScreen();

        if (!boardModel.getGenerator().hasOrangePeg()) {
            boardModel.setGameOver(true);
            // drawGameOverScreen();

            int len = boardModel.getGenerator().getPegListe().size();

            if (len >= 1) {
                for (int i = 0; i < len; i++) {
                    remove(boardModel.getGenerator().getPegListe().get(i).getLabelPeg());
                }
            }

            boardModel.getTrou().setMove(false);
            if (!multiPlayer)
                drawGameWiningScreen("YOU WON");
            }
        
    } 

    public void showFireWorksOnScreen() {
        ImageIcon imgFireWorks = new ImageIcon(this.getClass().getResource("ressources/fireworks.gif"));

        // Image img = imgFireWorks.getImage() ;
        // Image newimg = img.getScaledInstance(1000,1000,
        // java.awt.Image.SCALE_DEFAULT);

        // imgFireWorks = new ImageIcon(newimg) ;

        JLabel fireFireWorksLabel = new JLabel(imgFireWorks);

        boolean show = false;

        javax.swing.Timer timer = new javax.swing.Timer(3000, (action) -> {
            // show = true ;
        });

        timer.setRepeats(false);
        timer.start();

    }

    public void drawEndGameScreen(String state) {
        g2d.setColor(new Color(0, 0, 0, 150));
        g2d.fillRect(0, 0, (int) width, (int) height);

        /* gameOver */
        String text = state;
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 110f));
        int x = getXforCenteredText(text);
        int y = 210;
        /* shadow */
        g2d.setColor(Color.black);
        g2d.drawString(text, x, y);
        /* main color */
        g2d.setColor(Color.white);
        g2d.drawString(text, x - 4, y - 4);

        /* rety */

        g2d.setFont(g2d.getFont().deriveFont(50f));
        text = "Retry";
        x = getXforCenteredText(text);
        y += 2 * y;
        g2d.drawString(text, x, y);
        if (commandKey == 0) {
            g2d.drawString(">", x - 50, y);
        }

        /* back */
        text = "Quit";
        x = getXforCenteredText(text);
        y += 55;

        g2d.drawString(text, x, y);

        if (commandKey == 1) {
            g2d.drawString(">", x - 50, y);
        }

        /* go to menuLevel */
        text = "Levels";
        x = getXforCenteredText(text);
        y += 55;

        g2d.drawString(text, x, y);

        if (commandKey == 2) {
            g2d.drawString(">", x - 50, y);
        }
    }

    public void drawGameWiningScreen(String st){
        drawEndGameScreen(st);
    }

    public void drawGameOverScreen() {
        drawEndGameScreen("Game Over");
    }

    public int getXforCenteredText(String text) {
        Font font = g2d.getFont();
        Rectangle2D f = font.getStringBounds(text, g2d.getFontRenderContext());
        return (int) ((width - f.getWidth()) / 2);
    }

    public void changeValue(int[] tab) {
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
            boardModel.updateBoardModel();
            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!boardModel.isGameOver()) {
            boardModel.setBallStart(true);
            boardModel.getSound().setFile(0);
            boardModel.getSound().play();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (!boardModel.getBall().isBallStart() && !boardModel.isGameOver()) {
            double normeVect = (Math.sqrt(Math.pow(e.getX() - getBounds().getWidth() / 2, 2) + Math.pow(e.getY() - 50, 2)));
            double angle = Math.acos(Math.abs(e.getY() - 50) / (Math.sqrt(Math.pow(e.getX() - getBounds().getWidth() / 2, 2) + Math.pow(e.getY() - 50, 2))));
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
        // double var = w - (2.0 / 8.0) * w;
        double var = (6. / 8.0) * w;
        System.out.println(var + " var");
        System.out.println(w + "width ");

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
        int key = e.getKeyCode();

        // System.out.println("key pressed ");

        if (key == KeyEvent.VK_UP) {
            if (commandKey <= 0) {
                commandKey = 0;
            } else {
                commandKey--;
            }
        }

        if (key == KeyEvent.VK_DOWN) {
            if (commandKey >= 2) {
                commandKey = 2;
            } else {
                commandKey++;
            }
        }

        if (key == KeyEvent.VK_ENTER) {
            if (commandKey == 0) {
                System.out.println("Retry");

                for (int i = 0; i < boardModel.getGenerator().getPegListe().size(); i++) {
                    this.remove(boardModel.getGenerator().getPegListe().get(i).getLabelPeg());
                }

                boardModel.setPegGenerator(new PegGenerator(Toolkit.getDefaultToolkit().getScreenResolution(), 20));
                loadPegOnBoard(boardModel.getGenerator());
                boardModel.setGameOver(false);
            }
            if (commandKey == 1) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                frame.dispose();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }

            if (commandKey == 2) {
                app.getCardLayout().show(app.getPanelBoard(), "levelMenu");
                System.out.println("cardLayout");
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    public BoardModel getBoardModel() {
        return boardModel;
    }

    public void setApp(App app) {
        this.app = app;
    }

}
