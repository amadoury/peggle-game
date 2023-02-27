import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.TimerTask;
import java.util.Timer;

public class BoardMain extends Board {

    private Timer timer;
    private Dimension dimensionBoard;

    /* BoardModel */
    BoardModel boardModel;

    private double time = 0.015;


    public BoardMain() {
        initBoard();
        width = super.width ;
        height = super.height ;
    }

    private void initBoard() {

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
    }

    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D)g ;

        g2d.drawImage(imageBoard, 0, 0, (int) width, (int) height, null);

        /* changing */
        boardModel.getCanon().radianChanged(boardModel.getThetaCanon(), g2d);

        boardModel.getBall().setXInitial(boardModel.getCanon().getCanonX());
        boardModel.getBall().setYInitial(boardModel.getCanon().getCanonY());

        /* updating the ball's image */
        boardModel.getBall().updateImgBall();
        add(boardModel.getBall().getLabelImgBall());
        boardModel.contact();

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

}