import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PopUp extends JPopupMenu {
    private List<String> listPathElem = new ArrayList<String>();
    private List<JMenuItem> listMenuItem = new ArrayList<JMenuItem>();
    private List<ImageIcon> listImageIcon = new ArrayList<ImageIcon>();
    private List<Image> listImage = new ArrayList<Image>();

    public PopUp(List<String> list) {
        this.listPathElem = list;

        /* loading image et creation menuItem */
        for (int i = 0; i < listPathElem.size(); i++) {
            listImageIcon.add(new ImageIcon(this.getClass().getResource(listPathElem.get(i))));
            listImage.add(listImageIcon.get(i).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
            listImageIcon.set(i, new ImageIcon(listImage.get(i)));
            listMenuItem.add(new JMenuItem(listImageIcon.get(i)));
            add(listMenuItem.get(i));
        }
    }

    public List<JMenuItem> getListMenuItem() {
        return listMenuItem;
    }
}