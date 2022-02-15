package zoot.arbre.expressions;

public class ConstanteEntiere extends Constante {
    
    public ConstanteEntiere(String texte, int n) {
        super(texte, n) ;
    }

    @Override
    public String toMIPS() {
        return cste.equals("vrai") ? "1" : (cste.equals("faux") ? "0" : this.cste);
    }

}
