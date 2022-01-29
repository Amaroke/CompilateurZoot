package zoot.arbre.expressions;

public abstract class Variable extends Expression {

    protected String texte ;

    protected Variable(String texte, int n) {
        super(n) ;
        this.texte = texte ;
    }
    
    @Override
    public void verifier() {
        throw new UnsupportedOperationException("fonction verfier non définie ") ;
    }

}
