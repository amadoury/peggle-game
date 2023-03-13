import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class LabelPeg extends JLabel implements MouseInputListener {
    private PopUp popUp;
    private boolean isEditing = false;
    private int xClick;
    private int yClick;
    private App app;
    private BoardEdit boardEdit;
    private Peg peg;

    public LabelPeg(ImageIcon imgPeg) {
        super(imgPeg);
        List<String> listPath = new ArrayList<String>();
        listPath.add("ressources/delete.png");
        popUp = new PopUp(listPath);

        addMouseListener(this);
        addMouseMotionListener(this);

        for (int i = 0; i < listPath.size(); i++) {
            if (listPath.get(i).endsWith("delete.png")) {
                popUp.getListMenuItem().get(i).addActionListener((event) -> {
                    boardEdit.remove(this);
                    boardEdit.validate();
                    boardEdit.repaint();
                    // this.setIcon(null);
                });
            }
        }
    }

    public void setEditing(boolean isEditing) {
        this.isEditing = isEditing;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public void setBoardEdit(BoardEdit boardEdit) {
        this.boardEdit = boardEdit;
    }

    public void setPeg(Peg peg) {
        this.peg = peg;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (isEditing) {
            if (e.getButton() == MouseEvent.BUTTON3) {
                popUp.show(this, e.getX(), e.getY());
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (isEditing) {
            xClick = e.getX();
            yClick = e.getY();
        }
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

    @Override
    public void mouseDragged(MouseEvent e) {
        if (isEditing) {
            /* action to Drag label */
            int x = e.getXOnScreen() - xClick - (int) (0.125 * Toolkit.getDefaultToolkit().getScreenSize().getWidth())
                    - app.getInsets().left;
            int y = e.getYOnScreen() - yClick - app.getInsets().top;
            this.setLocation(x, y);
            peg.setPegX(x);
            peg.setPegY(y);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}