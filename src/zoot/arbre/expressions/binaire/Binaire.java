package zoot.arbre.expressions.binaire;

import zoot.arbre.expressions.Expression;
import zoot.arbre.expressions.unaire.ConstanteEntiere;

import java.util.ArrayList;

public abstract class Binaire extends Expression {

    private Expression expressionGauche;
    private Expression expressionDroite;

    public Binaire(Expression gauche, Expression droite, int n) {
        super(n);
        this.expressionGauche = gauche;
        this.expressionDroite = droite;
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        return null;
    }

    public String toMIPS(ArrayList<String> registres) {
        return "";
    }

    @Override
    public boolean isIdf() {
        return false;
    }

    @Override
    public boolean isBool() {
        return false;
    }

    @Override
    public boolean isFonction() {
        return false;
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public String getNom() {
        return null;
    }

    @Override
    public int nombreDErchov() {
        int nbD = expressionDroite.nombreDErchov();
        int nbG = expressionGauche.nombreDErchov();
        return (nbG == nbD) ? nbG + 1 : Math.max(nbG, nbD);
    }
}
