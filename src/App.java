import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class App extends JFrame {

    private Dimension dimensionFrame;

    private JPanel left = new JPanel();
    private JPanel right = new JPanel();
    private BoardMain boardMain;
    private boolean isEditing = false ;

    public App() {
        initUI();
    }

    private void initUI() {
        boardMain = new BoardMain();
        BoardEdit boardEdit = new BoardEdit() ;

        CardLayout cardLayout = new CardLayout() ;
        JPanel panelBoard = new JPanel() ;
        panelBoard.setLayout(cardLayout) ;
        panelBoard.add(boardMain, "boardMain") ;
        panelBoard.add(boardEdit, "boardEdit") ;

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
    
        cardLayout.show(panelBoard, "boardMain");

        //add(boardEdit , c); 
        add(panelBoard , c); 

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 0;
        add(right, c);

        pack();

        // Dimension dim = Toolkit.getDefaultToolkit().getScreenSize() ;

        boardEdit.setApp(this) ;

        boardMain.setDimensionBoard(boardMain.getSize());

        dimensionFrame = this.getBounds().getSize();

        boardMain.setWidthScreen(dimensionFrame.getWidth());
        boardMain.setHeightScreen(dimensionFrame.getHeight());


        // boardEdit.setWidthScreen(dimensionFrame.getWidth());
        // boardEdit.setHeightScreen(dimensionFrame.getHeight());

        setTitle("App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.addComponentListener(new ResizeListener());

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