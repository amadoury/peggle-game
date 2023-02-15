import javax.swing.*;
import java.awt.* ;

public class BoardLeft extends JPanel  {
    private JLabel labelNumberBall ;
    private JLabel labelPeg ; 
    private JLabel labelButton ; 
    private JButton buttonEdit ; 


    public BoardLeft(int number) {

        labelNumberBall = new JLabel(number + "") ;

        labelPeg = new JLabel("Peg") ;
        labelPeg.setLocation(0, 50) ;


        labelButton = new JLabel("button") ;
        labelButton.setLocation(0, 50);


        setLayout(new GridBagLayout()) ;

        GridBagConstraints gbc = new GridBagConstraints() ;

        gbc.fill =  GridBagConstraints.HORIZONTAL;
        gbc.ipady = 100 ;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
       //gbc.ipady = 1200 ;

        add(labelNumberBall , gbc);

        gbc.fill =  GridBagConstraints.HORIZONTAL;
        gbc.ipady = 100 ;

        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;

        add(labelPeg, gbc) ;

        gbc.fill =  GridBagConstraints.HORIZONTAL;
        gbc.ipady = 100 ;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(labelButton, gbc);
     
        this.add(labelNumberBall) ;
        this.add(labelPeg) ;
        this.add(labelButton) ;
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