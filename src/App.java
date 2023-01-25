import javax.swing.* ;
import java.awt.* ;
import java.awt.event.* ;

public class App extends JFrame{

    private Dimension dimensionFrame ;
    private JLabel left = new JLabel() ;
    private JLabel right = new JLabel() ;



    public App(){
        initUI() ;
    }

    private void initUI(){
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH) ;
        dimensionFrame = this.getBounds().getSize();

        System.out.println(dimensionFrame) ;

        this.setLayout(new GridLayout(1, 3)) ;
        add(left) ;
        add(new Board()) ;
        add(right) ;
        pack();
        dimensionFrame = this.getBounds().getSize();
        System.out.println(dimensionFrame) ;

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