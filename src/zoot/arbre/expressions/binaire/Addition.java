package zoot.arbre.expressions.binaire;

import zoot.arbre.expressions.Expression;


public class Addition extends Binaire {

    public Addition(Expression gauche, Expression droite, int n) {
        super(gauche, droite, n);
    }

    @Override
    public String getNom() {
        return this.expressionGauche.getNom()+" + "+expressionDroite.getNom();
    }

    @Override
    public String toMIPS() {

        return expressionGauche.toMIPS() + "\n" +
                "   #Empiler $v0\n" +
                "\tsw $v0,($sp)\n" +
                "\tadd $sp,$sp,-4\n" +
                expressionDroite.toMIPS() + "\n" +
                "   #Dépiler $v0\n" +
                "\tadd $sp,$sp,4\n" +
                "\tlw $t8,($sp)\n" +
                "\tadd $v0, $t8, $v0\n";
    }

}
