package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;

public class Retourne extends Instruction{

    private Expression expression;

    public Retourne(int n, Expression expression) {
        super(n);
        this.expression = expression;
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        StringBuilder str = new StringBuilder();
        str.append(expression.toMIPS() + "\n");
        str.append("\tadd, $sp, $sp, 4\n");
        str.append("\tlw $ra, ($sp)\n");
        str.append("\tadd $sp, $sp, "+ 0 +"\n"); // $sp = $s2
        str.append("\tjr $ra\n");
        return str.toString();
    }
}
