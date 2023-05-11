import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class App extends JPanel {

    private Dimension dimensionFrame;

    private BoardRight right;
    private BoardLeft left;

    // private JPanel right = new JPanel() ;

    private JPanel panelBoard;
    private BoardMain boardMain;
    private BoardEdit boardEdit;
    private BoardIA boardIA;

    private double width;
    private double height;
    private boolean isEditing = false;
    private CardLayout cardLayout;
    public Dimension dim;
    public String path;
    public boolean multiPlayer;
    private MenuLevel menuLevel;
    private boolean isValidate;

    public App(Dimension dim, String path, boolean multiPlayer, CardLayout cdLMenu, JPanel panelMenu,
            MenuLevel menuLevel, CardLayout cardMain) {
        this.dim = dim;
        this.path = path;
        this.multiPlayer = multiPlayer;
        right = new BoardRight(dim.getWidth(), dim.getHeight(), cdLMenu, panelMenu, menuLevel);
        left = new BoardLeft(dim.getWidth(), dim.getHeight());
        boardMain = new BoardMain(path, right, left, false, cdLMenu, panelMenu, menuLevel, this);
        boardIA = new BoardIA(path, right, left, cdLMenu, panelMenu, menuLevel, dim, this);
        initUI();
    }

    public App(Dimension dim, CardLayout cdLMenu, JPanel panelMenu, MenuLevel menuLevel, Main main) {
        this.menuLevel = menuLevel;
        this.dim = dim;
        try {
            boardEdit = new BoardEdit(dim);
        } catch (Exception e) {
            e.printStackTrace();
        }
        initUIBoardEdit();

        JButton buttonRetour = new JButton("Back To Menu");
        buttonRetour.setBounds(10, 10, 200, 40);

        JButton buttonPlay = new JButton("Play");

        isValidate = false;
        buttonPlay.setBounds(10, 70, 100, 40);

        if (!isValidate) {
            buttonPlay.setEnabled(false);
        }

        
        boardEdit.valid_edit.addActionListener((event) -> {
            boardEdit.WriteLevelText();
            buttonPlay.setEnabled(true);
            isValidate = true;
        });

        buttonPlay.addActionListener((event) -> {
            try {
                File file = new File("src/ressources/level/ediit1.txt");
                if (file.exists()) {
                    App app = new App(dim, "ressources/level/ediit1.txt", false, cdLMenu, panelMenu, menuLevel,
                            cdLMenu);

                    panelMenu.add(app, "appEdit");
                    cdLMenu.show(panelMenu, "appEdit");
                    // app = null ;
                    file.delete();
                    // main.moveCdLToEditor();

                    // panelMenu.remove(app);
                } else {
                    System.out.println("file doesn't exist");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        buttonRetour.addActionListener((event) -> {
            cdLMenu.show(panelMenu, "menup");
        });

        this.add(buttonRetour);
        this.add(buttonPlay);
        boardEdit.setApp(this);
    }

    public void initUIBoardEdit() {
        cardLayout = new CardLayout();
        panelBoard = new JPanel();
        panelBoard.setLayout(cardLayout);
        panelBoard.add(boardEdit, "boardEdit");

        width = (6. / 8.) * dim.getWidth();
        Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        height = winSize.height;
        double xStart = (1. / 8.) * dim.getWidth();

        setLayout(null);
        this.add(panelBoard);
        panelBoard.setBounds((int) xStart, 0, (int) width, (int) height);
        // this.addComponentListener(new ResizeListener());
    }

    private void initUI() {
        cardLayout = new CardLayout();
        panelBoard = new JPanel();
        panelBoard.setLayout(cardLayout);
        panelBoard.add(boardMain, "boardMain");
        panelBoard.add(boardIA, "boardIA");

        width = (6. / 8.) * dim.getWidth();
        height = dim.getHeight();
        double xStart = (1. / 8.) * dim.getWidth();
        setLayout(null);

        this.add(panelBoard);
        this.add(right);
        this.add(left);

        if (multiPlayer) {
            cardLayout.show(panelBoard, "boardIA");
        } else {
            cardLayout.show(panelBoard, "boardMain");
        }

        panelBoard.setBounds((int) xStart, 0, (int) width, (int) height);
        right.setBounds((int) (xStart + width), 0, (int) xStart, (int) height);
        left.setBounds(0, 0, (int) xStart, (int) height);

        if (!multiPlayer) {
            setParams(boardMain);
        } else {
            setParams(boardIA);
        }

        right.setWidth(dim.getWidth());
        right.setHeight(dim.getHeight());
        right.initalisation();

        left.setWidth(dim.getWidth());
        left.setHeight(dim.getHeight());

        this.addComponentListener(new ResizeListener());
    }

    public void setParams(BoardMain boardMain) {
        boardMain.setWidthScreen(dim.getWidth());
        boardMain.setHeightScreen(dim.getHeight());
    }

    public JPanel getPanelBoard() {
        return panelBoard;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

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