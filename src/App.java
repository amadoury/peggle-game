import java.awt.EventQueue;
import javax.swing.JFrame;

public class App extends JFrame {

    public App() {

        initUI();
    }

    private void initUI() {

        // add(new Board());

        setLayout(null);

         

        setSize(250, 200);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setTitle("Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            App ex = new App();
            ex.setVisible(true);
        });
    }
}