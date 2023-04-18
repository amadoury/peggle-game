import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class Page2 extends JFrame implements ActionListener {
    private JButton previousPageButton;
    private JButton nextPageButton;
    private JButton level1Button;
    private JButton level2Button;
    private JButton level3Button;
    private JFrame previousPage;

    public Page2(JFrame previousPage) {
        this.previousPage = previousPage;
        setTitle("World 2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 1000);
        setLocationRelativeTo(null);

        // Load the image from file "DEUX.png"
        URL imageUrl = getClass().getResource("deux1.png");
        ImageIcon icon = new ImageIcon(imageUrl);
        Image image = icon.getImage();

        // Create a JLabel with the image as background
        JLabel backgroundLabel = new JLabel(new ImageIcon(image));
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
        getContentPane().add(backgroundLabel);

        // Create a panel for the other components
        JPanel panel = new JPanel();
        panel.setOpaque(false); // Make the panel transparent
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
        level1Button = new JButton("Level 1");
        level1Button.addActionListener(this);
        level1Button.setFont(new Font("Arial", Font.PLAIN, 14));
        level1Button.setBounds(getWidth() / 2 - 50, getHeight() / 2 - 60, 100, 30);
        panel.add(level1Button);

        // Create and add the "Level 2" button
        level2Button = new JButton("Level 2");
        level2Button.addActionListener(this);
        level2Button.setFont(new Font("Arial", Font.PLAIN, 14));
        level2Button.setBounds(getWidth() / 2 - 50, getHeight() / 2 - 15, 100, 30);
        panel.add(level2Button);

        // Create and add the "Level 3" button
        level3Button = new JButton("Level 3");
        level3Button.addActionListener(this);
        level3Button.setFont(new Font("Arial", Font.PLAIN, 14));
        level3Button.setBounds(getWidth() / 2 - 50, getHeight() / 2 + 30, 100, 30);
        panel.add(level3Button);

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
        } else if (e.getSource() == level1Button) {
            // handle Level 1 button click here
        } else if (e.getSource() == level2Button) {
            // handle Level 2 button click here
        } else if (e.getSource() == level3Button) {
            // handle Level 3 button click here
        }
    }

    public static void main(String[] args) {
        new Page2(null);
    }
}