package zoot.arbre.declarations;


public class Symbole {

    private String type;
    protected int noLigne;
    private final int numBloc;
    protected int deplacement;
    protected int nbParams;

    public Symbole(String type, int noligne, int numBloc) {
        this.type = type;
        this.noLigne = noligne;
        this.numBloc = numBloc;
    }

    public int getDeplacement() {
        return deplacement;
    }

    public void setDeplacement(int deplacement) {
        this.deplacement = deplacement;
    }

    public void setNbParams(int nbParams) {
        this.nbParams = nbParams;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNoLigne() {
        return noLigne;
    }

    public int getNumBloc() {
        return numBloc;
    }

    public boolean isParam() {
        return false;
    }

    public int getNbParams() {
        return nbParams;
    }

    @Override
    public String toString() {
        return "Symbole{" +
                "type='" + type + '\'' +
                ", noLigne=" + noLigne +
                ", numBloc=" + numBloc +
                ", deplacement=" + deplacement +
                ", nbParams=" + nbParams +
                '}';
    }
}