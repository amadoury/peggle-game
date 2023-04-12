
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.xml.crypto.Data;

public class PegGenerator {

    private ArrayList<Peg> pegListe = new ArrayList<Peg>();
    private double resolutionScreen;
    private double widthBoard = 900;

    private int radius;
    int coordX;
    int coordY;
    int length;
    int pegSpacing;
    double longueur;
    double largeur;

    PegGenerator(double resolutionScreen, int r) {
        // multipleLinesOfPeg(12, 100 , 250, 1200, 80 , 7);
        this.resolutionScreen = resolutionScreen;
        radius = r;
        // multipleLinesOfPeg((int) (12 / (0.96)) , (int) (100 / (0.96)), (int)(250
        // /(0.96)) , (int)(900 /(0.96)), (int)(80 /(0.96)), 7);

    }

    void circleOfPeg(double radius, int pegSpacing, int coordX, int coordY) {
        for (int i = 0; i < 360; i += pegSpacing) {
            int x = coordX + (int) (radius * Math.cos(Math.toRadians(i)));
            int y = coordY + (int) (radius * Math.sin(Math.toRadians(i)));
            // g.fillOval(x - 2, y - 2, 50, 50);
            pegListe.add(new PegCercle(x, y, 50, "bleu"));
        }
    }

    void lineHorizontalOfPeg(int radius, int coordX, int coordY, int length, int pegSpacing) {
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
            // pegListe.add(new PegCercle(coordX + i, coordY, radius, c));
            pegListe.add(new PegRectangle(coordX + i, coordY, longueur, largeur, Math.PI / 5,
                    "bleu"));
            // pegListe.add(new PegRebond(coordX + i, coordY, radius * 3, c));

            c = "bleu";
        }
    }

    void multipleLinesOfPeg(int radius, int coordX, int coordY, int length, int pegSpacing, int numberLines) {
        for (int i = 0; i < numberLines * pegSpacing; i += pegSpacing) {
            if (i / pegSpacing % 2 == 0)
                coordX += pegSpacing / 2;
            else
                coordX -= pegSpacing / 2;
            lineHorizontalOfPeg(radius, coordX, coordY + i, length, pegSpacing);
        }
    }

    void spiralOfPeg(int coordX, int coordY, int length, int deltaRadius) {
        double deltaTheta = 0;
        double radiusSpiral = 60;
        for (double theta = 0; radiusSpiral <= length; theta += deltaTheta) {
            double x = radiusSpiral * Math.cos(theta);
            double y = radiusSpiral * Math.sin(theta);
            pegListe.add(new PegCercle((int) (coordX + x), (int) (coordY + y), radius, "bleu"));
            radiusSpiral += deltaRadius;
            deltaTheta = Math.toDegrees(20 * 360 / (2 * Math.PI * radiusSpiral) / (radiusSpiral + 500));
        }
    }

    /* ajoute les peg avec la résolution de l'écran */
    public void adaptResolutionPeg(int radius, int coordX, int coordY, int length, int pegSpacing, int longueur,
            int largeur) {
        this.radius = (int) (radius / resolutionScreen);
        this.coordX = (int) (coordX / resolutionScreen);
        this.coordY = (int) (coordY / resolutionScreen);
        this.length = (int) (length / resolutionScreen);
        this.pegSpacing = (int) (pegSpacing / resolutionScreen);
        this.longueur = (int) (longueur / resolutionScreen);
        this.largeur = (int) (largeur / resolutionScreen);

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

    public ArrayList<Peg> contact(Ball b) {// retourne null si contact avec aucun peg
        ArrayList<Peg> l = new ArrayList<Peg>();
        for (int i = 0; i < pegListe.size(); ++i) {
            Peg p = pegListe.get(i);
            if (p instanceof PegCercle && !p.isDestructed()) {
                double normeVect = (Math
                        .sqrt(Math.pow(b.getXt() - p.getPegX(), 2) + Math.pow(b.getYt() - p.getPegY(), 2)));
                if (normeVect <= ((PegCercle) p).getRayon() + b.getRayon()) {
                    l.add(p);
                }
            }
            if (p instanceof PegRectangle && !p.isDestructed()) {
                // if (b.getXt() + b.getRayon() > p.getPegX() - ((PegRectangle) p).getLongueur()
                // / 2.
                // && b.getXt() - b.getRayon() < p.getPegX() + ((PegRectangle) p).getLongueur()
                // / 2.
                // && b.getYt() + b.getRayon() > p.getPegY() - (((PegRectangle) p).getLargeur()
                // / 2.)
                // && b.getYt() - b.getRayon() < p.getPegY() + ((PegRectangle) p).getLargeur() /
                // 2.) {
                // l.add(p);
                // }

                PegRectangle r = (PegRectangle) p;

                double rayonAddedX;
                double rayonAddedY;

                double[] PARayons = { 0, b.getRayon(), -b.getRayon() };
                boolean insideOfRectangle = false;

                for (double PAr1 : PARayons) {
                    for (double PAr2 : PARayons) {
                        double PAx = b.getXt() - r.getOrigineVecteurX() + PAr1;
                        double PAy = b.getYt() - r.getOrigineVecteurY() + PAr2;
                        double detWithLongueur = PAx * r.getVecteurLongueurY() -
                                r.getVecteurLongueurX() * PAy;
                        double detWithLargeur = PAx * r.getVecteurLargeurY() - r.getVecteurLargeurX()
                                * PAy;

                        // double d = det(PQ, PR);
                        // A position de la balle peg PA vecteur longueur
                        // 0 <= -det(PA, PQ)/d <= 1 && 0 <= det(PA, PR)/d <= 1
                        double n = -detWithLongueur / r.getDeterminant();
                        double m = detWithLargeur / r.getDeterminant();

                        if (0 <= n && n <= 1
                                && 0 <= m && m <= 1) {
                            insideOfRectangle = true;
                        }
                    }
                }
                if (insideOfRectangle)
                    l.add(p);

                // System.out.println(" n m " + n + " " + m);

                // boolean[] conditions = r.projectionBallOrigineVecteurs(b.getXt(), b.getYt(),
                // b.getRayon());

                // boolean contactWithPeg = false;
                // for (int j = 0; j < 4; ++j) {
                // if (conditions[j])
                // contactWithPeg = true;
                // }
                // if (contactWithPeg)
                // l.add(p);

            }
        }
        if (l.size() == 0)
            return null;
        return l;
    }

    public void retireAllTouched() {
        for (int i = 0; i < pegListe.size(); ++i) {
            pegListe.get(i).updatePeg();
        }
    }

    public void setWidthBoard(double w) {
        widthBoard = w;
        adaptResolutionPeg(radius, 100, 250, (int) widthBoard - 400, 90 + 2 *
                radius, 60, 30);
        multipleLinesOfPeg(radius, coordX, coordY, length, pegSpacing, 6);
        // spiralOfPeg(500, 500, 300, 15);
        // pegListe.add(new PegRebond(500, 500, radius * 3, "bleu"));
        // pegListe.add(new PegSoleil(600, 200, radius * 3, "bleu"));
        // pegListe.add(new PegRectangle(600, 200, longueur, largeur, 0, "bleu"));

    }

}
