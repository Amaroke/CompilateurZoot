package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;
import zoot.arbre.expressions.Idf;
import zoot.exceptions.Erreur;
import zoot.exceptions.ListeErreurs;
import zoot.exceptions.VariableNonDeclaree;

public class Affectation extends Instruction {

    protected final Expression exp;
    protected final Idf idf;

    public Affectation(Idf idf, Expression e, int n) {
        super(n);
        exp = e;
        this.idf = idf;
    }

    @Override
    public void verifier() {
        this.idf.verifier();
        this.exp.verifier();
        try {
            if (!this.idf.getType().equals(this.exp.getType())) {
                ListeErreurs.getInstance().ajouter(new Erreur((this.idf.getNom() + " = " + this.exp.getNom() + " n'est pas autorisé, les variables ne sont pas du même type."), this.noLigne));
            }
        } catch (VariableNonDeclaree ignored) {
        }
    }

    @Override
    public String toMIPS() {
        return "   #" + idf.getNom() + " = " + exp.getNom() + "\n" + "\t" + exp.toMIPS() + "\n\tsw $v0, " +
                idf.getDeplacement() + "($s7)\n";
    }

}
