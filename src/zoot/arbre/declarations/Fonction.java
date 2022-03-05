package zoot.arbre.declarations;

import zoot.arbre.ArbreAbstrait;

public class Fonction {

    private final ArbreAbstrait arbre;
    private final int noLigne;
    private final String idf;
    //TODO connaitre type de retour
    //TODO Le type de l’expression est identique au type de retour de la fonction.
    //TODO L’instruction retourne ne peut pas se trouver en dehors du corps d’une fonction.

    public Fonction(ArbreAbstrait arbre, int noLigne, String idf) {
        this.arbre = arbre;
        this.noLigne = noLigne;
        this.idf = idf;
    }

    public String toMIPS(){
        //TODO C'est un peu random
        return "\t" + idf + ":\n" +
                arbre.toMIPS() + "\n";
    }

    public void verifier(){
        //TODO Une variable et une fonction ne peuvent pas porter le même nom.
    }

    public int getNoLigne() {
        return noLigne;
    }
}
