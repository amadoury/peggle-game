import javax.swing.*;
import java.awt.* ;
import java.util.ArrayList;

public class BoardLeft extends JPanel  {
    private JLabel labelNumberBall ;
    private JLabel labelPeg ; 
    private JLabel labelButton ; 
    private JButton buttonEdit ; 
    private JLabel labelImgBall ;
    int rayon = 12 ;


    public BoardLeft(int number) {
        ArrayList<JLabel> listLabelBall = new ArrayList<JLabel>() ;

        setLayout(null);

        int x, y ; 
        x = 10; y = 0 ;

        for(int i = 0; i < number; i++){
            ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("ressources/ball.png"));
            JLabel labelImgBall = new JLabel(imageIcon) ;

            Image image = imageIcon.getImage(); // transform it
            Image newimg = image.getScaledInstance(2 * rayon, 2 * rayon, java.awt.Image.SCALE_SMOOTH); 
            imageIcon = new ImageIcon(newimg); // transform it back
            labelImgBall.setIcon(imageIcon);

            listLabelBall.add(labelImgBall) ;

            labelImgBall.setBounds(x - rayon, y - rayon, 2 * rayon, 2 * rayon);;
            y += 2 * rayon + 5 ;

           // add(labelImgBall) ;
        }

        // int x = 0 ; 
        // int y = 0 ;

        // for(int i = 0; i < number; i++){
        //     //listLabelBall.get(i).setBounds(x - rayon, y - rayon, 2 * rayon, 2 * rayon);
        //     listLabelBall.get(i).setLocation(x, y);
        //    // y += 2 * rayon + 5 ;
        // }
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