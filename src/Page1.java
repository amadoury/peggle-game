import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class Page1 extends JFrame implements ActionListener {
    private JButton nextPageButton;
    private JButton level1Button;
    private JButton mainMenuButton;

    public Page1() {
        setTitle("world 1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setLocationRelativeTo(null);

        // Charger l'image à partir du fichier "image.img"
        URL imageUrl = getClass().getResource("final.png");
        ImageIcon icon = new ImageIcon(imageUrl);
        Image image = icon.getImage();

        // Créer un JLabel avec l'image en arrière-plan
        JLabel backgroundLabel = new JLabel(new ImageIcon(image));
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
        getContentPane().add(backgroundLabel);

        // Créer un panel pour les autres composants
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false); // Rendre le panel transparent
        getContentPane().add(panel);

        // Créer un panel pour le bouton Next World
        JPanel nextPagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        nextPagePanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20); // Marge autour des boutons
        gbc.anchor = GridBagConstraints.NORTH; // Aligner le bouton en haut au centre
        gbc.weightx = 1; // Faire en sorte que le bouton soit centré horizontalement
        panel.add(nextPagePanel, gbc);

        nextPageButton = new JButton("Next world");
        nextPageButton.addActionListener(this);
        nextPageButton.setPreferredSize(new Dimension(100, 25));
        nextPagePanel.add(nextPageButton);

        // Créer un panel pour le bouton Level 1
        JPanel level1Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        level1Panel.setOpaque(false);
        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE; // Ne pas étirer le bouton
        gbc.anchor = GridBagConstraints.CENTER; // Aligner le bouton au centre
        gbc.weighty = 1; // Ajouter de l'espace vide au-dessus du bouton
        panel.add(level1Panel, gbc);

        level1Button = new JButton("Level 1");
        level1Button.addActionListener(this);
        level1Button.setPreferredSize(new Dimension(100, 25));
        level1Panel.add(level1Button);

        // Créer un panel pour le bouton Main Menu
        JPanel mainMenuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        mainMenuPanel.setOpaque(false);
        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE; // Ne pas étirer le bouton
        gbc.anchor = GridBagConstraints.SOUTHWEST; // Aligner le bouton en bas à gauche
        gbc.weighty = 1; // Ajouter de l'espace vide au-dessus du bouton
        panel.add(mainMenuPanel, gbc);

        mainMenuButton = new JButton("Main Menu");
        mainMenuButton.addActionListener(this);
        mainMenuButton.setPreferredSize(new Dimension(100, 25));
        mainMenuPanel.add(mainMenuButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        dispose();
        new Page2(this);
    }

    public static void main(String[] args) {
        new Page1();
    }
}