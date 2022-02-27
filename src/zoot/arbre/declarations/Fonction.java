package zoot.arbre.declarations;

import zoot.arbre.ArbreAbstrait;

public class Fonction {

    private ArbreAbstrait arbre;
    private int noLigne;
    private String idf;

    public Fonction(ArbreAbstrait arbre, int noLigne, String idf) {
        this.arbre = arbre;
        this.noLigne = noLigne;
        this.idf = idf;
    }


    public String toMIPS(){
        StringBuilder str = new StringBuilder();
        str.append(idf + noLigne + ":\n");

        str.append("\tmove $s2, $sp\n");
        str.append("\tadd, $s2, $s2, " + 0 + "\n\n");

        str.append("\tadd, $sp, $sp, " + 0 + "\n\n");

        str.append("\tsw $ra, ($sp)\n");
        str.append("\tadd, $sp, $sp, -4\n\n");

        str.append(arbre.toMIPS());

        return str.toString();
    }

    public void verifier(){

    }



}
