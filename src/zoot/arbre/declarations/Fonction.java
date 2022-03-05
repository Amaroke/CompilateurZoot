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
        //TODO Une variable et une fonction ne peuvent pas porter le même nom.
        //TODO Il y a un problème on peut faire fonc1 = vrai
    }

    public int getNoLigne() {
        return noLigne;
    }
}
