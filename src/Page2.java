import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class Page2 extends JFrame implements ActionListener {
    private JButton previousPageButton;
    private JButton nextPageButton;
    private JFrame previousPage;
    
    public Page2(JFrame previousPage) {
        this.previousPage = previousPage;
        setTitle("World 2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setLocationRelativeTo(null);
        
        // Charger l'image à partir du fichier "image.img"
        URL imageUrl = getClass().getResource("DEUX.png");
        ImageIcon icon = new ImageIcon(imageUrl);
        Image image = icon.getImage();
        
        // Créer un JLabel avec l'image en arrière-plan
        JLabel backgroundLabel = new JLabel(new ImageIcon(image));
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
        getContentPane().add(backgroundLabel);
        
        // Créer un panel pour les autres composants
        JPanel panel = new JPanel();
        panel.setOpaque(false); // Rendre le panel transparent
        getContentPane().add(panel);
        
        previousPageButton = new JButton("Previous World");
        previousPageButton.addActionListener(this);
        panel.add(previousPageButton);

        // Set the location of the "Previous World" button
        previousPageButton.setBounds(10, 10, 120, 30);
        
        nextPageButton = new JButton("Next World");
        nextPageButton.addActionListener(this);
        panel.add(nextPageButton);

        // Set layout to null for absolute positioning
        panel.setLayout(null);

        // Create and add the "Level 1" button
        JButton level1Button = new JButton("Level 2");
        level1Button.setFont(new Font("Arial", Font.PLAIN, 14));
        level1Button.setBounds(getWidth() / 2 - 50, getHeight() / 2 - 15, 100, 30);
        panel.add(level1Button);

        // Set the location of the "Next World" button
        nextPageButton.setBounds(getWidth() - 150, 10, 120, 30);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == previousPageButton) {
            dispose();
            previousPage.setVisible(true);
        } else if (e.getSource() == nextPageButton) {
            dispose();
            new Page3(this);
        }
    }
    
    public static void main(String[] args) {
        new Page2(null);
    }
}

