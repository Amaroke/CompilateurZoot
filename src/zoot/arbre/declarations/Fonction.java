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
        this.arbre.verifier();
    }

    public String toMIPS(){
        return "\t" + idf + ":\n" +
                arbre.toMIPS() + "\n";
    }

    public void verifier(){
        arbre.verifier();
        //TODO Une variable et une fonction ne peuvent pas porter le même nom.
        //TODO fonc1 = vrai; fonctionne mais ne devrait pas
    }

    public int getNoLigne() {
        return noLigne;
    }
}
