import java.lang.Math ;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.* ;
import java.awt.* ;
import java.awt.event.MouseEvent;

public class BoardIA extends BoardMain {
    private Player player1;
    private Player player2;
    private boolean currentPlayer; //true si c'est le tour du player1 sinon false
    private Graphics2D g2d ;
    private String playerTurn;
    private boolean showText = true ;

    public BoardIA(String path, BoardRight right){
        super(path, right);
        int n  = ThreadLocalRandom.current().nextInt(0, 2);
        currentPlayer = n == 0 ? true : false ;
    }

    public void printPlayerTurnOnScreen(){
        playerTurn = currentPlayer ? "Tour du Player 1" : "Tour du Player 2";
        int x = getXforCenteredText(playerTurn) ;
        g2d.drawString(playerTurn, x, 500);   
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g2d = (Graphics2D)g ;

        renderPlayerTurn();

        runPlayerTurnLogic();
    }

    public void runPlayerTurnLogic() {
        Timer timer = new Timer(3000, (action) -> {
            showText = false;
        });
        timer.setRepeats(false);
        timer.start();
        //t.stop();
    }
    
    public void renderPlayerTurn() {
        if (showText) {
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, 50));
            printPlayerTurnOnScreen();
        }
    }

    @Override
    public void mousePressed(MouseEvent e){
        super.mousePressed(e);
        showText  = true ;
        repaint();
    }
}