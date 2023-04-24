import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class App extends JFrame {

    private Dimension dimensionFrame;

    private BoardRight right = new BoardRight();
    private BoardLeft left = new BoardLeft();

    // private JPanel right = new JPanel() ;

    private JPanel panelBoard;
    private BoardMain boardMain;

    private double width;
    private double height;
    private LevelMenu levelMenu;
    private boolean isEditing = false;
    private CardLayout cardLayout;
    public Dimension dim;

    public App(Dimension dim) {
        this.dim = dim;
        System.out.println("dimension de app " + dim);
        initUI();
    }

    private void initUI() {

        boardMain = new BoardMain(null, right, left);
        BoardEdit boardEdit = new BoardEdit(Toolkit.getDefaultToolkit().getScreenSize());
        levelMenu = new LevelMenu(dim);
        Intro intro = new Intro(dim);
        BoardIA boardIA = new BoardIA("src/ressources/level/level2.txt", right, left);

        cardLayout = new CardLayout();
        panelBoard = new JPanel();
        panelBoard.setLayout(cardLayout);

        panelBoard.add(levelMenu, "levelMenu");

        panelBoard.add(boardMain, "boardMain");
        panelBoard.add(boardEdit, "boardEdit");
        panelBoard.add(intro, "intro");
        panelBoard.add(boardIA, "boardIA");

        // boardMain.setDim(Toolkit.getDefaultToolkit().getScreenSize());
        // cardLayout.show(panelBoard, "intro");
        cardLayout.show(panelBoard, "boardIA");

        width = (6. / 8.) * dim.getWidth();
        height = dim.getHeight() - 100;
        double xStart = (1. / 8.) * dim.getWidth();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);
        this.add(panelBoard);
        this.add(right);
        this.add(left);

        panelBoard.setBounds((int) xStart, 0, (int) width, (int) height);
        right.setBounds((int) (xStart + width), 0, (int) xStart, (int) height);
        left.setBounds(0, 0, (int) xStart, (int) height);

        intro.listPage.get(2).launch.addActionListener((event) -> {
            cardLayout.show(panelBoard, "levelMenu");
        });

        levelMenu.getLevel1().addActionListener((event) -> {
            BoardMain bd = new BoardMain("src/ressources/level/level1.txt", right, left);
            bd.setApp(this);
            panelBoard.add(bd, "boardlevel1");
            cardLayout.show(panelBoard, "boardlevel1");
            setParams(bd);
        });

        levelMenu.getLevel2().addActionListener((event) -> {
            BoardMain bd = new BoardMain("src/ressources/level/level2.txt", right, left);
            bd.setApp(this);
            panelBoard.add(bd, "boardlevel2");
            cardLayout.show(panelBoard, "boardlevel2");

            setParams(bd);
        });

        levelMenu.getLevel3().addActionListener((event) -> {
            BoardMain bd = new BoardMain("src/ressources/level/level3.txt", right, left);
            bd.setApp(this);
            panelBoard.add(bd, "boardlevel3");
            cardLayout.show(panelBoard, "boardlevel3");
            setParams(bd);
        });

        pack();

        // dimensionFrame = this.getBounds().getSize();

        boardEdit.setApp(this);
        levelMenu.setDim(dim);
        intro.setDim(dim);

        setParams(boardIA);
        // -----------------------------------------------------------------------------------------------
        // setVisible(true);
        // setExtendedState(JFrame.MAXIMIZED_BOTH);
        // -----------------------------------------------------------------------------------------------

        // boardIA.setWidthScreen(dimensionFrame.getWidth());
        // boardIA.setHeightScreen(dimensionFrame.getHeight());

        setParams(boardMain);
        right.setWidth(width);
        right.setHeight(height);
        // if(cardLayout) est en mode boardMain
        right.initalisation();
        left.setWidth(width);
        left.setHeight(height);

        setTitle("Peggle Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.addComponentListener(new ResizeListener());
    }

    public void setParams(BoardMain boardMain) {
        // dimensionFrame = this.getBounds().getSize();
        // boardMain.setWidthScreen(dimensionFrame.getWidth());
        // boardMain.setHeightScreen(dimensionFrame.getHeight());

        boardMain.setWidthScreen(dim.getWidth());
        boardMain.setHeightScreen(dim.getHeight());
    }
    // public static void main(String[] args) {
    // EventQueue.invokeLater(() -> {
    // App app = new App();
    // });
    // }

    public JPanel getPanelBoard() {
        return panelBoard;
    }

    public LevelMenu getLevelMenu() {
        return levelMenu;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    // public static void main(String[] args) {
    // EventQueue.invokeLater(() -> {

    // // try {
    // // LoadingScreen loading = new LoadingScreen();
    // // } catch (FontFormatException | IOException e ) {
    // // e.printStackTrace();
    // // }
    // App app = new App();

    // app.setVisible(true);
    // });
    // }

    private class ResizeListener implements ComponentListener {
        public void componentHidden(ComponentEvent e) {
        }

        public void componentMoved(ComponentEvent e) {
        }

        public void componentShown(ComponentEvent e) {
        }

        public void componentResized(ComponentEvent e) {
            dimensionFrame = getBounds().getSize();
            // if (width != dimensionFrame.getWidth())
            // boardMain.setWidthScreen(dimensionFrame.getWidth());
            // if (height != dimensionFrame.getHeight())
            // dimensionFrame = e.getComponent().getBounds().getSize();
            // boardMain.setHeightScreen(dimensionFrame.getHeight());
        }
    }
}