package zoot.arbre.expressions.unaire;

import zoot.arbre.declarations.TDS;
import zoot.arbre.expressions.Expression;
import zoot.exceptions.Erreur;
import zoot.exceptions.ListeErreurs;

public class Negation extends Expression {

    Expression expression;

    public Negation(Expression expression, int n) {
        super(n);
        this.expression = expression;
    }

    @Override
    public void verifier() {
        if(expression.isBool()){
            expression.verifier();
        }else{
            ListeErreurs.getInstance().ajouter(new Erreur("Impossible de faire la négation d'autre chose qu'un booléen", noLigne));
        }
    }

    @Override
    public boolean isIdf() {
        return false;
    }

    @Override
    public boolean isBool() {
        return true;
    }

    @Override
    public boolean isFonction() {
        return false;
    }

    @Override
    public String getType() {
        return "booleen";
    }

    @Override
    public String getNom() {
        return "non"+this.expression.getNom();
    }

    @Override
    public int nombreDErchov() {
        return 0;
    }

    @Override
    public String toMIPS() {
        int etiquette = TDS.getInstance().getEtiquetteCourante();

        return this.expression.toMIPS() + "\n" +
                "\tli $t8, 1\n" +
                "\tbeq $v0,$t8,si" + etiquette + "\n" +
                "\tli $v0, 1\n" +
                "\tj go" + etiquette + "\n" +
                "\tsi" + etiquette + ":\n" +
                "\tli $v0, 0\n" +
                "\tj go" + etiquette + "\n" +
                "\tgo" + etiquette + ":\n";
    }
}
