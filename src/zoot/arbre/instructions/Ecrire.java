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
        exp.verifier();
    }

    @Override
    public String toMIPS() {
        StringBuilder str = new StringBuilder();
        if(exp.isConstante()){
            str.append("\tli $a0, ").append(exp.toMIPS());
            str.append("\n\t#On affiche :\n");
            str.append("\tli $v0, 1\n\tsyscall\n");

        }else {
            str.append("\tlw $v0, ")
                    .append(exp.toMIPS())
                    .append("\tmove $a0, $v0\n")
                    .append("\tli $v0, 1\n")
                    .append("\tsyscall\n");
        }
        str.append("\tla $a0, saut_ligne\n\tli $v0, 4\n\tsyscall\n\n");
        return str.toString();
    }

}
