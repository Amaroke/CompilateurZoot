package zoot.arbre.expressions;

import zoot.arbre.ArbreAbstrait;

public abstract class Expression extends ArbreAbstrait {

    protected Expression(int n) {
        super(n);
    }

    public abstract boolean isIdf();

    public abstract boolean isBool();

    public abstract boolean isFonction();

    public abstract String getType();

    public abstract String getNom();
}
