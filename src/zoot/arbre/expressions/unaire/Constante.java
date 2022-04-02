package zoot.arbre.expressions.unaire;

import zoot.arbre.expressions.Expression;

public abstract class Constante extends Expression {

    protected final String cste;

    protected Constante(String texte, int n) {
        super(n);
        cste = texte;
    }

    @Override
    public void verifier() {
    }

    @Override
    public String toString() {
        return cste;
    }

    @Override
    public boolean isIdf() {
        return false;
    }

    public String getNom() {
        return this.cste;
    }

}
