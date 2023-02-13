import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class App extends JFrame {

    private Dimension dimensionFrame;

    private JPanel left = new JPanel() ;
    private JPanel right = new JPanel();
    private JLabel labelBall = new JLabel() ;
    private Board board ;
    private boolean isEditing = false ;


    public App() {
        initUI();
    }

    private void initUI() {
        // this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        /* board creation */
        board = new Board() ;

        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        /* Label ball */

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        c.ipady = 1200 ;
        add(board.getBoardLeft() , c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 3;
        c.ipady = 1500;
        c.gridx = 1;
        c.gridy = 0;

        if (!isEditing) {
            add(board , c);
        }
        else{
            add(new BoardEdit(), c) ;
        }

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 0;
        add(right, c);

        pack();

        board.setDimensionBoard(board.getSize());

        dimensionFrame = this.getBounds().getSize();

        board.setWidthScreen(dimensionFrame.getWidth());
        board.setHeightScreen(dimensionFrame.getHeight());


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
        }
    }
}