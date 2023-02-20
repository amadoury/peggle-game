import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.util.Random;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Color;



public class LoadingScreen extends JFrame {
    public JProgressBar progressBar = new JProgressBar(0,100);
    private boolean end = false;
    private ImagePanel PanelImage = new ImagePanel(new ImageIcon("src/ressources/loading.png").getImage());
    private JPanel ProgressBarPanel = new JPanel();
    


    public LoadingScreen() throws FontFormatException, IOException {
        super();
        this.setSize(1400,1000);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        

        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        ProgressBarPanel.setLayout(null);
        progressBar.setBounds(385, 800, 650, 73);
        //progressBar.setBackground(new Color(0,0,204));
        progressBar.setForeground(new Color(255,102,0));
        progressBar.setBackground(Color.WHITE);


        // FONT STYLE
        String path_font = "ressources/font_style/font.ttf";
        InputStream is = MenuPrincipal.class.getResourceAsStream(path_font);
        Font font = Font.createFont(Font.TRUETYPE_FONT,is).deriveFont(65f);
        //(new Color(0,0,204));
        progressBar.setFont(font);
        

        ProgressBarPanel.add(progressBar);
        this.getContentPane().add(PanelImage);
        this.getContentPane().add(ProgressBarPanel);
        this.setVisible(true);
        System.out.println(end);
        this.loop();
        System.out.println(end);

        /*
        if(end == true){
            dispose();
            System.exit(0);
        } 
        */  
    }

    public void loop(){
        Random random = new Random();
        int position = 0;
        while(progressBar.getValue() < 115){
            progressBar.setValue(position);
            try{
                Thread.sleep(400);
            } catch (InterruptedException e){}
            position += (random.nextInt(9)+1);
        }
        this.end = true;
    }

    /*
    public static void main(String[] args) throws FontFormatException, IOException{
        LoadingScreen l = new LoadingScreen();
    }
    */

}
