package zoot.arbre.declarations;

public class SymboleParam extends Symbole {

    public SymboleParam(String type, int noligne, int numBloc) {
        super(type, noligne, numBloc);
    }

    @Override
    public boolean isParam() {
        return true;
    }

    @Override
    public String toString() {
        return "SymboleParam{" +
                "noLigne=" + noLigne +
                ", deplacement=" + deplacement +
                ", nbParams=" + nbParams +
                '}';
    }
}
