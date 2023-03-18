import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Color;

public class MenuPrincipal extends JFrame {
    private ImagePanel PanelImage = new ImagePanel(new ImageIcon("src/ressources/menu.png").getImage());
    private JPanel ButtonPanel = new JPanel();
    private JButton playButton = new JButton("PLAY");
    private JButton paramButton = new JButton("SETTINGS");
    private JButton exitButton = new JButton("EXIT");

    public MenuPrincipal() throws FontFormatException, IOException {
        super();
        setTitle("JEU");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1400, 1000);
        setResizable(false);

        String path_font = "ressources/font_style/font.ttf";
        InputStream is = MenuPrincipal.class.getResourceAsStream(path_font);
        Font font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(65f);

        Color c = new Color(207, 159, 255);
        playButton.setFont(font);
        playButton.setBackground(c);
        paramButton.setFont(font);
        paramButton.setBackground(c);
        exitButton.setFont(font);
        exitButton.setBackground(c);

        ButtonPanel.setLayout(null);
        playButton.setBounds(475, 700, 215, 105);
        paramButton.setBounds(715, 700, 440, 105);
        exitButton.setBounds(1180, 700, 198, 105);

        ButtonPanel.add(playButton);
        ButtonPanel.add(paramButton);
        ButtonPanel.add(exitButton);

        playButton.addActionListener(e -> {
            // renvoie vers le menuNiveau
            try {
                PlayActionPerformed(e);
            } catch (FontFormatException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        paramButton.addActionListener(e -> {
            // renvoie vers le menu des paramÃ¨tres
            ParamActionPerformed(e);
        });

        exitButton.addActionListener(e -> {
            ExitActionPerformed(e);
        });

        this.getContentPane().add(PanelImage);
        this.getContentPane().add(ButtonPanel);
        this.setVisible(true);
    }

    private void PlayActionPerformed(java.awt.event.ActionEvent evt) throws FontFormatException, IOException {
        // Level Selection menu
        /*
         * this.dispose();
         * LoadingScreen load = new LoadingScreen();
         * //load.setVisible(true);
         */
    }

    private void ParamActionPerformed(java.awt.event.ActionEvent evt) {
        // Parameters Menu
    }

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    /*
     * public static void main(String[] args) throws FontFormatException,
     * IOException{
     * new MenuPrincipal();
     * }
     */

}