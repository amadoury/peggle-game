import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class App extends JPanel {

    private Dimension dimensionFrame;

    private BoardRight right;
    private BoardLeft left;

    // private JPanel right = new JPanel() ;

    private JPanel panelBoard;
    private BoardMain boardMain;
    private BoardEdit boardEdit;

    private double width;
    private double height;
    private LevelMenu levelMenu;
    private boolean isEditing = false;
    private CardLayout cardLayout;
    public Dimension dim;
    public String path;
    public boolean multiPlayer;

    public App(Dimension dim, String path, boolean multiPlayer) {
        this.dim = dim;
        this.path = path;
        this.multiPlayer = multiPlayer;
        right = new BoardRight(dim.getWidth(), dim.getHeight());
        left = new BoardLeft(dim.getWidth(), dim.getHeight());
        boardMain = new BoardMain(path, right, left, false);
        initUI();
    }

    public App(Dimension dim, CardLayout cdLMenu, JPanel panelMenu) {
        this.dim = dim;
        try {
            boardEdit = new BoardEdit(dim);
        } catch (Exception e) {
            e.printStackTrace();
        }
        initUIBoardEdit();

        JButton buttonRetour = new JButton("Back To Menu");
        buttonRetour.setBounds(10, 10, 200, 40);

        buttonRetour.addActionListener((event) -> {
            cdLMenu.show(panelMenu, "menup");
        });

        this.add(buttonRetour);

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
        // this.add(right);
        // this.add(left);
        panelBoard.setBounds((int) xStart, 0, (int) width, (int) height);
        // right.setBounds((int) (xStart + width), 0, (int) xStart, (int) height);
        // left.setBounds(0, 0, (int) xStart, (int) height);

        // this.addComponentListener(new ResizeListener());
    }

    private void initUI() {
        cardLayout = new CardLayout();
        panelBoard = new JPanel();
        panelBoard.setLayout(cardLayout);
        panelBoard.add(boardMain, "boardMain");

        width = (6. / 8.) * dim.getWidth();
        height = dim.getHeight();
        double xStart = (1. / 8.) * dim.getWidth();
        setLayout(null);
        this.add(panelBoard);
        this.add(right);
        this.add(left);

        panelBoard.setBounds((int) xStart, 0, (int) width, (int) height);
        right.setBounds((int) (xStart + width), 0, (int) xStart, (int) height);
        left.setBounds(0, 0, (int) xStart, (int) height);

        setParams(boardMain);
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

    public LevelMenu getLevelMenu() {
        return levelMenu;
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