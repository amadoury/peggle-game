import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class Page1 extends JFrame implements ActionListener {
    private JButton nextPageButton;
    private JButton level1Button;
    private JButton level2Button; // new button
    private JButton level3Button; // new button
    private JButton mainMenuButton;

    public Page1() {
        setTitle("world 1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 1000);
        setLocationRelativeTo(null);

        // Load the image from file "final1.png"
        URL imageUrl = getClass().getResource("final1.png");
        ImageIcon icon = new ImageIcon(imageUrl);
        Image image = icon.getImage();

        // Create a JLabel with the image as background
        JLabel backgroundLabel = new JLabel(new ImageIcon(image));
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
        getContentPane().add(backgroundLabel);

        // Create a panel for the other components
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false); // Make the panel transparent
        getContentPane().add(panel);

        // Create a panel for the Next World button
        JPanel nextPagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        nextPagePanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20); // Margin around the buttons
        gbc.anchor = GridBagConstraints.NORTH; // Align the button at the top center
        gbc.weightx = 1; // Center the button horizontally
        panel.add(nextPagePanel, gbc);

        nextPageButton = new JButton("Next world");
        nextPageButton.addActionListener(this);
        nextPageButton.setPreferredSize(new Dimension(100, 25));
        nextPagePanel.add(nextPageButton);

        // Create a panel for the Level 1 button
        JPanel level1Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        level1Panel.setOpaque(true);
        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE; // Don't stretch the button
        gbc.anchor = GridBagConstraints.CENTER; // Align the button at center
        gbc.weighty = 1; // Add some empty space above the button
        panel.add(level1Panel, gbc);

        level1Button = new JButton("Level 1");
        level1Button.addActionListener(this);
        level1Button.setPreferredSize(new Dimension(100, 25));
        level1Panel.add(level1Button);

        // Create a panel for the Level 2 button
        JPanel level2Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        level2Panel.setOpaque(true);
        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE; // Don't stretch the button
        gbc.anchor = GridBagConstraints.CENTER; // Align the button at center
        gbc.weighty = 1; // Add some empty space above the button
        panel.add(level2Panel, gbc);

        level2Button = new JButton("Level 2");
        level2Button.addActionListener(this);
        level2Button.setPreferredSize(new Dimension(100, 25));
        level2Panel.add(level2Button);

        // Create a panel for the Level 3 button
        JPanel level3Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        level3Panel.setOpaque(true);
        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE; // Don't stretch the button
        gbc.anchor = GridBagConstraints.CENTER; // Align the button at center
        gbc.weighty = 1; // Add some empty space above the button
        panel.add(level3Panel, gbc);

        level3Button = new JButton("Level 3");
        level3Button.addActionListener(this);
        level3Button.setPreferredSize(new Dimension(100, 25));
        level3Panel.add(level3Button);
        
        // Create a panel for the Main Menu button
        JPanel mainMenuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        mainMenuPanel.setOpaque(false);
        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE; // Don't stretch the button
        gbc.anchor = GridBagConstraints.SOUTHWEST; // Align the button at bottom left
        gbc.weighty = 1; // Add some empty space above the button
        panel.add(mainMenuPanel, gbc);

        mainMenuButton = new JButton("Main Menu");
        mainMenuButton.addActionListener(this);
        mainMenuButton.setPreferredSize(new Dimension(100, 25));
        mainMenuPanel.add(mainMenuButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        dispose();
        if (e.getSource() == nextPageButton) {
            new Page2(this);
        }
        // add else if statements for other buttons if needed
    }

    public static void main(String[] args) {

        new Page1();
    }
}