package zoot.arbre.instructions;

import zoot.arbre.ArbreAbstrait;
import zoot.arbre.declarations.TDS;
import zoot.arbre.expressions.Expression;
import zoot.exceptions.Erreur;
import zoot.exceptions.ListeErreurs;

public class Conditionnelle extends Instruction{

    private final Expression expressionConditionelle;
    private final ArbreAbstrait instructionSi;
    private final ArbreAbstrait instructionAlors;

    public Conditionnelle(Expression expression, int n, ArbreAbstrait instructionSi, ArbreAbstrait instructionAlors) {
        super(n);
        this.expressionConditionelle = expression;
        this.instructionSi = instructionSi;
        this.instructionAlors = instructionAlors;
    }

    @Override
    public String toMIPS() {
        int etiquette = TDS.getInstance().getEtiquetteCourante();
        StringBuilder str = new StringBuilder();
        str.append("   #Condition\n").append(this.expressionConditionelle.toMIPS()).append("\n");
        str.append("\tli $t8, 1\n");
        str.append("\tbeq $v0,$t8,si").append(etiquette).append("\n");
        if(instructionAlors != null){
            str.append(instructionAlors.toMIPS()).append("\n");
        }
        str.append("\tj go").append(etiquette).append("\n");
        str.append("\tsi").append(etiquette).append(":\n");
        if(instructionSi != null){
            str.append(instructionSi.toMIPS()).append("\n");
        }
        str.append("\tj go").append(etiquette).append("\n");
        str.append("\tgo").append(etiquette).append(":\n");
        return str.toString();
    }

    @Override
    public void verifier() {
        if(expressionConditionelle.isBool()) {
            expressionConditionelle.verifier();
            if(instructionSi != null){
                instructionSi.verifier();
            }
            if(instructionAlors != null){
                instructionAlors.verifier();
            }
        }else{
            ListeErreurs.getInstance().ajouter(new Erreur("La condition doit être booléenne", noLigne));
        }
    }
}
