import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.ImageIcon;
import java.awt.event.*;




public class MenuPrincipal extends JFrame {
    private ImagePanel PanelImage = new ImagePanel(new ImageIcon("src/ressources/menu.jpg").getImage());
    private JPanel ButtonPanel = new JPanel();

    private int width,height;
    private JButton playButton = createButton("PLAY",(int)(width*0.64),(int)(height*0.35));
    private JButton editButton = createButton("EDITOR",(int)(width*0.64),(int)(height*0.5));
    private JButton exitButton = createButton("EXIT",(int)(width*0.64),(int)(height*0.65));

    public MenuPrincipal() throws FontFormatException, IOException {
      Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
      setTitle("JEU");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(1920, 1080);
      setResizable(false);
      width = (int)screensize.getWidth();
      height = (int)screensize.getHeight();

      ButtonPanel.setLayout(null);
      ButtonPanel.setOpaque(false);
      ButtonPanel.setPreferredSize(new Dimension(1920, 1080));

      playButton.setBounds((int)(width*0.64),(int)(height*0.35),325,105);
      editButton.setBounds((int)(width*0.64),(int)(height*0.5),325,105);
      exitButton.setBounds((int)(width*0.64),(int)(height*0.65),325,105);



      ButtonPanel.add(playButton); ButtonPanel.add(editButton); ButtonPanel.add(exitButton);
        
        playButton.addActionListener(e -> 
        {
          // renvoie vers le menuNiveau
          PlayActionPerformed(e);
        });

        editButton.addActionListener(e -> 
        {
          // renvoie vers l'éditeur
          EditorActionPerformed(e);
        });

        exitButton.addActionListener(e ->
        {
          ExitActionPerformed(e);
        });

        this.getContentPane().add(PanelImage);
        this.getContentPane().add(ButtonPanel);
        this.setVisible(true);
    }

    private void PlayActionPerformed(java.awt.event.ActionEvent evt) {                                         
      // Level Selection menu
    }                                        

    private void EditorActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // Editor
    }                                        

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {                                         
        System.exit(0);
    }  

    private JButton createButton(String text,int width,int height) throws FontFormatException, IOException{
      JButton button = new JButton(text);

      String path_font = "ressources/font_style/font.ttf";
      InputStream is = LevelMenu.class.getResourceAsStream(path_font);
      Font font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(65f);
      button.setFocusPainted(false);
      button.setFont(font);
      Color colorPrinciaple = new Color(212,226,8);
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

  class ImagePanel extends JPanel {

    private Image img;
  
    public ImagePanel(String img) {
      this(new ImageIcon(img).getImage());
    }
  
    public ImagePanel(Image img) {
      this.img = img;
      Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
      setPreferredSize(size);
      setMinimumSize(size);
      setMaximumSize(size);
      setSize(1920,1080);
      setLayout(null);
    }
  
    public void paintComponent(Graphics g) {
      g.drawImage(img, 0, 0, null);
    }
}


    // class RoundBtn implements Border {
    //   private int r;
    //   RoundBtn(int r) {
    //       this.r = r;
    //   }
    //   public Insets getBorderInsets(Component c) {
    //       return new Insets(this.r+1, this.r+1, this.r+2, this.r);
    //   }
    //   public boolean isBorderOpaque() {
    //       return true;
    //   }
    //   public void paintBorder(Component c, Graphics g, int x, int y, 
    //   int width, int height) {
    //       g.drawRoundRect(x, y, width-1, height-1, r, r);
    //   }
    // }  

  public static void main(String[] args) throws FontFormatException, IOException{
    new MenuPrincipal();
  }
}





