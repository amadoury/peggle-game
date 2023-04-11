
import javax.swing.* ;
import javax.swing.border.EmptyBorder;

import java.awt.* ;
import java.io.IOException;
import java.io.InputStream;

class LevelMenu extends JPanel{
    private JLabel text = new JLabel("Level", SwingConstants.CENTER) ;
    private JPanel level = new JPanel() ;
    private JPanel header = new JPanel() ;
    private JButton level1 = new JButton("1") ;
    private JButton level2 = new JButton("2") ;
    private JButton level3 = new JButton("3");
    private JButton level4 = new JButton("4");
    private JButton level5 = new JButton("5");
    private JButton level6 = new JButton("6");
    private JButton level7 = new JButton("7");
    public int width, height ;

    public LevelMenu() throws FontFormatException, IOException{

        text.setBorder(new EmptyBorder(new Insets(75, 0, 0, 0)));
        // text.setFont(new Font("Serif", Font.BOLD, 50));
        // header.add(text) ;

        String path_font = "ressources/font_style/font.ttf";
        InputStream is = LevelMenu.class.getResourceAsStream(path_font);
        Font font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(65f);
        level1.setFont(font);
        level2.setFont(font);
        level3.setFont(font);
        level4.setFont(font);
        level5.setFont(font);
        level6.setFont(font);
        level7.setFont(font);
        text.setFont(font);
        header.add(text) ;

        header.setBackground(new Color(250, 245 , 230));
        level.setBackground(new Color(250, 245 , 230));
        setLayout(new BorderLayout()) ;
        
        level.add(level1) ;
        level.add(level2) ;
        level.add(level3) ;
        level.add(level4) ;
        level.add(level5) ;
        level.add(level6) ;
        level.add(level7) ;

        text.setAlignmentX(CENTER_ALIGNMENT);
        add(header, BorderLayout.NORTH);
        this.add(level) ;
    }

    public void setDim(Dimension dim){
        this.width = (int)dim.getWidth();
        this.height = (int)dim.getHeight();
        repaint();
    }

    public void paintComponent(Graphics g){
        setPreferredSize(new Dimension(width, height));
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
    public JButton getLevel4(){
        return level4 ;
    }
    public JButton getLevel5(){
        return level5;
    }
    public JButton getLevel6(){
        return level6;
    }
    public JButton getLevel7(){
        return level7;
    }



}