package zoot.arbre.expressions.binaire;

import zoot.arbre.expressions.Expression;
import zoot.exceptions.Erreur;
import zoot.exceptions.ListeErreurs;


public class Addition extends Binaire {

    public Addition(Expression gauche, Expression droite, int n) {
        super(gauche, droite, n);
    }

    @Override
    public void verifier() {
        if(!this.expressionDroite.isBool() && !this.expressionGauche.isBool()) {
            this.expressionDroite.verifier();
            this.expressionGauche.verifier();
        }else{
            ListeErreurs.getInstance().ajouter(new Erreur("Impossible de faire une addition avec des booléens", noLigne));
        }
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
        return expressionDroite.getType();
    }

}
