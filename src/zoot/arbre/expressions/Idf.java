package zoot.arbre.expressions;

public class Idf extends Variable {

    public Idf(String texte, int n) {
        super(texte, n) ;
        this.noLigne = n;
    }

    @Override
    public String toMIPS() {
        return null;
    }

}
