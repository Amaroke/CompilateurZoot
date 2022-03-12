package zoot.arbre.declarations;

import zoot.arbre.ArbreAbstrait;

public class Fonction {

    private final ArbreAbstrait arbre;
    private final int noLigne;
    private final String idf;
    private final int numeroBloc;

    public Fonction(ArbreAbstrait arbre, int noLigne, String idf, int numeroBloc) {
        this.arbre = arbre;
        this.noLigne = noLigne;
        this.idf = idf;
        this.numeroBloc = numeroBloc;
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

    public int getNumeroBloc() {
        return numeroBloc;
    }
}
