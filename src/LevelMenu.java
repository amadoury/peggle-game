
import javax.swing.* ;
import javax.swing.border.EmptyBorder;

import java.awt.* ;

class LevelMenu extends JPanel{
    private JLabel text = new JLabel("Level", SwingConstants.CENTER) ;
    private JPanel level = new JPanel() ;
    private JPanel header = new JPanel() ;
    private JButton level1 = new JButton("1") ;
    private JButton level2 = new JButton("2") ;
    private JButton level3 = new JButton("3");
    private CardLayout cardLayout; 

    public LevelMenu(){

        text.setBorder(new EmptyBorder(new Insets(75, 0, 0, 0)));
        text.setFont(new Font("Serif", Font.BOLD, 50));

        header.add(text) ;

        header.setBackground(new Color(250, 245 , 230));
        level.setBackground(new Color(250, 245 , 230));

        setLayout(new BorderLayout()) ;

        level.add(level1) ;
        level.add(level2) ;
        level.add(level3) ;

        text.setAlignmentX(CENTER_ALIGNMENT);
        add(header, BorderLayout.NORTH);
        this.add(level) ;
    }

    public JButton getLevel1(){
        return level1 ;
    }

    public JButton getLevel2(){
        return level2 ;
    }

    public JButton getLevel3(){
        return level3 ;
    }
}