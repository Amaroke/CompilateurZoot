package zoot.arbre;

import java.util.ArrayList;

public abstract class ArbreAbstrait {

    protected int noLigne;

    protected ArbreAbstrait(int n) {
        noLigne = n;
    }

    public int getNoLigne() {
        return noLigne;
    }

    public abstract void verifier();

    public abstract String toMIPS();

}
