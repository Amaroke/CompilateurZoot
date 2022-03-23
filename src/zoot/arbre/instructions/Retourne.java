package zoot.arbre.instructions;

import zoot.arbre.declarations.Fonction;
import zoot.arbre.declarations.ListeFonctions;
import zoot.arbre.declarations.TDS;
import zoot.arbre.expressions.Expression;
import zoot.exceptions.Erreur;
import zoot.exceptions.ListeErreurs;
import zoot.exceptions.VariableNonDeclaree;

import java.util.ArrayList;
import java.util.Arrays;


public class Retourne extends Instruction {

    private final Expression expression;

    public Retourne(int n, Expression expression) {
        super(n);
        this.expression = expression;
    }

    @Override
    public void verifier() {
        expression.verifier();
        if (ListeFonctions.getInstance().getCpt() != 1) {
            ListeErreurs.getInstance().ajouter(new Erreur("Impossible d'utiliser l'instruction retourne en dehors d'une fonction.", this.noLigne));
        }
        try {
            int tmp = 0;
            Fonction fonctionMere = null;
            for (Fonction f : ListeFonctions.getInstance().getFonctions()) {
                if (f.getNoLigne() < this.noLigne) {
                    if (f.getNoLigne() > tmp) {
                        tmp = f.getNoLigne();
                        fonctionMere = f;
                    }
                }
            }
            String type = null;
            if (fonctionMere != null) {
                type = TDS.getInstance().trouverFonction((fonctionMere).getIdf(), fonctionMere.getNbParam()).getType();
            }
            if (type != null && !type.equals(expression.getType())) {
                ListeErreurs.getInstance().ajouter(new Erreur("Le type de l'instruction retourné ne correspond pas au type de retour de la fonction correspondante.", noLigne));
            }
        } catch (VariableNonDeclaree e) {
            ListeErreurs.getInstance().ajouter(new Erreur(e.getMessage(), this.noLigne));
        }
    }

    @Override
    public String toMIPS() {
        ArrayList<String> registres = new ArrayList<>(Arrays.asList("$v0", "$t0", "$t1", "$t2"));
        return "\t" + expression.toMIPS() + "\n" +
                "\tjr $ra\n";
    }
}
