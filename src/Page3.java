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
        setSize(1300, 1000);
        setLocationRelativeTo(null);

        // Load the image from file "trois.png"
        URL imageUrl = getClass().getResource("trois1.png");
        ImageIcon icon = new ImageIcon(imageUrl);
        Image image = icon.getImage();

        // Create a JLabel with the image as background
        JLabel backgroundLabel = new JLabel(new ImageIcon(image));
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
        getContentPane().add(backgroundLabel);

        // Create a panel for the other components
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false); // Make the panel transparent

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 20, 5, 20); // Margin around the buttons
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH; // Align the buttons to the top center
        gbc.weightx = 1; // Center the button horizontally
        gbc.ipady = 10; // Reduce the height of the buttons

        previousPageButton = new JButton("Previous World");
        previousPageButton.addActionListener(this);
        panel.add(previousPageButton, gbc);

        gbc.gridy++;
        JButton level1Button = new JButton("Level 1");
        level1Button.addActionListener(this);
        panel.add(level1Button, gbc);

        gbc.gridy++;
        JButton level2Button = new JButton("Level 2");
        level2Button.addActionListener(this);
        panel.add(level2Button, gbc);

        gbc.gridy++;
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
        } else if (e.getActionCommand().equals("Level 1")) {
            // handle Level 1 button click here
        } else if (e.getActionCommand().equals("Level 2")) {
            // handle Level 2 button click here
        } else if (e.getActionCommand().equals("Level 3")) {
            // handle Level 3 button click here
        }
    }

    public static void main(String[] args) {
        new Page3(null);
    }
}