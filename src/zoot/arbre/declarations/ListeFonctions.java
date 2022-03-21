package zoot.arbre.declarations;

import zoot.arbre.expressions.Expression;

import java.util.ArrayList;
import java.util.HashMap;

public class ListeFonctions {

    private final ArrayList<Fonction> fonctions;
    private final ArrayList<Expression> parametres;
    private int cpt;
    private static final ListeFonctions INSTANCE = new ListeFonctions();

    public ListeFonctions() {
        this.fonctions = new ArrayList<>();
        this.parametres = new ArrayList<>();
        this.cpt = 0;
    }

    public static ListeFonctions getInstance() {
        return INSTANCE;
    }

    public void ajouter(Fonction f) {
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

    public void ajouterParametre(Expression param) {
        this.parametres.add(param);
    }

    public void verifier() {
        for (Fonction f : this.fonctions) {
            TDS.getInstance().setBlocCourant(f.getNumeroBloc());
            f.verifier();
            TDS.getInstance().setBlocCourant(0);
        }
    }
}
