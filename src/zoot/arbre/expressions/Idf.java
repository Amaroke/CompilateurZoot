package zoot.arbre.expressions;

import zoot.arbre.declarations.Symbole;
import zoot.arbre.declarations.TDS;
import zoot.exceptions.VariableNonDeclaree;

public class Idf extends Expression {

    private final String nom;
    private Symbole symbole;

    public Idf(String nom, int n) {
        super(n) ;
        this.nom = nom;
        this.noLigne = n;
    }

    @Override
    public void verifier() throws VariableNonDeclaree {
        this.symbole = TDS.getInstance().identifier(this.nom);
    }

    @Override
    public String toMIPS() {
        return (TDS.getInstance().identifier(nom).getDeplacement() + ("($s7)\n"));
    }

    @Override
    public boolean isIdf() {
        return true;
    }

    @Override
    public boolean isBool() {
        return (symbole.getType().equals("booleen"));
    }

    @Override
    public String getType() {
        return this.symbole.getType();
    }

    @Override
    public String getNom() {
        return this.nom;
    }

}
