package zoot.arbre.declarations;

public class SymboleFonction extends Symbole {

    public SymboleFonction(String type, int noligne) {
        super(type, noligne, 0);
        this.nbParams = TDS.getInstance().getNbParam();
    }
}
