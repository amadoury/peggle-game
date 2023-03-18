import javax.swing.* ;
import java.awt.* ;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List ;

public class BoardEdit extends Board {
    private PopUp popUp ;
    private Editor editor ;
    private int xMouse ; 
    private int yMouse ;
    private App app ;

    public BoardEdit() {
        editor = new Editor() ;

        List<String> listPath = new ArrayList<String>() ;
        listPath.add("ressources/peg-orange.png");
        listPath.add("ressources/peg-bleu.png");

        popUp = new PopUp(listPath) ;

        add(popUp) ;

        for(int i = 0; i < popUp.getListMenuItem().size(); i++){
            if(listPath.get(i).endsWith("orange.png")){
                popUp.getListMenuItem().get(i).addActionListener((event) -> {
                    /* pegCreation creation */
                    PegCercle pegCercle = new PegCercle(xMouse, yMouse, 25, "orange") ;

                    /* add peg to editor */ 
                    editor.addPeg(pegCercle);

                    /* add to boardEdit */                   
                    add(pegCercle.getJlabel()) ;

                    /* set : isEditing, App, BoardEdit */
                    pegCercle.getJlabel().setEditing(true);
                    pegCercle.getJlabel().setApp(app); 
                    pegCercle.getJlabel().setBoardEdit(this);
                    pegCercle.getJlabel().setPeg(pegCercle) ;
                    repaint();                   
                });
            }
            else{
                popUp.getListMenuItem().get(i).addActionListener((event) -> {
                    PegCercle pegCercle = new PegCercle(xMouse, yMouse, 25, "bleu") ;

                    /* add peg to editor */ 
                    editor.addPeg(pegCercle);

                    /* add to boardEdit */
                    add(pegCercle.getJlabel()) ;

                    /* set : isEditing, App, BoardEdit, Peg */
                    pegCercle.getJlabel().setEditing(true);
                    pegCercle.getJlabel().setApp(app);
                    pegCercle.getJlabel().setBoardEdit(this);
                    pegCercle.getJlabel().setPeg(pegCercle) ;
                    repaint();
                });
            }
        }
    }

    public void paintComponent(Graphics g){
        g2d = (Graphics2D) g;

        g2d.drawImage(imageBoard, 0, 0, (int) width, (int) height, null);
        if(editor.getListPeg().size() >= 1){
            //System.out.println("peg in editor : x = " + editor.getListPeg().get(0).getPegX() + "y = " + editor.getListPeg().get(0).getPegY());
            
            try {
                FileWriter writer = new FileWriter("level4.txt");
                for(Peg e : editor.getListPeg()) {
                    writer.write(e.pegX + "/" + e.pegY+"|" +e.getColor()+"\n");
                }  
                writer.close();
            } catch(IOException e){
                System.out.println("Une erreur est survenue lors de la création du fichier.");
                e.printStackTrace();
            } 

            //afficher("level1.txt");
        } 
    }


    // public void setWidthScreen(double w) {
    //     double var = w - (2.0 / 8.0) * w;
    //     width = var;
    // }

    // public void setHeightScreen(double w) {
    //     height = w;
    // }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            popUp.show(this, e.getX() , e.getY());
            xMouse = e.getX() ;
            yMouse = e.getY() ;
        }
    }

    public void setApp(App app){
        this.app = app ;
    }
    
    // Fonction permettant de lire les coordonnées des pegs.
    public static void afficher(String cheminFichier) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(cheminFichier));
            String ligne;
            while ((ligne = br.readLine()) != null) {
                Pattern pattern = Pattern.compile("^(\\d+)/([\\d]+)\\|(.+)$");
                Matcher matcher = pattern.matcher(ligne);
                if (matcher.find()) {
                    int x = Integer.parseInt(matcher.group(1));
                    int y = Integer.parseInt(matcher.group(2));
                    String c = matcher.group(3);
                    //System.out.println("a: " + a + ", b: " + b + ", c: " + c);

                }
                /*
                 *  Le premier groupe ^(\\d+) extrait la première série de chiffres avant le caractère / et le capture dans un groupe.
                    Le deuxième groupe ([\\d]+)\\| extrait la deuxième série de chiffres après le caractère / et avant le caractère |, et le capture dans un groupe.
                    Le troisième groupe (.+)$ extrait le reste de la ligne après le caractère | et le capture dans un groupe.
                 */

            }
            br.close();
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier " + cheminFichier + " : " + e.getMessage());
        }
    }

}
