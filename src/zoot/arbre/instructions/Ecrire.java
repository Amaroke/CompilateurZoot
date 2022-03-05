package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;

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
        StringBuilder str = new StringBuilder();
        str.append("   #ecrire ").append(exp.getNom()).append("\n");
        if(exp.isFonction()){
           str.append(exp.toMIPS());
            str.append("\tmove $a0, $v0\n");
            if(exp.getType().equals("booleen")){
                    str.append("\tbeq $s1, $t0, Sinon").append(exp.getNoLigne()).append("\n");
                    str.append("\tla $a0, AffichageVrai\n");
                    str.append("\tli $v0, 4\n\tsyscall" + "\n");
                    str.append("\tb FinSi").append(exp.getNoLigne()).append("\n");
                    str.append("\tSinon").append(exp.getNoLigne()).append(":").append("\n");
                    str.append("\tla $a0, AffichageFaux\n");
                    str.append("\tli $v0, 4\n\tsyscall" + "\n");
                    str.append("\tFinSi").append(exp.getNoLigne()).append(":").append("\n");
            }else{
                str.append("\tli $v0, 1\n\tsyscall\n");
            }
        }
        else {
            if (exp.isBool()) {
                if (exp.isIdf()) {
                    str.append("\tlw $t0, ").append(exp.toMIPS()).append("\n");
                    str.append("\tbeq $s1, $t0, Sinon").append(exp.getNoLigne()).append("\n");
                    str.append("\tla $a0, AffichageVrai\n");
                    str.append("\tli $v0, 4\n\tsyscall" + "\n");
                    str.append("\tb FinSi").append(exp.getNoLigne()).append("\n");
                    str.append("\tSinon").append(exp.getNoLigne()).append(":").append("\n");
                    str.append("\tla $a0, AffichageFaux\n");
                    str.append("\tli $v0, 4\n\tsyscall" + "\n");
                    str.append("\tFinSi").append(exp.getNoLigne()).append(":").append("\n");
                } else {
                    str.append("\tla $a0,");
                    if (exp.getNom().equals("vrai")) {
                        str.append(" AffichageVrai\n");
                    } else {
                        str.append(" AffichageFaux\n");
                    }
                    str.append("\tli $v0, 4\n\tsyscall" + "\n");
                }
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
        }
        str.append("\tla $a0, saut_ligne\n\tli $v0, 4\n\tsyscall\n\n");

        return str.toString();
    }
}
