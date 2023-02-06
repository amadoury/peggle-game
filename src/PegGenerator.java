
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PegGenerator {

    private ArrayList<Peg> pegListe = new ArrayList<Peg>();

    PegGenerator() {
        // circleOfPeg(200, 20, 200, 200);
        // lineHorizontalOfPeg(50, 100, 100, 900, 60);
        multipleLinesOfPeg(25, 100, 250, 900, 60, 7);
        // pegListe.add(null);
    }

    void circleOfPeg(double radius, int pegSpacing, int coordX, int coordY) {
        for (int i = 0; i < 360; i += pegSpacing) {
            int x = coordX + (int) (radius * Math.cos(Math.toRadians(i)));
            int y = coordY + (int) (radius * Math.sin(Math.toRadians(i)));
            // g.fillOval(x - 2, y - 2, 50, 50);
            pegListe.add(new PegCercle(x, y, 50, "bleu"));
        }
    }

    void lineHorizontalOfPeg(double radius, int coordX, int coordY, int length, int pegSpacing) {
        String c = "bleu";
        Random r = new Random();
        int numberOfOrangePeg = r.nextInt(5) + 2;
        if (numberOfOrangePeg == 6)
            numberOfOrangePeg = 2;
        List<Integer> l = melangeNumbers(length / pegSpacing, numberOfOrangePeg);
        int lPosition = 0;
        for (int i = 0; i < length; i += pegSpacing) {
            if (lPosition < l.size() && i / pegSpacing == l.get(lPosition)) {
                c = "orange";
                ++lPosition;
            }
            pegListe.add(new PegCercle(coordX + i, coordY, radius, c));
            c = "bleu";
        }
    }

    void multipleLinesOfPeg(double radius, int coordX, int coordY, int length, int pegSpacing, int numberLines) {
        for (int i = 0; i < numberLines * pegSpacing; i += pegSpacing) {
            if (i / pegSpacing % 2 == 0)
                coordX += pegSpacing / 2;
            else
                coordX -= pegSpacing / 2;
            lineHorizontalOfPeg(radius, coordX, coordY + i, length, pegSpacing);
        }
    }

    public ArrayList<Peg> getPegListe() {
        return pegListe;
    }

    private List<Integer> melangeNumbers(int n, int len) {
        List<Integer> l = new ArrayList<Integer>();
        for (int i = 0; i < n; ++i) {
            l.add(i);
        }
        Collections.shuffle(l);
        l = l.subList(0, len);
        Collections.sort(l);
        return l;
    }
}
