package zoot.arbre.declarations;

import zoot.arbre.expressions.Expression;

import java.util.ArrayList;
import java.util.HashMap;

public class ListeFonctions {

    private final ArrayList<Fonction> fonctions;
    private final ArrayList<Expression> parametresEffectifs;
    private final HashMap<Entree, Symbole> parametres;
    private int cpt;
    private static final ListeFonctions INSTANCE = new ListeFonctions();

    public ListeFonctions() {
        this.fonctions = new ArrayList<>();
        this.parametresEffectifs = new ArrayList<>();
        this.parametres = new HashMap<>();
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

    public Expression recupererParametreEffectif() {
        System.out.println(parametresEffectifs);
        Expression res = parametresEffectifs.get(0);
        parametresEffectifs.remove(0);
        return res;
    }

    public void ajouterParametreEffectif(Expression param) {
        this.parametresEffectifs.add(param);
    }

    public void viderParametres() {
        this.parametres.clear();
    }

    public void ajouterParametre(Entree e, Symbole s) {
        parametres.put(e, s);
    }

    public HashMap<Entree, Symbole> getParametres() {
        return parametres;
    }

    public void verifier() {
        for (Fonction f : this.fonctions) {
            TDS.getInstance().setBlocCourant(f.getNumeroBloc());
            f.verifier();
            TDS.getInstance().setBlocCourant(0);
        }
    }
}
