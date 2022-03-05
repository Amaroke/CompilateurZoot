package zoot.arbre.expressions;

import zoot.arbre.declarations.Entree;
import zoot.arbre.declarations.TDS;

public class AppelFonction extends Expression {

    private final Idf idf;

    public AppelFonction(Idf idf, int n) {
        super(n);
        this.idf = idf;
    }


    @Override
    public void verifier() {

    }


    @Override
    public String toMIPS() {
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
        return TDS.getInstance().identifier(new Entree(idf.getNom())).getType();
    }

    @Override
    public String getNom() {
        return this.idf.getNom();
    }
}
