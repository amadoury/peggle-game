import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class App extends JFrame {

    private Dimension dimensionFrame;

    private JPanel left = new JPanel();
    private JPanel right = new JPanel();
    private BoardMain boardMain;
    private LevelMenu levelMenu ;
    private boolean isEditing = false ;
    

    public App() {
        initUI();
    }

    private void initUI() {
        boardMain = new BoardMain(null);
        BoardEdit boardEdit = new BoardEdit() ;
        levelMenu = new LevelMenu() ;

        CardLayout cardLayout = new CardLayout() ;
        JPanel panelBoard = new JPanel() ;
        panelBoard.setLayout(cardLayout) ;
        panelBoard.add(boardMain, "boardMain") ;
        panelBoard.add(boardEdit, "boardEdit") ;
        panelBoard.add(levelMenu, "levelMenu") ;

        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;

        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        add(left, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 3;
        c.ipady = 1500;
        c.gridx = 1;
        c.gridy = 0;
    
        cardLayout.show(panelBoard, "levelMenu");
        //cardLayout.show(panelBoard, "levelMenu");

        levelMenu.getLevel1().addActionListener((event) -> {
            BoardMain bd = new BoardMain("src/ressources/level/level1.txt");
            panelBoard.add(bd, "boardlevel1");
            cardLayout.show(panelBoard, "boardlevel1");
            setParams(bd);
        });

        levelMenu.getLevel2().addActionListener((event) -> {
            BoardMain bd = new BoardMain("src/ressources/level/level2.txt");
            panelBoard.add(bd, "boardlevel2");
            cardLayout.show(panelBoard, "boardlevel2");
            setParams(bd);
        });


        levelMenu.getLevel3().addActionListener((event) -> {
            BoardMain bd = new BoardMain("src/ressources/level/level3.txt");
            panelBoard.add(bd, "boardlevel3");
            cardLayout.show(panelBoard, "boardlevel3");
            setParams(bd);
        });

        //add(boardEdit , c); 
        //cardLayout.show(panelBoard, "boardMain");
        add(panelBoard , c); 

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 0;
        add(right, c);

        pack();

        // Dimension dim = Toolkit.getDefaultToolkit().getScreenSize() ;

        boardEdit.setApp(this) ;
        setParams(boardMain);

        // boardMain.setDimensionBoard(boardMain.getSize());

        // dimensionFrame = this.getBounds().getSize();

        // boardMain.setWidthScreen(dimensionFrame.getWidth());
        // boardMain.setHeightScreen(dimensionFrame.getHeight());


        // boardEdit.setWidthScreen(dimensionFrame.getWidth());
        // boardEdit.setHeightScreen(dimensionFrame.getHeight());

        setTitle("App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.addComponentListener(new ResizeListener());

    }

    public void setParams(BoardMain boardMain){
        boardMain.setDimensionBoard(boardMain.getSize());

        dimensionFrame = this.getBounds().getSize();

        boardMain.setWidthScreen(dimensionFrame.getWidth());
        boardMain.setHeightScreen(dimensionFrame.getHeight());
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            App app = new App();
            app.setVisible(true);
        });
    }

    private class ResizeListener implements ComponentListener {
        public void componentHidden(ComponentEvent e) {
        }

        public void componentMoved(ComponentEvent e) {
        }

        public void componentShown(ComponentEvent e) {
        }

        public void componentResized(ComponentEvent e) {
            dimensionFrame = e.getComponent().getBounds().getSize();
            boardMain.setWidthScreen(dimensionFrame.getWidth());
            boardMain.setHeightScreen(dimensionFrame.getHeight());
        }
    }
}