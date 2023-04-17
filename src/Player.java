public class Player {
    private int nbPegOrange ; //le nombre de peg orange detruit
    private int score ;

    public Player(){
        nbPegOrange = 0;
        score = 0;
    }

    public int getNbPegOrange() {
        return nbPegOrange;
    }

    public int getScore() {
        return score;
    }

    public void setNbPegOrange(int nbPegOrange) {
        this.nbPegOrange = nbPegOrange;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void incrementeScore(){
        score++;
    }
}
