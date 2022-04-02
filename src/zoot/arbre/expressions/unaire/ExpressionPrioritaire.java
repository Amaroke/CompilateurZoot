package zoot.arbre.expressions.unaire;

import zoot.arbre.expressions.Expression;

public class ExpressionPrioritaire extends Expression {

    private final Expression expression;

    public ExpressionPrioritaire(Expression expression, int n) {
        super(n);
        this.expression = expression;
    }

    @Override
    public void verifier() {
        expression.verifier();
    }

    @Override
    public boolean isIdf() {
        return false;
    }

    @Override
    public boolean isBool() {
        return expression.isBool();
    }

    @Override
    public boolean isFonction() {
        return false;
    }

    @Override
    public String getType() {
        return expression.getType();
    }

    @Override
    public String getNom() {
        return "(" + this.expression + ")";
    }

    @Override
    public int nombreDErchov() {
        return 0;
    }

    @Override
    public String toMIPS() {
        return expression.toMIPS();
    }
}
