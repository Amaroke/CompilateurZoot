package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;

import java.util.ArrayList;
import java.util.Arrays;

public class Ecrire extends Instruction {

    protected final Expression exp;

    public Ecrire(Expression e, int n) {
        super(n);
        exp = e;
    }

    @Override
    public void verifier() {
        this.exp.verifier();
    }

    @Override
    public String toMIPS() {
        ArrayList<String> registres = new ArrayList<>(Arrays.asList("$v0", "$t0", "$t1", "$t2"));
        StringBuilder str = new StringBuilder();
        str.append("   #ecrire ").append(exp.getNom()).append("\n");
        str.append("\t").append(exp.toMIPS()).append("\n");
        str.append("\tmove $a0, $v0\n");
        if (exp.isBool()) {
            str.append("\tbeq $zero, $a0, Sinon").append(exp.getNoLigne()).append("\n");
            str.append("\tla $a0, AffichageVrai\n");
            str.append("\tli $v0, 4\n");
            str.append("\tsyscall\n");
            str.append("\tb FinSi").append(exp.getNoLigne()).append("\n");
            str.append("\tSinon").append(exp.getNoLigne()).append(":").append("\n");
            str.append("\tla $a0, AffichageFaux\n");
            str.append("\tli $v0, 4\n");
            str.append("\tsyscall\n");
            str.append("\tFinSi").append(exp.getNoLigne()).append(":").append("\n");
        } else {
            str.append("\tli $v0, 1\n\tsyscall\n");
        }
        str.append("\tla $a0, saut_ligne\n\tli $v0, 4\n\tsyscall\n\n");
        return str.toString();
    }

}
