import javax.swing.* ;
import java.awt.* ;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import javax.imageio.ImageIO;
import java.awt.event.*;


public class MenuPrincipal extends JPanel {
    private Image imgbcg ; 
    private JPanel ButtonPanel = new JPanel();
    private int width,height;
    public JButton playButton = createButton("PLAY");
    public JButton tutoButton = createButton("TUTORIAL");
    public JButton editButton = createButton("EDITOR");
    public JButton exitButton = createButton("EXIT");
    private Dimension dim ;

    public MenuPrincipal(Dimension dim){
        this.dim = dim ;
        try{
            imgbcg = ImageIO.read(this.getClass().getResource("ressources/bcg-Menu-principal.jpg")) ;
        }
        catch(Exception e){
            e.printStackTrace();
        }

        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();


        width = (int)screensize.getWidth();
        height = (int)screensize.getHeight();

        ButtonPanel.setLayout(null);
        ButtonPanel.setOpaque(false);
        ButtonPanel.setPreferredSize(new Dimension(1920, 1080));        

        playButton.setBounds((int)(width*0.42),(int)(height*0.40),360,95);
        tutoButton.setBounds((int)(width*0.42),(int)(height*0.52),360,95);
        editButton.setBounds((int)(width*0.42),(int)(height*0.64),360,95);
        exitButton.setBounds((int)(width*0.42),(int)(height*0.76),360,95);


        ButtonPanel.add(playButton);
        ButtonPanel.add(tutoButton); 
        ButtonPanel.add(editButton);
        ButtonPanel.add(exitButton);

        this.add(ButtonPanel);
        this.setVisible(true);
    }


    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g ;
        g2d.drawImage(imgbcg, 0, 0, (int)dim.getWidth() , (int)dim.getHeight() , null, null);
    }


    private JButton createButton(String text){
      JButton button = new JButton(text);

      String path_font = "ressources/font_style/font.ttf";
      InputStream is ;
      Font font ;

      try{
        is = LevelMenu.class.getResourceAsStream(path_font);
        font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(57f);
        button.setFont(font);
      }
      catch(Exception e){
        e.printStackTrace();
      }
      button.setFocusPainted(false);
    
      // 178,255,102
      // 153,255,51
      Color colorPrinciaple = new Color(153,255,51);
      // Color colorPrinciaple = new Color(212,226,8);
      Color colorClicked = new Color(165,176,3);
      Color textColor = new Color(153,0,153);
      button.setBackground(colorPrinciaple);
      button.setForeground(textColor);


      button.addMouseListener(new MouseAdapter() {
        public void mouseEntered(MouseEvent e) {
            button.setBackground(Color.WHITE);
            button.setForeground(Color.BLACK);
        }
      });

      button.addMouseListener(new MouseAdapter() {
        public void mouseExited(MouseEvent e) {
            button.setBackground(colorPrinciaple);
            button.setForeground(textColor);
        }
      });

      button.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
          button.setBackground(colorClicked);
        }
      });

      return button;
    }
}
