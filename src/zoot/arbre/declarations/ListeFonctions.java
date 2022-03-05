package zoot.arbre.declarations;

import java.util.ArrayList;

public class ListeFonctions {

    private final ArrayList<Fonction> fonctions;
    private int cpt;
    private static final ListeFonctions INSTANCE = new ListeFonctions();

    public ListeFonctions(){
        this.fonctions = new ArrayList<>();
        this.cpt = 0;
    }

    public static ListeFonctions getInstance(){
        return INSTANCE;
    }

    public void ajouter(Fonction f){
        this.fonctions.add(f);
    }

    public ArrayList<Fonction> getFonctions() {
        return fonctions;
    }

    public int getCpt() {
        return cpt;
    }

    public void setCpt(int cpt) {
        this.cpt = cpt;
    }

    public void verifier() {
        for (Fonction f : fonctions) {
            f.verifier();
        }
    }
}
