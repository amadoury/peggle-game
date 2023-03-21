import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PhoneMenu extends JFrame {
    private JPanel buttonPanel;
    private JButton level1Button;
    private JPanel backgroundPanel;
    private JButton goToSecondMenuButton;

    public PhoneMenu() {
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
        level1Button = new JButton("Level 1");
        buttonPanel.add(level1Button, gbc);

        // Set up the JPanel for the background image
        backgroundPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image backgroundImage = new ImageIcon("thback.png").getImage();
                g.drawImage(backgroundImage, 0, 0, null);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        // Add the button panel to the center of the background panel
        backgroundPanel.add(buttonPanel, BorderLayout.CENTER);

        // Add a button to go to the second menu at the top of the first menu
        goToSecondMenuButton = new JButton("Go to Level 2");
        goToSecondMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // close the current menu
                PhoneMenu2 menu2 = new PhoneMenu2(); // create the second menu
                menu2.setVisible(true); // show the second menu
            }
        });
        add(goToSecondMenuButton, BorderLayout.NORTH);

        // Add the background panel to the JFrame
        add(backgroundPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        PhoneMenu menu = new PhoneMenu();
        menu.setVisible(true);
    }
}
