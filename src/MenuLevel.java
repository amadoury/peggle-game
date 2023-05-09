import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.io.InputStream;
import java.util.ArrayList;

public class MenuLevel extends JPanel {
    private Dimension dim;
    private boolean isMultiplayer = false;
    private CardLayout cardLayout ;
    private CardLayout cdLayoutMain ;
    private JPanel mainPanel ;
    private ArrayList<Page> listPage = new ArrayList<Page>() ;
    private ArrayList<Button> listButtonLevels = new ArrayList<Button>() ;
    private CardLayout cardLayLevels = new CardLayout() ;
    private JPanel panelLevels = new JPanel() ;


    public MenuLevel(Dimension dim, CardLayout cdLayout, JPanel mainPanel) {
        this.dim = dim;
        this.cdLayoutMain = cdLayout;
        this.mainPanel = mainPanel;
        this.setLayout(null);

        cardLayout = new CardLayout();

        this.setLayout(cardLayout);

        Page page1 = new Page("ressources/bcg-menu-level.jpg", 1, 1, 9) ;
        listPage.add(page1) ;

        Page page2 = new Page("ressources/bcg-menu-level.jpg", 2, 10, 12) ;
        listPage.add(page2);

        panelLevels.setLayout(cardLayLevels);

        this.add(listPage.get(0), "page1");
        this.add(listPage.get(1), "page2");
        //this.add(panelLevels,  "panelLevels");

        cardLayout.show(this, "page1") ;
        //cardLayout.first(this);

        listPage.get(0).setBounds(0,0, (int)dim.getWidth(), (int)dim.getHeight());
    }

    public void moveCdLToPage1(){
        cardLayout.show(this, "page1") ;
    }



    public class Page extends JPanel{
        private Button avancer ;
        private Button retourner ;
        private String text = "Select Level" ;
        private Button iaToggle ;
        int xText ;
        int yText ;

        private Font font;

        private Graphics2D g2d;
        private Image bckImage;

        private JLabel labelInfoIA = new JLabel("Turn On to Play with IA");

        public Page(String path, int n, int deb, int fin){
            iaToggle = new Button(n) ;

            avancer = new Button("ressources/fleche-droite1.png", true, "avancer", n);
            retourner = new Button("ressources/fleche-gauche1.png", true, "retour", n);

            this.setLayout(null);

            this.add(avancer);
            this.add(retourner);
            this.add(iaToggle);
            this.add(labelInfoIA);

            labelInfoIA.setBounds((int) dim.getWidth() - 320, 80, 300, 30);
            iaToggle.setBounds((int) dim.getWidth() - 300, 100, 100, 100);

            try {
                bckImage = ImageIO.read(this.getClass().getResource(path));
            } catch (Exception e) {
                e.printStackTrace();
            }

            int xStart = (int) ((2. / 8.) * dim.getWidth()) + 100;
            int yStart = (int) ((1. / 5.) * dim.getHeight());

 
            for(int i = deb; i <= fin ; i++){
                listButtonLevels.add(new Button("ressources/img-level-"+ i + ".png", "ressources/level/level"+i+".txt", i)) ;
                this.add(listButtonLevels.get(i - 1));
            }

            double widthBis = (dim.getWidth() - 2 * xStart);
            int part = (int) (widthBis / 3.);

            for(int i = deb - 1 ; i < fin ; i += 3){
                listButtonLevels.get(i).setBounds(xStart, yStart, 175, 155) ;
                listButtonLevels.get(i+1).setBounds(xStart + part , yStart,175, 155);
                listButtonLevels.get(i+2).setBounds(xStart + 2 * part , yStart,175, 155);

                yStart += 200;
            }

            avancer.setBounds((int) (dim.getWidth() - 120), (int) ((1. / 5.) * dim.getHeight()) + 200, 100, 100);
            retourner.setBounds(20, (int) ((1. / 5.) * dim.getHeight()) + 200, 200, 100);

            String path_font = "ressources/font_style/Roboto-BlackItalic.ttf";
            InputStream is;

            try {
                is = this.getClass().getResourceAsStream(path_font);
                font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(65f);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void paintComponent(Graphics g) {
            g2d = (Graphics2D) g;

            g2d.setColor(Color.WHITE);
            g2d.setFont(font);
            xText = getXforCenteredText("Select Level");
            yText = 75;

            g2d.drawImage(bckImage, 0, 0, (int) dim.getWidth(), (int) dim.getHeight(), null, null);
            g2d.drawString(text, xText, yText);
        }

        public int getXforCenteredText(String text) {
            Font font = g2d.getFont();
            Rectangle2D f = font.getStringBounds(text, g2d.getFontRenderContext());
            return (int) ((dim.getWidth() - f.getWidth()) / 2);
        }
    }

    public class Button extends JLabel implements MouseListener {
        public App app;
        private int nLevel;
        private ImageIcon imgIcon;
        private boolean iaButton;
        private boolean status = true;
        private String type;
        private int nbPage;
        private String pathLevel;
    
        public Button(int n){
            this.nbPage = n ;
            type = "ia";
            imgIcon = new ImageIcon(this.getClass().getResource("ressources/ia-off.png"));
            this.setIcon(imgIcon);
            iaButton = true;
            addMouseListener(this);
        }

        public Button(String path, boolean resizeImg, String type, int i) {
            imgIcon = new ImageIcon(this.getClass().getResource(path));
            this.type = type;
            this.nbPage = i;
            if (resizeImg) {
                Image img = imgIcon.getImage();
                Image newImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                imgIcon.setImage(newImg);
            }
            this.setIcon(imgIcon);
            addMouseListener(this);
            this.setToolTipText("Appuyer pour desactiver l'IA");

        }

        public Button(String pathImg, String pathLevel, int n) {
            this(pathImg, false, "bLevel", n);
            this.nLevel = n;
            this.pathLevel = pathLevel ;
            //app = new App(dim, pathLevel, isMultiplayer , cdLayoutMain, mainPanel, MenuLevel.this);
            //MenuLevel.this.add(app, "app" + n);
            //panelLevels.add(app, "app"+n);
        }

        public void changeImg(boolean status) {
            String str = status ? "on" : "off";
            String text = status ? "Turn Off To Play Without IA" : "Turn On To Play With IA";
            imgIcon = new ImageIcon(this.getClass().getResource("ressources/ia-" + str + ".png"));
            this.setIcon(imgIcon);
            listPage.get(nbPage - 1).labelInfoIA.setText(text) ;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (type.equals("bLevel")) {
                System.out.println("ismultiplayer dans menuLvele" + isMultiplayer);
                app = new App(dim, pathLevel, isMultiplayer , cdLayoutMain, mainPanel, MenuLevel.this);
                MenuLevel.this.add(app, "app" + nLevel);
                cardLayout.show(MenuLevel.this, "app" + nLevel);
            } else if (iaButton) {
                changeImg(status);
                isMultiplayer = status ;
                status = !status;
            } else if (type.equals("retour") && nbPage == 1) {
                cdLayoutMain.show(mainPanel, "menup");
            }
            else if (type.equals("avancer") && nbPage == 1){
                cardLayout.show(MenuLevel.this, "page2");
            }
            else if (type.equals("retour") && nbPage == 2){
                cardLayout.show(MenuLevel.this, "page1");
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (type.equals("bLevel")) {

            } else if (iaButton) {
                // JLabel info = new JLabel("Info");

                if (status) {
                    // this.setToolTipText("Appuyer pour desactiver l'IA");

                } else {

                }
            } else if (type.equals("retour") && nbPage == 1) {
                this.setToolTipText("Appuyer pour retourner au Menu Principal");
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
}