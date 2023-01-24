import javax.swing.* ;
import java.awt.* ;
import java.awt.event.* ;

public class App extends JFrame{

    private Dimension dimensionFrame ;

    public App(){
        initUI() ;
    }

    private void initUI(){
        this.setExtendedState(JFrame.MAXIMIZED_BOTH) ;

        dimensionFrame = this.getBounds().getSize();
        add(new Board(dimensionFrame)) ;
        pack();

        setTitle("App") ;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        this.addComponentListener(new ResizeListener()) ;
    }

    public static void main(String [] args){
        EventQueue.invokeLater(() -> {
            App app = new App() ;
            app.setVisible(true) ;
        });
    }

    private class ResizeListener implements ComponentListener{
        public void componentHidden(ComponentEvent e) {}
        public void componentMoved(ComponentEvent e) {}
        public void componentShown(ComponentEvent e){}
        public void componentResized(ComponentEvent e){
            dimensionFrame = e.getComponent().getBounds().getSize() ;
        }

    }
}