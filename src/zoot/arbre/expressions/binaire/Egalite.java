package zoot.arbre.expressions.binaire;

import zoot.arbre.expressions.Expression;
import zoot.exceptions.Erreur;
import zoot.exceptions.ListeErreurs;

public class Egalite extends Binaire{

    public Egalite(Expression gauche, Expression droite, int n) {
        super(gauche, droite, n);
    }

    @Override
    public void verifier() {
        if ((!this.expressionDroite.isBool() && !this.expressionGauche.isBool()) || (this.expressionDroite.isBool() && this.expressionGauche.isBool())) {
            this.expressionDroite.verifier();
            this.expressionGauche.verifier();
        } else {
            ListeErreurs.getInstance().ajouter(new Erreur("Impossible de faire une égalité entre un entier et un booléen", noLigne));
        }
    }

    @Override
    public String toMIPS() {

        return this.expressionGauche.toMIPS() + "\n" +
                "\t   #Empiler $v0\n" +
                "\tsw $v0,($sp)\n" +
                "\tadd $sp,$sp,-4\n" +
                this.expressionDroite.toMIPS() + "\n" +
                "\t   #Dépiler $v0\n" +
                "\tadd $sp,$sp,4\n" +
                "\tlw $t8,($sp)\n" +
                "\tbeq $v0,$t8,si" + noLigne + "\n" +
                "\tli $v0, 0\n" +
                "\tj go" + noLigne + "\n" +
                "si" + noLigne + ":\n" +
                "\tli $v0, 1\n" +
                "\tj go" + noLigne + "\n" +
                "go" + noLigne + ":\n";
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
        return this.expressionGauche.getNom() + " == " + this.expressionDroite.getNom();
    }

}
