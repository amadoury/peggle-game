import javax.swing.* ;
import javax.swing.border.EmptyBorder;

import java.awt.* ;
import java.util.ArrayList;

public class Tutorial extends JPanel {
    public ArrayList<Page> listPage = new ArrayList<Page>() ;
    public int width, height ;

    public Tutorial(Dimension dim, CardLayout cdlMenu, JPanel mainPanel){
        this.setPreferredSize(dim);

        /* for the first page */
        ArrayList<String> textPage1  = new ArrayList<String>();

        textPage1.add("Welcome to Peggle Game") ;
        String text1 = "<html><h2> L'objectif du jeu est : </h2> <ul><li>Détruire tous les Pegs Orange.</li><li>Vous avez 10 balles pour les détruire, si vous les épuisez et qu'il reste encore des pegs orange alors vous avez perdu</li> </ul></html>"; 
        textPage1.add(text1);
        listPage.add(new Page(textPage1, "ressources/img-intro-1.png", false));
    
        //this.add(listPage.get(0)) ;

        /* for the second page */
        ArrayList<String> textPage2 = new ArrayList<String>() ;
        textPage2.add("Welcome to Peggle Game") ;
        textPage2.add("Si la balle tombe dans le trou, vous obtenez une balle de plus");

        listPage.add(new Page(textPage2, "ressources/img-intro-2.png", false));
        //this.add(listPage.get(1));

        /* for page end */
        ArrayList<String> textPageEnd = new ArrayList<String>() ;
        textPageEnd.add("Let's GO !!!") ;
        textPageEnd.add("Vous savez maintenant comment jouer à Peggle ! Appuyez ci-dessus pour commencer ");
        listPage.add(new Page(textPageEnd, "ressources/img-start.png", true)) ;

        /* for cardLayout */
        CardLayout cardLayout = new CardLayout() ;
        JPanel panelPage = new JPanel() ;
        panelPage.setLayout(cardLayout) ;
        panelPage.add(listPage.get(0), "page1") ;
        panelPage.add(listPage.get(1), "page2");
        panelPage.add(listPage.get(2), "pageEnd");
        this.add(panelPage) ;

        //listPage.get(0).backward.setEnabled(false);

        listPage.get(0).forward.addActionListener((event) -> {
            cardLayout.show(panelPage, "page2");
        });


        listPage.get(0).backward.addActionListener((event) -> {
            cdlMenu.show(mainPanel, "menup") ;
        });

        listPage.get(1).backward.addActionListener((event) -> {
            cardLayout.show(panelPage, "page1");
        });

        listPage.get(1).forward.addActionListener((event) -> {
            cardLayout.show(panelPage, "pageEnd");
        });

        listPage.get(2).backward.addActionListener((event) -> {
            cardLayout.show(panelPage, "page2");
        });
    }

    // public void paintComponent(Graphics g){
    //     super.paintComponent(g);
    //     this.setPreferredSize(new Dimension(width , height));
    // }

    public void setDim(Dimension dim){
        this.width = (int)dim.getWidth();
        this.height = (int)dim.getHeight() ;
    }
    
    public class Page extends JPanel{
        private JButton forward = new JButton("Avancer");
        private JButton backward = new JButton("Retour");
        private JLabel labelText = new JLabel() ;
        private JLabel labelInf = new JLabel() ;
        private JLabel labelImg = new JLabel() ;
        private JPanel bottom = new JPanel() ;
        private JPanel header = new JPanel() ;
        private boolean isEnd ;
        public JButton launch = new JButton();

        public Page(ArrayList<String> text, String path, boolean isEnd){
            this.isEnd = isEnd ;
            this.setLayout(new BorderLayout()) ;
            header.setLayout(new BorderLayout()) ;
            bottom.setLayout(new BorderLayout()) ;
           // this.setSize(width, height);      

            header.setBorder(new EmptyBorder(new Insets(0, 0, 30, 0)));

            labelText.setText(text.get(0));
            labelText.setHorizontalAlignment(SwingConstants.CENTER);
            labelText.setFont(new Font("MONOSPACED", Font.BOLD, 50));
            labelText.setBorder(new EmptyBorder(new Insets(0, 0, 20, 0)));

            labelInf.setText(text.get(1));
            labelInf.setFont(new Font("serif", Font.BOLD, 17));
            
            ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(path));
            labelImg = new JLabel(imageIcon);
            labelImg.setBorder(new EmptyBorder(new Insets(0, 0, 20, 0)));

            header.add(labelText, BorderLayout.NORTH) ;
            header.add(labelInf, BorderLayout.CENTER) ;

            forward.setBackground(Color.LIGHT_GRAY);
            backward.setBackground(Color.LIGHT_GRAY);
            ImageIcon imgforward = new ImageIcon(this.getClass().getResource("ressources/fleche-droite1.png"));
            Image imagef = imgforward.getImage();
            Image newimgf = imagef.getScaledInstance(25, 15 ,java.awt.Image.SCALE_SMOOTH);

            imgforward = new ImageIcon(newimgf);

            forward.setBackground(Color.LIGHT_GRAY);
            backward.setBackground(Color.LIGHT_GRAY);

           
            JPanel panelEnd = new JPanel() ;
            //panelEnd.setLayout(new BorderLayout()) ;
            if (isEnd){
                launch.setIcon(imageIcon);
                //launch.setAlignmentY(CENTER_ALIGNMENT);
                //launch.setPreferredSize(new Dimension(100, 100));
                panelEnd.add(launch);
            }

            ImageIcon imgbackward = new ImageIcon(this.getClass().getResource("ressources/fleche-gauche1.png"));
            Image imageb = imgbackward.getImage();
            Image newimgb = imageb.getScaledInstance(25, 15 ,java.awt.Image.SCALE_SMOOTH);
           
            imgbackward = new ImageIcon(newimgb);
           
            forward.setIcon(imgforward);
            backward.setIcon(imgbackward);

            bottom.add(forward, BorderLayout.EAST);
            bottom.add(backward, BorderLayout.WEST);

            this.add(header, BorderLayout.NORTH);

            if (!isEnd){
                this.add(labelImg, BorderLayout.CENTER) ;
            }
            else{
                this.add(panelEnd, BorderLayout.CENTER);
            }

            this.add(bottom, BorderLayout.SOUTH) ;     
        }
    }
}