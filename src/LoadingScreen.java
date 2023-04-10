import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.util.Random;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Color;
import java.awt.Dimension;


public class LoadingScreen extends JFrame {
    public JProgressBar progressBar = new JProgressBar(0,100);
    private boolean end = false;
    //private ImagePanel PanelImage = new ImagePanel(new ImageIcon("src/ressources/loading.png").getImage());
    private JPanel panelImage = new JPanel();
    private JPanel ProgressBarPanel = new JPanel();
    

    public LoadingScreen() throws FontFormatException, IOException {
        super();
        //this.setSize(1400,1000);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        //ImageIcon imgbcq = new ImageIcon("src/ressources/loading.png")
        this.getContentPane().setBackground(Color.BLACK);

        double width = this.getBounds().getSize().getWidth() ;
        double height = this.getBounds().getSize().getHeight() ;
                
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        

        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        ProgressBarPanel.setLayout(null);
        progressBar.setBounds(100, 100, 650, 73);
        //progressBar.setBounds(100 , (int)height / 2 , (int)width - 100, 73);

        progressBar.setForeground(new Color(255,102,0));
        progressBar.setBackground(Color.WHITE);
        progressBar.setMaximum(100);

        //FONT STYLE
        String path_font = "ressources/font_style/font.ttf";
        InputStream is = LoadingScreen.class.getResourceAsStream(path_font);
        Font font = Font.createFont(Font.TRUETYPE_FONT,is).deriveFont(65f);
        progressBar.setFont(font);        

        ProgressBarPanel.add(progressBar);
        //this.add(PanelImage);
        this.add(ProgressBarPanel);
        System.out.println(" 1 er" + getBounds().getSize());

        this.setVisible(true);

        System.out.println("dim avant de creer app "+ this.getBounds().getSize());

        App app = new App(new Dimension((int)((6. / 8.)*getBounds().getSize().getWidth()), (int)getBounds().getSize().getHeight())) ;

        this.loop();
        System.out.println(" last " + this.getBounds().getSize());

        app.setVisible(true);
        //dispose();
    }

    public void loop(){
        Random random = new Random();
        int position = 0;
        while(position <= 100){
            progressBar.setValue(position);
            try{
                Thread.sleep(100);
            } catch (InterruptedException e){}
            position += (random.nextInt(9)+1);
        }
        this.end = true;
    }
    
    public static void main(String[] args) throws FontFormatException, IOException{
        LoadingScreen l = new LoadingScreen();
    }    
}