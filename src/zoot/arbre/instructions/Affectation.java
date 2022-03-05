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
        StringBuilder str = new StringBuilder();
        if (exp.isFonction()) {
            str.append("   #").append(idf.getNom()).append(" = ").append(exp.getNom()).append("\n");
            str.append(exp.toMIPS());
            str.append("\tsw $v0, ").append(idf.toMIPS()).append("\n");
        } else {
            if (!exp.isIdf()) {
                str.append("   #").append(idf.getNom()).append(" = ").append(exp).append("\n");
                if (exp.isBool()) {
                    str.append("\tla $v0, ").append(exp.toMIPS()).append("\n")
                            .append("\tsw $v0, ").append(idf.toMIPS()).append("\n");
                } else {
                    str.append("\tli $v0, ").append(exp.toMIPS()).append("\n")
                            .append("\tsw $v0, ").append(idf.toMIPS()).append("\n");
                }
            } else {
                str.append("   #").append(idf.getNom()).append(" = ").append(exp.getNom()).append("\n");
                str.append("\tlw $v0, ").append(exp.toMIPS()).append("\n").append("\tsw $v0, ").append(idf.toMIPS()).append("\n");
            }
        }
        return str.toString();
    }


    @Override
    protected boolean isRetourne() {
        return false;
    }
}
