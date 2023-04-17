import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BoardLeft extends JPanel {
    private JLabel labelScorePlayer1 = new JLabel() ;
    private JLabel labelScorePlayer2 = new JLabel() ;

    public BoardLeft() {
        this.setLayout(null);
        labelScorePlayer1.setText("Player 1 : 0");
        labelScorePlayer2.setText("Player 2 : 0");
        this.add(labelScorePlayer1);
        this.add(labelScorePlayer2) ;
        labelScorePlayer1.setBounds(10, 100, 200, 50);
        labelScorePlayer2.setBounds(10, 250, 200, 50);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }  

    public void updateScore(int scorePlayer1, int scorePlayer2){
        labelScorePlayer1.setText("Player 1 : " + scorePlayer1);
        labelScorePlayer2.setText("Player 2 : " + scorePlayer2);
    }
}
