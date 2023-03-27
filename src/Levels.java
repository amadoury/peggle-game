import javax.swing.JButton;

import javax.swing.* ;
import java.awt.*;
import java.util.ArrayList;

public class Levels {
    private ArrayList<JButton> buttonsLevels ;
    public Levels(int n){
        for(int i = 1; i <= n; i++){
            buttonsLevels.add(new JButton(i+"")) ;
        }
    }
}
