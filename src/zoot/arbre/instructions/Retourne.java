package zoot.arbre.instructions;

import zoot.arbre.declarations.Entree;
import zoot.arbre.declarations.Fonction;
import zoot.arbre.declarations.ListeFonctions;
import zoot.arbre.declarations.TDS;
import zoot.arbre.expressions.Expression;
import zoot.exceptions.Erreur;
import zoot.exceptions.ListeErreurs;
import zoot.exceptions.VariableNonDeclaree;


public class Retourne extends Instruction{

    private final Expression expression;

    public Retourne(int n, Expression expression) {
        super(n);
        this.expression = expression;
    }

    @Override
    public void verifier() {
        if (ListeFonctions.getInstance().getCpt() != 1) {
            ListeErreurs.getInstance().ajouter(new Erreur("Impossible d'utiliser l'instruction retourne en dehors d'une fonction.", this.noLigne));
        }
        int tmp = 0;
        String idf = null;
        for (Fonction f : ListeFonctions.getInstance().getFonctions()) {
            if (f.getNoLigne() < this.noLigne) {
                if (f.getNoLigne() >= tmp) {
                    tmp = f.getNoLigne();
                    idf = f.getIdf();
                }
            }
        }
        try {
            if (!TDS.getInstance().identifier(new Entree(TDS.getInstance().getBloc(), idf, "fonction")).getType().equals(expression.getType())) {
                ListeErreurs.getInstance().ajouter(new Erreur("Le type de l'instruction retourne ne correspondant pas au type de retour de la fonction correspondante.", noLigne));
            }
        } catch (VariableNonDeclaree e) {
            ListeErreurs.getInstance().ajouter(new Erreur(e.getMessage(), this.noLigne));
        }
        expression.verifier();
    }

    @Override
    public String toMIPS() {
        StringBuilder str = new StringBuilder();
        if (!expression.isFonction()) {
            if (expression.getType().equals("booleen")) {
                str.append("\tla, $v0, ").append(expression.toMIPS()).append("\n");
            } else {

                if(expression.isIdf()) {
                    str.append("\tlw $v0, ").append(expression.toMIPS()).append("\n");
                }
                else {
                    str.append("\tli $v0, ").append(expression.toMIPS()).append("\n");
                }
            }
        } else {
            str.append(expression.toMIPS()).append("\n");
        }
        str.append("\tjr $ra\n");
        return str.toString();
    }
}
