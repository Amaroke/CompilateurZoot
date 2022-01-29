package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;
import zoot.arbre.expressions.Idf;

public class Affect extends Instruction {

    protected Expression exp ;
    protected Idf idf;

    public Affect(Idf idf, Expression e, int n) {
        super(n) ;
        exp = e ;
        this.idf = idf;
    }

    @Override
    public void verifier() {
        throw new UnsupportedOperationException("fonction verfier non définie ") ;
    }

    @Override
    public String toMIPS() {
        return null;
    }

}
