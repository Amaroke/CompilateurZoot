package zoot.arbre.expressions;

public class Idf extends Expression {

    private String nom;

    public Idf(String nom, int n) {
        super(n) ;
        this.nom = nom;
        this.noLigne = n;
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        return null;
    }

}
