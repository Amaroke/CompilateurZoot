package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;

public class Ecrire extends Instruction {

    protected Expression exp ;

    public Ecrire (Expression e, int n) {
        super(n) ;
        exp = e ;
    }

    @Override
    public void verifier() {
        throw new UnsupportedOperationException("fonction verfier non définie ") ;
    }

    @Override
    public String toMIPS() {
        StringBuilder str = new StringBuilder("");
        str.append("\tli $a0, " + exp.toMIPS());
        str.append("\n\t#On affiche :\n");
        str.append("\tli $v0, 1\n\tsyscall\n");
        str.append("\tla $a0, saut_ligne\n\tli $v0, 4\n\tsyscall\n\n");
        return str.toString();
    }

}
