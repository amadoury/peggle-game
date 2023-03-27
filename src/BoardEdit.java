
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BoardEdit extends Board {
    private PopUp popUp;
    private Editor editor;
    private int xMouse;
    private int yMouse;
    private App app;

    public BoardEdit() {
        editor = new Editor();

        List<String> listPath = new ArrayList<String>();
        listPath.add("ressources/peg-orange.png");
        listPath.add("ressources/peg-bleu.png");
        listPath.add("ressources/peg-bleu-rectangle.png");
       // listPath.add("ressources/peg-bleu-rectangle.png") ;

        popUp = new PopUp(listPath);

        add(popUp);

        for (int i = 0; i < popUp.getListMenuItem().size(); i++) {
            if (listPath.get(i).endsWith("orange.png")) {
                popUp.getListMenuItem().get(i).addActionListener((event) -> {
                    /* pegCreation creation */
                    PegCercle pegCercle = new PegCercle(xMouse, yMouse, (int) (25 * (resolutionScreen / 100)),
                            "orange");

                    /* add peg to editor */
                    editor.addPeg(pegCercle);

                    /* add to boardEdit */
                    add(pegCercle.getLabelPeg());

                    /* set : isEditing, App, BoardEdit */
                    pegCercle.getLabelPeg().setEditing(true);
                    pegCercle.getLabelPeg().setApp(app);
                    pegCercle.getLabelPeg().setBoardEdit(this);
                    pegCercle.getLabelPeg().setPeg(pegCercle);
                    repaint();
                });
            } else if (listPath.get(i).endsWith("bleu.png")) {
                popUp.getListMenuItem().get(i).addActionListener((event) -> {
                    PegCercle pegCercle = new PegCercle(xMouse, yMouse, (int) (25 * (resolutionScreen / 100)), "bleu");

                    /* add peg to editor */
                    editor.addPeg(pegCercle);

                    /* add to boardEdit */
                    add(pegCercle.getLabelPeg());

                    /* set : isEditing, App, BoardEdit, Peg */
                    pegCercle.getLabelPeg().setEditing(true);
                    pegCercle.getLabelPeg().setApp(app);
                    pegCercle.getLabelPeg().setBoardEdit(this);
                    pegCercle.getLabelPeg().setPeg(pegCercle);
                    repaint();
                });
            } else if (listPath.get(i).endsWith("bleu-rectangle.png")) {
                popUp.getListMenuItem().get(i).addActionListener((event) -> {
                    PegRectangle pegRectangle = new PegRectangle(xMouse, yMouse, (int) (50 * (resolutionScreen / 100)),
                            (int) (25 * (resolutionScreen / 100)), "bleu");

                    /* add peg to editor */
                    editor.addPeg(pegRectangle);

                    /* add to boardEdit */
                    add(pegRectangle.getLabelPeg());

                    /* set : isEditing, App, BoardEdit, Peg */
                    pegRectangle.getLabelPeg().setEditing(true);
                    pegRectangle.getLabelPeg().setApp(app);
                    pegRectangle.getLabelPeg().setBoardEdit(this);
                    pegRectangle.getLabelPeg().setPeg(pegRectangle);
                    repaint();
                });
            }
        }
    }

    public void paintComponent(Graphics g){
        g2d = (Graphics2D) g;

        g2d.drawImage(imageBoard, 0, 0, (int) width, (int) height, null);
        if(editor.getListPeg().size() >= 1){
            try {
                FileWriter writer = new FileWriter("level4.txt");
                for(Peg e : editor.getListPeg()) {
                    String type;
                    if(e instanceof PegCercle) type = "PegCercle";
                    else type = "PegRectangle";
                    writer.write(e.pegX + "/" + e.pegY+"/" + e.getColor() + "/" +type + "\n");
                }  
                writer.close();
            } catch(IOException e){
                System.out.println("Une erreur est survenue lors de la création du fichier.");
                e.printStackTrace();
            } 
            //afficher("level1.txt");
        } 
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            popUp.show(this, e.getX(), e.getY());
            xMouse = e.getX();
            yMouse = e.getY();
        }
    }

    public void setApp(App app) {
        this.app = app;
    }

    public Editor getEditor() {
        return editor;
    }
}