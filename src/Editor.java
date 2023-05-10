import java.util.ArrayList;
import java.util.List;

public class Editor {
    private List<Peg> listPeg;

    public Editor() {
        listPeg = new ArrayList<Peg>();
    }

    public List<Peg> getListPeg() {
        return listPeg;
    }

    public void addPeg(Peg peg) {
        listPeg.add(peg);
    }
}