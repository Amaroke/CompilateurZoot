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
        //TODO le type de retour doit être le même que le type de la fonction
        //TODO L’instruction retourne ne peut pas se trouver en dehors du corps d’une fonction.
    }

    @Override
    public String toMIPS() {
        StringBuilder str = new StringBuilder();
        if(expression.getType().equals("booleen")){
            str.append("\tla, $v0, " + expression.toMIPS() + "\n");
            str.append("\tjr $ra\n");
        }else{
            str.append("\tli, $v0, " + expression.toMIPS() + "\n");
            str.append("\tjr $ra\n");
        }
        return str.toString();
    }
}
