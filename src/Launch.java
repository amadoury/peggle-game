import javax.swing.* ;
import java.awt.* ;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener ;

public class Launch extends JFrame{
    private JPanel loading ;
    private JPanel app ;
    public Launch(){

        CardLayout cardLayout = new CardLayout() ;
        //loading = new 
        
        setTitle("App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.addComponentListener(new ResizeListener());
    }

    // public static void main(String[] args) {
    //     EventQueue.invokeLater(() -> {
    //         App app = new App();
    //         app.setVisible(true);
    //     });
    // }

    private class ResizeListener implements ComponentListener {
        public void componentHidden(ComponentEvent e) {
        }

        public void componentMoved(ComponentEvent e) {
        }

        public void componentShown(ComponentEvent e) {
        }

        public void componentResized(ComponentEvent e) {
            // dimensionFrame = e.getComponent().getBounds().getSize();
            // boardMain.setWidthScreen(dimensionFrame.getWidth());
            // boardMain.setHeightScreen(dimensionFrame.getHeight());
        }
    }
}
