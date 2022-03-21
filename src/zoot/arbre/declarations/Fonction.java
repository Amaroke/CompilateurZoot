package zoot.arbre.declarations;

import zoot.arbre.ArbreAbstrait;
import zoot.arbre.BlocDInstructions;
import zoot.arbre.instructions.Retourne;
import zoot.exceptions.Erreur;
import zoot.exceptions.ListeErreurs;

import java.util.HashMap;
import java.util.Map;

public class Fonction {

    private final ArbreAbstrait arbre;
    private final int noLigne;
    private final String idf;
    private final int numeroBloc;
    private HashMap<Entree, Symbole> parametres;

    public Fonction(ArbreAbstrait arbre, int noLigne, String idf, int numeroBloc) {
        this.arbre = arbre;
        this.noLigne = noLigne;
        this.idf = idf;
        this.numeroBloc = numeroBloc;
        this.parametres = new HashMap<>();
    }

    public String toMIPS() {
        StringBuilder str = new StringBuilder();
        str.append("\t").append(idf).append(":\n");
        if (parametres.size() != 0) {
            for (Map.Entry<Entree, Symbole> m : this.parametres.entrySet()) {
                str.append("\t").append(ListeFonctions.getInstance().recupererParametreEffectif().toMIPS()).append("\n");
                str.append("\tsw $v0, ").append(m.getValue().getDeplacement()).append("($s7)\n");
            }
        }
        str.append(arbre.toMIPS()).append("\n");
        return str.toString();
    }

    public void verifier() {
        boolean retournePresent = false;
        for (ArbreAbstrait a : ((BlocDInstructions) arbre).getProgramme()) {
            if (a instanceof Retourne) {
                retournePresent = true;
                break;
            }
        }
        if (!retournePresent) {
            ListeErreurs.getInstance().ajouter(new Erreur("Pas de retourne dans la fonction " + idf, noLigne));
        }
        arbre.verifier();
    }

    public void recupererParametres() {
        this.parametres = new HashMap<>(ListeFonctions.getInstance().getParametres());
        ListeFonctions.getInstance().viderParametres();
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
