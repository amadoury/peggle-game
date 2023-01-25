import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class App extends JFrame {

    private Dimension dimensionFrame;

    private JPanel left = new JPanel();
    private JPanel right = new JPanel();
    private JPanel top = new JPanel();
    private JPanel bottom = new JPanel();

    public App() {
        initUI();
    }

    private void initUI() {
        // this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        // c.fill = GridBagConstraints.HORIZONTAL;
        // c.weightx = 0.5;
        // c.weighty = 0.5;
        // c.gridx = 0;
        // c.gridy = 0;
        // // c.gridwidth = 1;
        // // c.gridheight = 1;
        // // c.ipadx = 1;
        // // c.ipady = 1;
        // add(left, c);
        // c.fill = GridBagConstraints.HORIZONTAL;
        // c.gridx = 2;
        // c.gridy = 0;
        // add(right, c);
        // // add(top, BorderLayout.NORTH);
        // // add(bottom, BorderLayout.SOUTH);
        // c.fill = GridBagConstraints.HORIZONTAL;
        // c.gridx = 1;
        // c.gridy = 0;
        // add(new Board(dimensionFrame), c);
        JButton button = new JButton("Button 1");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        add(left, c);

        button = new JButton("Button 2");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 3;

        // System.out.println(dimensionFrame.getHeight());
        // System.out.println((int) dimensionFrame.getHeight());
        c.gridx = 1;
        c.gridy = 0;
        add(new Board(), c);

        button = new JButton("Button 3");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 0;
        add(right, c);

        System.out.println(dimensionFrame);

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