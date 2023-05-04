import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.* ;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.io.InputStream;
import java.util.ArrayList;

public class MenuLevel extends JPanel{
    private Dimension dim ;
    private boolean isMultiplayer = false;
    private CardLayout cardLayout ;
    //private ArrayList<App> listAppLevels = new ArrayList<App>() ;

    public MenuLevel(Dimension dim){
        this.dim = dim ;
        this.setLayout(null);

        cardLayout = new CardLayout() ;

        this.setLayout(cardLayout);


        Page page = new Page("ressources/bcg-menu-level.jpg") ;

        this.add(page, "page");
    

        cardLayout.show(this, "page") ;

        page.setBounds(0,0, (int)dim.getWidth(), (int)dim.getHeight());

    }

    public class Page extends JPanel{
        private Button avancer = new Button("ressources/fleche-droite1.png", true);
        private Button retourner = new Button("ressources/fleche-gauche1.png", true);
        private String text = "Select Level" ;
        int xText ;
        int yText ;

        private Font font ;

        private Graphics2D g2d ;
        private Image bckImage ;
        ArrayList<Button> listButtonLevels = new ArrayList<Button>() ;

        public Page(String path){
            this.setLayout(null) ;

            this.add(avancer);
            this.add(retourner);

            try{
                bckImage = ImageIO.read(this.getClass().getResource(path)) ;
            }
            catch(Exception e){
                e.printStackTrace();
            }


            int xStart = (int)((2. / 8.) * dim.getWidth()) + 100;
            int yStart = (int)((1. / 5.) * dim.getHeight());

            for(int i = 1; i < 10; i++){
                //ressources/img-button-level-"+i+".png
                listButtonLevels.add(new Button("ressources/img-levels.png", "src/ressources/level/level2.txt", i)) ;
                this.add(listButtonLevels.get(i - 1));
            }
            
            double widthBis =(dim.getWidth() - 2 * xStart) ;
            int part = (int)(widthBis / 3.) ;


            for(int i = 0; i < 9; i += 3){
                listButtonLevels.get(i).setBounds(xStart, yStart, 175, 155) ;
                listButtonLevels.get(i+1).setBounds(xStart + part , yStart,175, 155);
                listButtonLevels.get(i+2).setBounds(xStart + 2 * part , yStart,175, 155);

                yStart += 200 ;
            }

            avancer.setBounds((int)(dim.getWidth() - (1. / 8.) *  dim.getWidth()),  (int)((1. / 5.) * dim.getHeight()) + 200, 200, 100);
            retourner.setBounds(20,  (int)((1. / 5.) * dim.getHeight()) + 200, 200, 100);


            String path_font = "ressources/font_style/Roboto-BlackItalic.ttf";
            InputStream is ;
      
            try{
              is = LevelMenu.class.getResourceAsStream(path_font);
              font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(65f);
            }
            catch(Exception e){
              e.printStackTrace();
            }
        }

        @Override
        public void paintComponent(Graphics g){
            g2d = (Graphics2D)g ;

            g2d.setColor(Color.WHITE);
            g2d.setFont(font);
            xText = getXforCenteredText("Select Level") ;
            yText = 75 ;

            g2d.drawImage(bckImage, 0, 0, (int)dim.getWidth(), (int)dim.getHeight() , null, null) ;
            g2d.drawString(text, xText, yText);
        }

        public int getXforCenteredText(String text) {
            Font font = g2d.getFont();
            Rectangle2D f = font.getStringBounds(text, g2d.getFontRenderContext());
            return (int) ((dim.getWidth() - f.getWidth()) / 2);
        } 
    }

    public class Button extends JLabel implements MouseListener{
        public App app ;
        private int nLevel ;

        public Button(String path, boolean resizeImg){
            ImageIcon imgIcon = new ImageIcon(this.getClass().getResource(path)) ;
            
            if (resizeImg){
                Image img = imgIcon.getImage() ;
                Image newImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                imgIcon.setImage(newImg);
            }
            this.setIcon(imgIcon);
            addMouseListener(this);
        }

        public Button(String pathImg, String pathLevel, int n){
            this(pathImg, false);
            this.nLevel = n ;
            app = new App(dim, pathLevel, isMultiplayer);
            MenuLevel.this.add(app, "app" + n);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            //App app1 = new App(dim,"ressources/level/level2.txt", false);
            cardLayout.show(MenuLevel.this, "app" + nLevel);
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
}