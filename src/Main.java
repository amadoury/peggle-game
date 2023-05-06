import java.awt.*;
import javax.swing.*;

public class Main extends JFrame {
    private Dimension dim;
    private JPanel mainPanel = new JPanel();
    private CardLayout cardLayout = new CardLayout();

    public Main(Dimension dim) {
        this.dim = dim;
        mainPanel.setLayout(cardLayout);

        MenuPrincipal menup = new MenuPrincipal(dim);
        mainPanel.add(menup, "menup");
        MenuLevel menuLevel = new MenuLevel(dim);
        mainPanel.add(menuLevel, "menuLevel");

        this.add(mainPanel);

        cardLayout.show(mainPanel, "menup");

        menup.playButton.addActionListener((event) -> {
            cardLayout.show(mainPanel, "menuLevel");
        });

        menup.editButton.addActionListener((event) -> {
            cardLayout.show(mainPanel, "menuLevel");
        });

        menup.exitButton.addActionListener((event) -> {
            dispose();
        });

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}