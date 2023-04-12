import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class App extends JFrame {

    private Dimension dimensionFrame;

    private JPanel left = new JPanel();
    private BoardRight right = new BoardRight(10);
    private BoardMain boardMain;

    private double width;
    private double height;
    private boolean isEdit = false;

    public App() {
        initUI();
    }

    private void initUI() {

        boardMain = new BoardMain(right);
        BoardEdit boardEdit = new BoardEdit();

        CardLayout cardLayout = new CardLayout();
        JPanel panelBoard = new JPanel();
        panelBoard.setLayout(cardLayout);
        panelBoard.add(boardMain, "boardMain");
        panelBoard.add(boardEdit, "boardEdit");

        setLayout(new GridBagLayout());

        // this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // setLayout(new GridBagLayout());

        // setSize(Toolkit.getDefaultToolkit().getScreenSize());

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

        cardLayout.show(panelBoard, "boardMain");

        // board = new BoardMain();
        add(panelBoard, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 0;
        add(right, c);

        pack();

        // Dimension dim = Toolkit.getDefaultToolkit().getScreenSize() ;

        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        boardEdit.setApp(this);

        boardMain.setDimensionBoard(boardMain.getSize());

        dimensionFrame = this.getBounds().getSize();

        width = dimensionFrame.getWidth();
        height = dimensionFrame.getHeight();

        boardMain.setWidthScreen(width);
        boardMain.setHeightScreen(height);
        right.setWidth(width);
        right.setHeight(height);
        // if(cardLayout) est en mode boardMain
        right.ballsInitalisation();

        setTitle("Peggle Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.addComponentListener(new ResizeListener());
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            App app = new App();

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
            dimensionFrame = getBounds().getSize();
            if (width != dimensionFrame.getWidth())
                boardMain.setWidthScreen(dimensionFrame.getWidth());
            if (height != dimensionFrame.getHeight())
                dimensionFrame = e.getComponent().getBounds().getSize();
            boardMain.setHeightScreen(dimensionFrame.getHeight());
        }
    }
}