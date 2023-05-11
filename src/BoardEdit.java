import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;
import java.awt.event.*;

public class BoardEdit extends Board {
    private PopUp popUp;
    private Editor editor;
    private int xMouse;
    private int yMouse;
    // private JButton valid_edit = new JButton("VALIDER");
    // private JButton retourMenu = new JButton("Back To Menu");
    public boolean isValidate = false;
    public JButton valid_edit = createButton("TO VALIDATE");
    private JButton retourMenu = createButton("BACK TO MENU");
    private App app;
    private int rayon = 20;
    private PegRectangle rectangleMoving;
    private double rectangleMovingAngle;
    public CardLayout cardMain;

    public BoardEdit(Dimension dim) throws FontFormatException, IOException {
        super(1);
        width = (6. / 8.) * dim.getWidth();
        height = dim.getHeight();
        editor = new Editor();

        List<String> listPath = new ArrayList<String>();
        listPath.add("ressources/peg-orange.png");
        listPath.add("ressources/peg-bleu.png");
        listPath.add("ressources/peg-bleu-rectangle.png");
        listPath.add("ressources/peg-soleil.png");
        listPath.add("ressources/peg-rebond.png");

        valid_edit.setBounds((int) (width * 0.40), 0, 325, 50);
        add(valid_edit);

        popUp = new PopUp(listPath);

        add(popUp);

        addMouseListener(this);
        addMouseMotionListener(this);

        for (int i = 0; i < popUp.getListMenuItem().size(); i++) {
            if (listPath.get(i).endsWith("orange.png")) {
                popUp.getListMenuItem().get(i).addActionListener((event) -> {
                    /* pegCreation creation */
                    PegCercle pegCercle = new PegCercle(xMouse, yMouse, (int) (rayon / (resolutionScreen / 100)),
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
                    PegCercle pegCercle = new PegCercle(xMouse, yMouse, (int) (rayon / (resolutionScreen / 100)),
                            "bleu");

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
                    PegRectangle pegRectangle = new PegRectangle(xMouse, yMouse, (int) (60 /
                            (resolutionScreen / 100)),
                            (int) (30 * (resolutionScreen / 100)), 0, "bleu");

                    /* add peg to editor */
                    editor.addPeg(pegRectangle);

                    /* add to boardEdit */
                    rectangleMoving = pegRectangle;

                    add(pegRectangle.getLabelPeg());

                    /* set : isEditing, App, BoardEdit, Peg */
                    pegRectangle.getLabelPeg().setEditing(true);
                    pegRectangle.getLabelPeg().setApp(app);
                    pegRectangle.getLabelPeg().setBoardEdit(this);
                    pegRectangle.getLabelPeg().setPeg(pegRectangle);
                    repaint();
                });
            } else if (listPath.get(i).endsWith("soleil.png")) {
                popUp.getListMenuItem().get(i).addActionListener((event) -> {
                    PegSoleil pegSoleil = new PegSoleil(xMouse, yMouse, (int) (rayon * 3 / (resolutionScreen / 100)),
                            "soleil");
                    editor.addPeg(pegSoleil);
                    add(pegSoleil.getLabelPeg());

                    pegSoleil.getLabelPeg().setEditing(true);
                    pegSoleil.getLabelPeg().setApp(app);
                    pegSoleil.getLabelPeg().setBoardEdit(this);
                    pegSoleil.getLabelPeg().setPeg(pegSoleil);
                    repaint();
                });
            } else if (listPath.get(i).endsWith("rebond.png")) {
                popUp.getListMenuItem().get(i).addActionListener((event) -> {
                    PegRebond pegRebond = new PegRebond(xMouse, yMouse, (int) (rayon * 3 / (resolutionScreen / 100)),
                            "rebond");
                    editor.addPeg(pegRebond);
                    add(pegRebond.getLabelPeg());

                    pegRebond.getLabelPeg().setEditing(true);
                    pegRebond.getLabelPeg().setApp(app);
                    pegRebond.getLabelPeg().setBoardEdit(this);
                    pegRebond.getLabelPeg().setPeg(pegRebond);
                    repaint();
                });
            }
        }
    }

    public boolean isValidateEdit() {
        return isValidate;
    }

    public void paintComponent(Graphics g) {
        g2d = (Graphics2D) g;
        g2d.drawImage(imageBoard, 0, 0, (int) width, (int) height, null);
    }

    public void setApp(App app) {
        this.app = app;
    }

    public void WriteLevelText(){
        if(editor.getListPeg().size() >= 1){
            try {
                File file = new File("src/ressources/level/ediit1.txt") ;
                if (file.createNewFile()){
                    System.out.println("file create");
                    FileWriter writer = new FileWriter(file);
                    for(Peg e : editor.getListPeg()) {
                        if (e instanceof PegRebond){
                            writer.write((e.pegX/width) + "/" + (e.pegY/height)+"/" + e.getColor() + "/PegRebond\n");
                        }
                        else if (e instanceof PegSoleil){
                            writer.write((e.pegX/width) + "/" + (e.pegY/height)+"/" + e.getColor() + "/PegSoleil\n");
                        }
                        else if(e instanceof PegCercle){
                            writer.write((e.pegX/width) + "/" + (e.pegY/height)+"/" + e.getColor() + "/PegCercle\n");
                        } 
                        else if(e instanceof PegRectangle) {
                            writer.write((e.pegX/width) + "/" + (e.pegY/height)+"/" + e.getColor() + "/PegRectangle/" + ((PegRectangle)e).getAngle() +"\n");
                        }
                    }  
                    writer.close();
                }
            } catch(Exception e){
                System.out.println("Une erreur est survenue lors de la création du fichier.");
                e.printStackTrace();
            } 
        }
    }

    public Editor getEditor() {
        return editor;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);

        String path_font = "ressources/font_style/font.ttf";
        InputStream is;
        Font font;

        try {
            is = this.getClass().getResourceAsStream(path_font);
            font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(40f);
            button.setFont(font);
        } catch (Exception e) {
            e.printStackTrace();
        }
        button.setFocusPainted(false);

        // 178,255,102
        // 153,255,51
        Color colorPrinciaple = new Color(153, 255, 51);
        // Color colorPrinciaple = new Color(212,226,8);
        Color colorClicked = new Color(165, 176, 3);
        Color textColor = new Color(153, 0, 153);
        button.setBackground(colorPrinciaple);
        button.setForeground(textColor);

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(Color.WHITE);
                button.setForeground(Color.BLACK);
            }
        });

        button.addMouseListener(new MouseAdapter() {
            public void mouseExited(MouseEvent e) {
                button.setBackground(colorPrinciaple);
                button.setForeground(textColor);
            }
        });

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                button.setBackground(colorClicked);
            }
        });

        return button;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            popUp.show(this, e.getX(), e.getY());
            xMouse = e.getX();
            yMouse = e.getY();
            return;
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        rectangleMoving = null;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (rectangleMoving != null) {
            double angle = -Math.atan2(e.getX() - rectangleMoving.getPegX(), e.getY() - rectangleMoving.getPegY());
            angle += Math.PI / 2.0;
            angle = Math.toDegrees(angle);
            if (angle < 0) {
                angle += 360;
            }
            angle = Math.toRadians(angle);

            remove(rectangleMoving.getLabelPeg());
            rectangleMoving.rotationPegRectangle(angle);
            add(rectangleMoving.getLabelPeg());
            rectangleMoving.getLabelPeg().setEditing(true);
            rectangleMoving.getLabelPeg().setApp(app);
            rectangleMoving.getLabelPeg().setBoardEdit(this);
            rectangleMoving.getLabelPeg().setPeg(rectangleMoving);
            repaint();
        }
    }

}
