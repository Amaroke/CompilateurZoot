package zoot.arbre.expressions.unaire;

import zoot.arbre.expressions.Expression;
import zoot.exceptions.Erreur;
import zoot.exceptions.ListeErreurs;

public class Oppose extends Expression {

    private final Expression expression;

    public Oppose(Expression expression, int n) {
        super(n);
        this.expression = expression;
    }

    @Override
    public void verifier() {
        System.out.println(this.expression.isBool());
        if (!this.expression.isBool()) {
            expression.verifier();
        } else {
            ListeErreurs.getInstance().ajouter(new Erreur("Impossible de traiter l'opérateur \"-\" devant un booléen", noLigne));
        }
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
        return false;
    }

    @Override
    public String getType() {
        return expression.getType();
    }

    @Override
    public String getNom() {
        return "-" + expression;
    }

    @Override
    public int nombreDErchov() {
        return 0;
    }

    @Override
    public String toMIPS() {
        return expression.toMIPS() + "\n" +
                "   #Empiler $v0\n" +
                "\tsw $v0,($sp)\n" +
                "\tadd $sp,$sp,-4\n" +
                "   #Dépiler $v0\n" +
                "\tadd $sp,$sp,4\n" +
                "\tlw $t8,($sp)\n" +
                "\tmul $v0, $t8, -1\n" +
                "\tmflo $v0\n";
    }
}
