import javax.swing.*;
import java.awt.*;

public class PhoneMenu2 extends JFrame {
    private JPanel buttonPanel;
    private JButton level2Button;
    private JPanel backgroundPanel;

    public PhoneMenu2() {
        // Set up the JFrame
        super("Phone Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 500);
        setLayout(new BorderLayout());

        // Set up the JPanel for the button
        buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        level2Button = new JButton("Level 2");
        buttonPanel.add(level2Button, gbc);

        // Set up the JPanel for the background image
        backgroundPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image backgroundImage = new ImageIcon("secondMAP.png").getImage();
                g.drawImage(backgroundImage, 0, 0, null);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        // Add the button panel to the center of the background panel
        backgroundPanel.add(buttonPanel, BorderLayout.CENTER);
        
        // Add a button to the top of the menu to navigate to PhoneMenu3
        JButton topButton = new JButton("Go to Level 3");
        topButton.addActionListener(e -> {
            PhoneMenu3 menu3 = new PhoneMenu3();
            menu3.setVisible(true);
            dispose();
        });
        backgroundPanel.add(topButton, BorderLayout.NORTH);

        // Add a button to the bottom of the menu to navigate to PhoneMenu
        JButton bottomButton = new JButton("Go to Level 1");
        bottomButton.addActionListener(e -> {
            PhoneMenu menu = new PhoneMenu();
            menu.setVisible(true);
            dispose();
        });
        backgroundPanel.add(bottomButton, BorderLayout.SOUTH);

        // Add the background panel to the JFrame
        add(backgroundPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        PhoneMenu2 menu = new PhoneMenu2();
        menu.setVisible(true);
    }
}
