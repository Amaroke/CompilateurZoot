package zoot.arbre.expressions.binaire;

import zoot.arbre.expressions.Expression;

public abstract class Binaire extends Expression {

    protected Expression expressionGauche;
    protected Expression expressionDroite;

    public Binaire(Expression gauche, Expression droite, int n) {
        super(n);
        this.expressionGauche = gauche;
        this.expressionDroite = droite;
    }

    @Override
    public abstract void verifier();

    @Override
    public abstract String toMIPS();

    @Override
    public abstract boolean isIdf();

    @Override
    public abstract boolean isBool();

    @Override
    public abstract boolean isFonction();

    @Override
    public abstract String getType();

    @Override
    public abstract String getNom();

    @Override
    public int nombreDErchov() {
        int nbD = expressionDroite.nombreDErchov();
        int nbG = expressionGauche.nombreDErchov();
        return (nbG == nbD) ? nbG + 1 : Math.max(nbG, nbD);
    }
}
