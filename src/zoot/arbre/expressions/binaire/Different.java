package zoot.arbre.expressions.binaire;

import zoot.arbre.declarations.TDS;
import zoot.arbre.expressions.Expression;
import zoot.exceptions.Erreur;
import zoot.exceptions.ListeErreurs;

public class Different extends Binaire {

    public Different(Expression gauche, Expression droite, int n) {
        super(gauche, droite, n);
    }

    @Override
    public void verifier() {
        if ((!this.expressionDroite.isBool() && !this.expressionGauche.isBool()) || (this.expressionDroite.isBool() && this.expressionGauche.isBool())) {
            this.expressionDroite.verifier();
            this.expressionGauche.verifier();
        } else {
            ListeErreurs.getInstance().ajouter(new Erreur("Impossible de faire la différence entre un entier et un booléen", noLigne));
        }
    }

    @Override
    public String toMIPS() {
        int etiquette = TDS.getInstance().getEtiquetteCourante();
        return this.expressionGauche.toMIPS() + "\n" +
                "   #Empiler $v0\n" +
                "\tsw $v0,($sp)\n" +
                "\tadd $sp,$sp,-4\n" +
                this.expressionDroite.toMIPS() + "\n" +
                "   #Dépiler $v0\n" +
                "\tadd $sp,$sp,4\n" +
                "\tlw $t8,($sp)\n" +
                "\tbne $v0,$t8,si" + etiquette + "\n" +
                "\tli $v0, 0\n" +
                "\tj go" + etiquette + "\n" +
                "si" + etiquette + ":\n" +
                "\tli $v0, 1\n" +
                "\tj go" + etiquette + "\n" +
                "go" + etiquette + ":\n";
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
        return this.expressionGauche.getNom() + " != " + this.expressionDroite.getNom();
    }
}
