import javax.swing.*;
import java.awt.* ;

public class BoardLeft extends JPanel  {
    private JLabel labelNumberBall ;
    private JLabel labelPeg = new JLabel("Peg") ; 
    private JLabel labelButton = new JLabel("Button");

    JButton b1 = new JButton("1") ;
    JButton b2 = new JButton("2") ;
    JButton b3 = new JButton("3") ;


    public BoardLeft(int number) {
        labelNumberBall = new JLabel() ;
        labelNumberBall.setText(number + "") ;

        setLayout(new GridLayout(3, 1));
        // this.add(labelNumberBall) ;
        // this.add(labelPeg) ;
        // this.add(labelButton) ;

        this.add(b1) ;
        this.add(b2) ;
        this.add(b3) ;

    }

    public void updateLabelBall(int number) {
        labelNumberBall.setText(number + "") ;
    }

    public void setNumberBall(int number) {
        this.labelNumberBall.setText(number + " ") ;
    }

    public void setLabelBall(String s){
        this.labelNumberBall.setText(s);
    }
}