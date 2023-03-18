import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener ;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;

public class BoardMain extends Board implements KeyListener{

    private Timer timer;
    private Dimension dimensionBoard;

    private Graphics2D g2d  ;


    private int commandKey = 0 ;

    /* BoardModel */
    BoardModel boardModel;

    private double time = 0.015;


    public BoardMain(String filePath) {
        initBoard(filePath);
        width = super.width ;
        height = super.height ;
    }

    private void initBoard(String filePath) {

        /* Initialisation of boardModel */
        boardModel = new BoardModel(Toolkit.getDefaultToolkit().getScreenResolution());


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
                listPeg.add(new PegCercle(Integer.parseInt(tabRows[0]), Integer.parseInt(tabRows[1]), 12, tabRows[2])) ;
            }
            scanner.close();

            boardModel.getGenerator().setPegListe(listPeg);

            loadPegOnBoard(boardModel.getGenerator());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void loadPegOnBoard(PegGenerator pegGen){
        add(boardModel.getCanon().getJlabel());
        for (int i = 0; i < pegGen.getPegListe().size(); ++i) {
            add(pegGen.getPegListe().get(i).getJlabel());
        }
    }

    @Override
    public void paintComponent(Graphics g) {

        g2d = (Graphics2D)g ;

        g2d.drawImage(imageBoard, 0, 0, (int) width, (int) height, null);

        /* changing */
        boardModel.getCanon().radianChanged(boardModel.getThetaCanon(), g2d);

        boardModel.getBall().setXInitial(boardModel.getCanon().getCanonX());
        boardModel.getBall().setYInitial(boardModel.getCanon().getCanonY());

        /* updating the ball's image */
        boardModel.getBall().updateImgBall();
        add(boardModel.getBall().getLabelImgBall());
        boardModel.contact();

        if(!boardModel.getGenerator().hasOrangePeg()){
            // this.addKeyListener(this) ;
            // this.setFocusable(true);
            boardModel.setGameOver(true);
            drawGameOverScreen();
        }
    }

    public void drawGameOverScreen(){
        g2d.setColor(new Color(0,0,0, 150));
        g2d.fillRect(0,0, (int)width, (int)height) ;

        /* gameOver */
        String text = "Game Over";
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 110f)) ;
        int x = getXforCenteredText(text); 
        int y = 210 ;
        /* shadow */
        g2d.setColor(Color.black);
        g2d.drawString(text, x, y) ;
        /* main color */
        g2d.setColor(Color.white) ;
        g2d.drawString(text, x-4, y-4) ;

        /* rety */

        g2d.setFont(g2d.getFont().deriveFont(50f)) ;
        text = "Retry";
        x = getXforCenteredText(text);
        int xbis = x ;
        y += 2 * y ;
        g2d.drawString(text, x, y);
        if (commandKey == 0){
            g2d.drawString(">", x - 40, y) ;
        }

        /* back */
        text = "Quit";
        x= getXforCenteredText(text);
        y += 55 ;

        g2d.drawString(text, x, y);

        if (commandKey == 1){
            g2d.drawString(">", xbis - 40, y) ;
        }
    }

    public int getXforCenteredText(String text){
        Font font = g2d.getFont() ;
        Rectangle2D f = font.getStringBounds(text, g2d.getFontRenderContext());
        return (int)((width - f.getWidth()) / 2) ;
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
        }
    }

    public void setWidthScreen(double w) {
        super.setWidthScreen(w);
        boardModel.setWidthBoard(width);

        boardModel.getBall().setWidthBoard(width);
        /* New */
        boardModel.getCanon().setOrbX(width);
    }

    public void setHeightScreen(double w){
        super.setHeightScreen(w);
        boardModel.getBall().setHeightBoard(w);
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
                    this.remove(boardModel.getGenerator().getPegListe().get(i).getJlabel()) ;
                }

                boardModel.setPegGenerator(new PegGenerator(Toolkit.getDefaultToolkit().getScreenResolution()));
                loadPegOnBoard(boardModel.getGenerator());
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