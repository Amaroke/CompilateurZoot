package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;
import zoot.arbre.expressions.Idf;

public class Lire extends Instruction {

    protected Idf idf ;

    public Lire(Idf idf, int n) {
        super(n) ;
        this.idf = idf ;
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
