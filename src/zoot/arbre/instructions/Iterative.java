package zoot.arbre.instructions;

import zoot.arbre.ArbreAbstrait;
import zoot.arbre.declarations.TDS;
import zoot.arbre.expressions.Expression;
import zoot.exceptions.Erreur;
import zoot.exceptions.ListeErreurs;

public class Iterative extends Instruction {

    Expression expressionConditionnelle;
    ArbreAbstrait instructions;

    public Iterative(ArbreAbstrait instructions, Expression expressionConditionnelle, int n) {
        super(n);
        this.instructions = instructions;
        this.expressionConditionnelle = expressionConditionnelle;
    }

    @Override
    public String toMIPS() {
        int etiquette = TDS.getInstance().getEtiquetteCourante();
        return "\tuntil" + etiquette + ":\n" +
                instructions.toMIPS() + "\n" +
                expressionConditionnelle.toMIPS() + "\n" +
                "\tbeqz $v0, until" + etiquette + "\n";
    }

    @Override
    public void verifier() {
        if (this.expressionConditionnelle.isBool()) {
            this.expressionConditionnelle.verifier();
        } else {
            ListeErreurs.getInstance().ajouter(new Erreur("La condition doit être booléenne", noLigne));
        }
    }
}
