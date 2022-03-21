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
    private String etiquette;

    public Fonction(ArbreAbstrait arbre, int noLigne, String idf, int numeroBloc) {
        this.arbre = arbre;
        this.noLigne = noLigne;
        this.idf = idf;
        this.numeroBloc = numeroBloc;
        this.parametres = new HashMap<>();
    }

    public String toMIPS() {
        StringBuilder str = new StringBuilder();
        int plusPetit = 0;
        for (Map.Entry<Entree, Symbole> m : this.parametres.entrySet()) {
            if (m.getValue().getDeplacement() < plusPetit) {
                plusPetit = m.getValue().getDeplacement();
            }
        }
        str.append("\t").append(etiquette).append(":\n");
        for (Map.Entry<Entree, Symbole> m : this.parametres.entrySet()) {
            str.append("\tlw $v0, 4($sp)\n");
            str.append("\taddi $sp,$sp, 4\n");
            str.append("\tsw $v0, ").append(plusPetit).append("($s7)\n");
            plusPetit += 4;
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

    public void setEtiquette() {
        this.etiquette = this.idf + this.parametres.size();
    }

    public String getIdf() {
        return idf;
    }

    public int getNumeroBloc() {
        return numeroBloc;
    }

    public int getNbParam() {
        return this.parametres.size();
    }

    public HashMap<Entree, Symbole> getParametres() {
        return parametres;
    }

    public String getTypeParam(int i) {
        for (Map.Entry<Entree, Symbole> m : this.parametres.entrySet()) {
            if (i == 0) {
                return m.getValue().getType();
            }
            i--;
        }
        return null;
    }
}
