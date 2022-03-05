package zoot.arbre.expressions;

public class AppelFonction extends Expression {

    private final Idf idf;

    public AppelFonction(Idf idf, int n) {
        super(n);
        this.idf = idf;
    }

    @Override
    public void verifier() {
        //TODO
    }

    @Override
    public String toMIPS() {
        //TODO C'est un peu random
        return "\tsw $v0, ($sp)\n" +
                "\tadd $sp, $sp, -4\n" +
                "\tjal " + idf.getNom() + "\n\n";
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
        //TODO
        return "entier";
    }

    @Override
    public String getNom() {
        return this.idf.getNom();
    }
}
