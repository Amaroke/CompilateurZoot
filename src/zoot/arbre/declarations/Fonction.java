package zoot.arbre.declarations;

import zoot.arbre.ArbreAbstrait;

public class Fonction {

    private final ArbreAbstrait arbre;
    private final int noLigne;
    private final String idf;

    public Fonction(ArbreAbstrait arbre, int noLigne, String idf) {
        this.arbre = arbre;
        this.noLigne = noLigne;
        this.idf = idf;
    }

    public String toMIPS(){
        return "\t" + idf + ":\n" +
                arbre.toMIPS() + "\n";
    }

    public void verifier(){
        arbre.verifier();
    }

    public int getNoLigne() {
        return noLigne;
    }

    public String getIdf() {
        return idf;
    }
}
