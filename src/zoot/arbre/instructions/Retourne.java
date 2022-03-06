package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;


public class Retourne extends Instruction{

    private final Expression expression;

    public Retourne(int n, Expression expression) {
        super(n);
        this.expression = expression;
    }

    @Override
    public void verifier() {
        expression.verifier();
        //TODO Le type de retour n'est pas forcément celui de la fonction.
        //TODO Retour est hors d'une fonction.
    }

    @Override
    public String toMIPS() {
        StringBuilder str = new StringBuilder();
        if (expression.getType().equals("booleen")) {
            str.append("\tla, $v0, ").append(expression.toMIPS()).append("\n");
            str.append("\tjr $ra\n");
        } else {
            str.append("\tli, $v0, ").append(expression.toMIPS()).append("\n");
            str.append("\tjr $ra\n");
        }
        return str.toString();
    }
}
