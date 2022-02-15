package zoot.arbre.instructions;

import zoot.arbre.declarations.TDS;
import zoot.arbre.expressions.Expression;
import zoot.arbre.expressions.Idf;
import zoot.exceptions.VariableNonDeclaree;

public class Affectation extends Instruction {

    protected Expression exp ;
    protected Idf idf;

    public Affectation(Idf idf, Expression e, int n) {
        super(n) ;
        exp = e ;
        this.idf = idf;
    }

    @Override
    public void verifier() throws VariableNonDeclaree {
        this.idf.verifier();
        this.exp.verifier();
    }

    @Override
    public String toMIPS() {
        StringBuilder str = new StringBuilder();
        str.append("\t# ").append(idf.getNom()).append(" = ").append(exp.toMIPS()).append("\n");
        System.out.println(exp);
        str.append("\tli $v0, ").append(exp.toMIPS()).append("\n");
        str.append("\tsw $v0, ").append(TDS.getInstance().identifier(idf.getNom()).getDeplacement()).append("($s7)\n\n");
        return str.toString();
    }

}
