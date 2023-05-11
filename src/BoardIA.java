import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class BoardIA extends BoardMain {
    private Player player1 = new Player();
    private Player playerIA = new Player();
    private JLabel labelScore1 = new JLabel("Votre Score : " + player1.getScore()) ;
    private JLabel labelScoreIA = new JLabel("Score de l'IA : " + playerIA.getScore()) ;
    private boolean currentPlayer; // true si c'est le tour du player1 sinon false
    private Graphics2D g2d;
    private String playerTurn;
    private boolean showText = true;

    public BoardIA(String path, BoardRight right, BoardLeft left, CardLayout cardLayout, JPanel mainPanel, MenuLevel menuLevel, Dimension dim, App app) {
        super(path, right, left, true, cardLayout, mainPanel, menuLevel, app);

        currentPlayer = true ;
        boardModel.getBall().setBoardIA(this);

        int xS1 = (int)(dim.getWidth() * 1. / 8.) + 100 ; 
        int xSIA = (int)(dim.getWidth() * 1. / 2.) + 100 ;
        int yS = 10 ;
        this.add(labelScore1);
        this.add(labelScoreIA);
        labelScore1.setBounds(xS1, yS, 300, 50);
        labelScoreIA.setBounds(xSIA, yS, 300, 50);
        boardModel.getBall().setPlayer1(player1);
        boardModel.getBall().setPlayerIA(playerIA);
    }

    public void printPlayerTurnOnScreen() {
        playerTurn = currentPlayer ? "Votre Tour" : "Tour de l'IA";
        int x = getXforCenteredText(playerTurn);
        g2d.drawString(playerTurn, x, 500);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2d = (Graphics2D) g;

        if (!boardModel.getBall().isBallStart()) {
            renderPlayerTurn();
        }

        labelScore1.setText("Votre Score : " + player1.getScore());
        labelScoreIA.setText("Score de l'IA : " + playerIA.getScore());
        boardModel.getBall().setCurrentPlayer(!currentPlayer);


        // if (!currentPlayer) {
        //     //System.out.println("tour de l'ia");
        //     Point p = new Point(300, 300) ;
        //     double normeVect = (Math.sqrt(Math.pow(p.getX() - getBounds().getWidth() / 2, 2) + Math.pow(p.getY() - 50, 2)));
        //     boardModel.getBall().setVitesseX((p.getX() - getBounds().getWidth() / 2) / normeVect);
        //     boardModel.getBall().setVitesseY(p.getY() / normeVect);
        //     // boardModel.setBallStart(true);
        //     // currentPlayer = !currentPlayer ;
        // }

        if (!boardModel.getGenerator().hasOrangePeg()){
            String str ;
            if (player1.getScore() < playerIA.getScore()){
                str = "IA WIN";
            }
            else if (player1.getScore() > playerIA.getScore()){
                str = "YOU WIN" ;
            }
            else{
                str = "EQUALITY" ;
            }
            drawGameWiningScreen(str);
        }
    }

    public void renderPlayerTurn() {
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 50));
        printPlayerTurnOnScreen();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // (currentPlayer && !boardModel.getBall().isBallStart())
        if (!boardModel.getBall().isBallStart()){
            currentPlayer = !currentPlayer;
            super.mousePressed(e);
        }
    }

    public boolean getCurrentPlayer() {
        return currentPlayer;
    }
}