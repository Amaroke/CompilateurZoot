package zoot.arbre.expressions.binaire;

import zoot.arbre.declarations.TDS;
import zoot.arbre.expressions.Expression;
import zoot.exceptions.Erreur;
import zoot.exceptions.ListeErreurs;

public class Union extends Binaire {

    public Union(Expression gauche, Expression droite, int n) {
        super(gauche, droite, n);
    }

    @Override
    public void verifier() {
        if (this.expressionDroite.isBool() && this.expressionGauche.isBool()) {
            this.expressionDroite.verifier();
            this.expressionGauche.verifier();
        } else {
            ListeErreurs.getInstance().ajouter(new Erreur("Impossible de faire une union entre autre chose que deux booléens", noLigne));
        }
    }


    @Override
    public String toMIPS() {
        int etiquette = TDS.getInstance().getEtiquetteCourante();
        return this.expressionDroite.toMIPS() + "\n" +
                "\tli $t8, 1\n" +
                "\tbeq $v0, $t8, si" + etiquette + "a" + "\n" +
                "\tj sinon" + etiquette + "a" + "\n" +
                "\tsi" + etiquette + "a" + ":\n" +
                "\tli $v0, 1\n" +
                "\tj go" + etiquette + "a" + "\n" +
                "\tsinon" + etiquette + "a" + ":\n" +
                this.expressionGauche.toMIPS() + "\n" +
                "   #Empiler $v0\n" +
                "\tsw $v0,($sp)\n" +
                "\tadd $sp,$sp,-4\n" +
                "   #Dépiler $v0\n" +
                "\tadd $sp,$sp,4\n" +
                "\tlw $t8,($sp)\n" +
                "\tli $v0, 1\n" +
                "\tbeq $t8, $v0, si" + etiquette + "b" + "\n" +
                "\tj sinon" + etiquette + "b" + "\n" +
                "\tsi" + etiquette + "b" + ":\n" +
                "\tli $v0, 1\n" +
                "\tj go" + etiquette + "a" + "\n" +
                "\tsinon" + etiquette + "b" + ":\n" +
                "\tli $v0, 0\n" +
                "\tj go" + etiquette + "a" + "\n" +
                "\tgo" + etiquette + "a" + ":\n";
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
        return this.expressionGauche.getNom() + " ou " + this.expressionDroite.getNom();
    }
}
