package zoot.arbre.expressions.binaire;

import zoot.arbre.expressions.Expression;
import zoot.exceptions.Erreur;
import zoot.exceptions.ListeErreurs;

public class Intersection extends Binaire{

    public Intersection(Expression gauche, Expression droite, int n) {
        super(gauche, droite, n);
    }

    @Override
    public void verifier() {
        if(this.expressionDroite.isBool() &&  this.expressionGauche.isBool()) {
            this.expressionDroite.verifier();
            this.expressionGauche.verifier();
        }else{
            ListeErreurs.getInstance().ajouter(new Erreur("Impossible de faire une intersection entre autre chose que deux booléens", noLigne));
        }
    }


    @Override
    public String toMIPS() {
        return this.expressionDroite.toMIPS() + "\n" +
                "\tli $t8, 0\n" +
                "\tbeq $v0, $t8, si" + noLigne + "a" + "\n" +
                "\tj sinon" + noLigne + "a" + "\n" +
                "\tsi" + noLigne + "a" + ":\n" +
                "\tli $v0, 0\n" +
                "\tj go" + noLigne + "a" + "\n" +
                "\tsinon" + noLigne + "a" + ":\n" +
                this.expressionGauche.toMIPS() + "\n" +
                "   #Empiler $v0\n" +
                "\tsw $v0,($sp)\n" +
                "\tadd $sp,$sp,-4\n" +
                "   #Dépiler $v0\n" +
                "\tadd $sp,$sp,4\n" +
                "\tlw $t8,($sp)\n" +
                "\tli $v0, 0\n" +
                "\tbeq $t8, $v0, si" + noLigne + "b" + "\n" +
                "\tj sinon" + noLigne + "b" + "\n" +
                "\tsi" + noLigne + "b" + ":\n" +
                "\tli $v0, 0\n" +
                "\tj go" + noLigne + "a" + "\n" +
                "\tsinon" + noLigne + "b" + ":\n" +
                "\tli $v0, 1\n" +
                "\tj go" + noLigne + "a" + "\n" +
                "\tgo" + noLigne + "a" + ":\n";
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
        return this.expressionGauche.getNom() + " et " + this.expressionDroite.getNom();
    }
}
