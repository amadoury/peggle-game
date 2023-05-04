import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JPanel;
import java.util.Random;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Color;
import java.awt.Dimension;


public class LoadingScreen extends JFrame {
    public JProgressBar progressBar = new JProgressBar(0,100);
    private JPanel panelImage = new JPanel();
    private JPanel ProgressBarPanel = new JPanel();

    public LoadingScreen() throws FontFormatException, IOException {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
                
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        ProgressBarPanel.setLayout(null);
        progressBar.setBounds(100, 100, 650, 73);

        progressBar.setForeground(new Color(255,102,0));
        progressBar.setBackground(Color.WHITE);
        progressBar.setMaximum(100);

        //FONT STYLE
        String path_font = "ressources/font_style/font.ttf";
        InputStream is = LoadingScreen.class.getResourceAsStream(path_font);
        Font font = Font.createFont(Font.TRUETYPE_FONT,is).deriveFont(65f);
        progressBar.setFont(font);        
        ProgressBarPanel.add(progressBar);
        this.add(ProgressBarPanel);

        this.setVisible(true);

        this.loop();

        //App app = new App(getBounds().getSize()) ;

        Main main = new Main(getBounds().getSize());
        main.setVisible(true);

        //app.setVisible(true);

        try{
            Thread.sleep(100);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        dispose();
    }

    public void loop(){
        int position = 0;
        while(position <= 100){
            progressBar.setValue(position);
            try{
                Thread.sleep(100);
            } catch (InterruptedException e){}
            position += 10 ;
        }
    }
    
    public static void main(String[] args) throws FontFormatException, IOException{
        LoadingScreen l = new LoadingScreen();
    }    
}