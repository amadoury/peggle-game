import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class Page3 extends JFrame implements ActionListener {
    private JButton previousPageButton;
    private JFrame previousPage;

    public Page3(JFrame previousPage) {
        this.previousPage = previousPage;
        setTitle("World 3");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setLocationRelativeTo(null);

        // Charger l'image à partir du fichier "image.img"
        URL imageUrl = getClass().getResource("trois.png");
        ImageIcon icon = new ImageIcon(imageUrl);
        Image image = icon.getImage();

        // Créer un JLabel avec l'image en arrière-plan
        JLabel backgroundLabel = new JLabel(new ImageIcon(image));
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
        getContentPane().add(backgroundLabel);

        // Créer un panel pour les autres composants
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false); // Rendre le panel transparent

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20); // Marge autour des boutons
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH; // Aligner les boutons en haut au centre
        gbc.weightx = 1; // Faire en sorte que le bouton soit centré horizontalement

        previousPageButton = new JButton("Previous World");
        previousPageButton.addActionListener(this);
        panel.add(previousPageButton, gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE; // Ne pas étirer le bouton
        gbc.anchor = GridBagConstraints.CENTER; // Aligner le bouton au centre
        gbc.weighty = 1; // Ajouter de l'espace vide au-dessus du bouton
        JButton level3Button = new JButton("Level 3");
        level3Button.addActionListener(this);
        panel.add(level3Button, gbc);

        getContentPane().add(panel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == previousPageButton) {
            dispose();
            previousPage.setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Page3(null);
    }
}
