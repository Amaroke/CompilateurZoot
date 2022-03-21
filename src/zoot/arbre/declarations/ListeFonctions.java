package zoot.arbre.declarations;

import zoot.arbre.expressions.Expression;
import zoot.exceptions.DoubleDeclaration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        for (Map.Entry<Entree, Symbole> m : TDS.getInstance().getBlocs().get(0).entrySet()) {
            if (m.getKey().getNom().equals(f.getIdf()) && !m.getKey().getType().equals("fonction")) {
                throw new DoubleDeclaration("La fonction : \"" + f.getIdf() + "\" porte le même nom qu'une variable.");
            }
        }
        for (Fonction fct : fonctions) {
            if (f.getIdf().equals(fct.getIdf()) && f.getNbParam() == fct.getNbParam())
                throw new DoubleDeclaration("La fonction : \"" + fct.getIdf() + "\" a été déclaré deux fois.");
        }

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

    public ArrayList<Expression> getParametresEffectifs() {
        return parametresEffectifs;
    }

    public void viderParametresEffectifs() {
        this.parametresEffectifs.clear();
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
