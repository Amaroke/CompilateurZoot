package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;
import zoot.arbre.expressions.Idf;
import zoot.exceptions.Transtypage;

public class Affectation extends Instruction {

    protected Expression exp ;
    protected Idf idf;

    public Affectation(Idf idf, Expression e, int n) {
        super(n) ;
        exp = e ;
        this.idf = idf;
    }

    @Override
    public void verifier() throws Transtypage {
        this.idf.verifier();
        this.exp.verifier();
        if (!this.idf.getType().equals(this.exp.getType())) {
            throw new Transtypage();
        }
    }

    @Override
    public String toMIPS() {
        StringBuilder str = new StringBuilder();
        if (!exp.isIdf()) {
            if (exp.isBool()) {
                str.append("\tla $v0, ").append(exp.toMIPS()).append("\n")
                        .append("\tsw $v0, ").append(idf.toMIPS()).append("\n");
            } else {
                str.append("\tli $v0, ").append(exp.toMIPS()).append("\n")
                        .append("\tsw $v0, ").append(idf.toMIPS()).append("\n");
            }
        } else {
            str.append("\tlw $v0, ").append(exp.toMIPS()).append("\n")
                    .append("\tsw $v0, ").append(idf.toMIPS()).append("\n");
        }
        return str.toString();
    }

}
