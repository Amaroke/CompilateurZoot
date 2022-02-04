package zoot.arbre.instructions;

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
        return null;
    }

}
