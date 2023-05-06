import java.awt.* ;
import javax.swing.*;


public class Main extends JFrame {
    private Dimension dim ;
    private JPanel mainPanel = new JPanel() ;
    private CardLayout cardLayout = new CardLayout() ;

    public Main(Dimension dim){
        this.dim = dim ;
        mainPanel.setLayout(cardLayout);

        MenuPrincipal menup = new MenuPrincipal(dim) ;
        mainPanel.add(menup, "menup");
        MenuLevel menuLevel = new MenuLevel(dim) ;
        mainPanel.add(menuLevel, "menuLevel") ;

        App appEdit = new App(dim, cardLayout, mainPanel) ;
        mainPanel.add(appEdit, "appEdit") ;

        Tutorial tutorial = new Tutorial(dim, cardLayout, mainPanel) ;
        mainPanel.add(tutorial, "tutorial") ;

        this.add(mainPanel) ;

        cardLayout.show(mainPanel, "menup");

        menup.playButton.addActionListener((event) ->{
            cardLayout.show(mainPanel, "menuLevel");
        });

        menup.tutoButton.addActionListener((event) ->{
            cardLayout.show(mainPanel, "tutorial");
        });

        menup.exitButton.addActionListener((event) ->{
            System.exit(1);
        });

        menup.editButton.addActionListener((event) -> {
            cardLayout.show(mainPanel, "appEdit");
        });

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}