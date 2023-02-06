import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class App extends JFrame {

    private Dimension dimensionFrame;

    private JPanel left = new JPanel();
    private JPanel right = new JPanel();

    public App() {
        initUI();
    }

    private void initUI() {
        // this.setExtendedState(JFrame.MAXIMIZED_BOTH);
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
        Board board = new Board();
        add(board, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 0;
        add(right, c);

        pack();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        board.setDimensionBoard(board.getSize());

        dimensionFrame = this.getBounds().getSize();

        board.setWidthScreen(dimensionFrame.getWidth());

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