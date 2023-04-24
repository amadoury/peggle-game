import java.util.concurrent.ThreadLocalRandom;
import javax.swing.* ;
import java.awt.* ;
import java.awt.event.MouseEvent;

public class BoardIA extends BoardMain {
    private Player player1 = new Player();
    private Player player2 = new Player();
    private boolean currentPlayer; //true si c'est le tour du player1 sinon false
    private Graphics2D g2d ;
    private String playerTurn;
    private boolean showText = true ;

    public BoardIA(String path, BoardRight right, BoardLeft left){
        super(path, right, left, true);
        //boardModel = new BoardModelIA((int)resolutionScreen, this , right, left);
        int n  = ThreadLocalRandom.current().nextInt(0, 2);
        currentPlayer = n == 0 ? true : false ;
        boardModel.getBall().setBoardIA(this);
    }

    public void printPlayerTurnOnScreen(){
        playerTurn = currentPlayer ? "Tour du Player 1" : "Tour du Player 2";
        int x = getXforCenteredText(playerTurn) ;
        g2d.drawString(playerTurn, x, 500);   
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g2d = (Graphics2D)g ;  
        
        if (!boardModel.getBall().isBallStart()){
            renderPlayerTurn();
        }

        //left.updateScore(player1.getScore(), player2.getScore());
    
        System.out.println("p 1 :" + player1.getScore());
        System.out.println("p 2 :" + player2.getScore());
    }



    public void renderPlayerTurn(){
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 50));
        printPlayerTurnOnScreen();
    }

    @Override
    public void mousePressed(MouseEvent e){
        super.mousePressed(e);
        currentPlayer = !currentPlayer ;
    }

    public boolean getCurrentPlayer(){
        return currentPlayer;
    }
}