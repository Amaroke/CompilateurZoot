package zoot.arbre.expressions;

public class Booleen extends Constante{

    public Booleen(String texte, int n) {
        super(texte, n);
    }

    @Override
    public String toMIPS() {
        return cste.equals("vrai") ? "1" : "0";
    }

}
