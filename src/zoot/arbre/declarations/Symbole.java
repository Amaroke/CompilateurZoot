package zoot.arbre.declarations;


public class Symbole {

    private String type;
    protected int noLigne;
    private int numBloc;
    protected int deplacement;
    private int nbParams;

    public Symbole(String type, int noligne, int numBloc){
        this.type = type;
        this.noLigne = noligne;
        this. numBloc = numBloc;
    }

    public int getDeplacement() {
        return deplacement;
    }

    public void setDeplacement(int deplacement) {
        this.deplacement = deplacement;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Symbole{");
        sb.append("deplacement=").append(deplacement);
        sb.append(", type='").append(type).append(" ");
        sb.append(", noLigne=").append(noLigne);
        sb.append(", numBloc=").append(numBloc);
        sb.append("}");
        return sb.toString();
    }
}