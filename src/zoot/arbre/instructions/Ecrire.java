package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;

public class Ecrire extends Instruction {

    protected Expression exp;

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
        StringBuilder str = new StringBuilder();
        System.out.println(exp.isBool());
        if (exp.isBool()) {
            str.append("\tlw $t0, ").append(exp.toMIPS()).append("\n");
            str.append("\tbeq $s1, $t0, Sinon").append(exp.getNoLigne()).append("\n");
            str.append("\tla $a0, AffichageVrai\n");
            str.append("\tli $v0, 4\n\tsyscall" + "\n");
            str.append("\tb FinSi").append(exp.getNoLigne()).append("\n");
            str.append("\tSinon").append(exp.getNoLigne()).append(":").append("\n");
            str.append("\tla $a0, AffichageFaux\n");
            str.append("\tli $v0, 4\n\tsyscall" + "\n");
            str.append("\tFinSi").append(exp.getNoLigne()).append(":").append("\n");
        } else if (!exp.isIdf()) {

            str.append("\tli $a0, ").append(exp.toMIPS()).append("\n");
            str.append("\tli $v0, 1\n\tsyscall\n");
        } else {
            str.append("\tlw $v0, ")
                    .append(exp.toMIPS())
                    .append("\tmove $a0, $v0\n")
                    .append("\tli $v0, 1\n")
                    .append("\tsyscall\n");
        }
        str.append("\tla $a0, saut_ligne\n\tli $v0, 4\n\tsyscall\n\n");
        return str.toString();
    }
}
