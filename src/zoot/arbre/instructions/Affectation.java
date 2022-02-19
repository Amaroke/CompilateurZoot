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
        StringBuilder str = new StringBuilder();
        if(exp.isConstante()){
            str.append("\tli $v0, " + exp.toMIPS() + "\n")
                    .append("\tsw $v0, " + idf.toMIPS() + "\n");
        }else{
            str.append("\tlw $v0, " + exp.toMIPS() + "\n")
                    .append("\tsw $v0, " + idf.toMIPS() + "\n");
        }

        return str.toString();
    }

}
