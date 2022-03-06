package zoot.arbre.expressions;

public class AppelFonction extends Expression {

    private final Idf idf;

    public AppelFonction(Idf idf, int n) {
        super(n);
        this.idf = idf;
    }


    @Override
    public void verifier() {
        idf.verifier();
    }


    @Override
    public String toMIPS() {
        return "\tjal " + idf.getNom() + "\n";
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
        return true;
    }

    @Override
    public String getType() {
        return idf.getType();
    }

    @Override
    public String getNom() {
        return this.idf.getNom();
    }
}
