/*
 * @author Aurelien 
 */
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;



public class MenuPrincipal extends JFrame {
    private ImagePanel PanelImage = new ImagePanel(new ImageIcon("src/ressources/menu.png").getImage());
    private JPanel ButtonPanel = new JPanel();
    private JButton playButton = new JButton();
    private JButton paramButton = new JButton();
    private JButton exitButton = new JButton();

    public MenuPrincipal() {
      setTitle("JEU");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(1400, 1000);
      setResizable(false);

      playButton.setIcon(new ImageIcon(getClass().getResource("ressources/buttonPlay.png")));
      paramButton.setIcon(new ImageIcon(getClass().getResource("ressources/buttonParam.png")));
      exitButton.setIcon(new ImageIcon(getClass().getResource("ressources/buttonExit.png")));

      ButtonPanel.setLayout(null);
      playButton.setBounds(475,700,215,105);
      paramButton.setBounds(720,700,421,105);
      exitButton.setBounds(1170,700,198,105);

      playButton.setBorder(null);
      playButton.setOpaque(false); playButton.setContentAreaFilled(false); playButton.setBorderPainted(false); playButton.setFocusPainted(false);
      paramButton.setOpaque(false); paramButton.setContentAreaFilled(false); paramButton.setBorderPainted(false); paramButton.setFocusPainted(false);
      exitButton.setOpaque(false); exitButton.setContentAreaFilled(false); exitButton.setBorderPainted(false); exitButton.setFocusPainted(false);



      ButtonPanel.add(playButton); ButtonPanel.add(paramButton); ButtonPanel.add(exitButton);
        
        playButton.addActionListener(e -> 
        {
            // renvoie vers le menuNiveau
            PlayActionPerformed(e);
        });

        paramButton.addActionListener(e -> 
        {
            // renvoie vers le menu des paramètres
            ParamActionPerformed(e);
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

    private void ParamActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // Parameters Menu
    }                                        

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {                                         
        System.exit(0);
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
          setSize(1400,1000);
          setLayout(null);
        }
      
        public void paintComponent(Graphics g) {
          g.drawImage(img, 0, 0, null);
        }
    }
}


