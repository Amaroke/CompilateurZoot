package zoot.arbre;

import zoot.arbre.instructions.Instruction;

import java.util.ArrayList;

/**
 * 21 novembre 2018
 *
 * @author brigitte wrobel-dautcourt
 */

public class BlocDInstructions extends ArbreAbstrait {
    
    protected ArrayList<ArbreAbstrait> programme ;

    public BlocDInstructions(int n) {
        super(n) ;
        programme = new ArrayList<>() ;
    }
    
    public void ajouter(ArbreAbstrait a) {
        programme.add(a) ;
    }

    @Override
    public void verifier() {
        throw new UnsupportedOperationException("fonction verifier non définie ") ;
    }
    
    @Override
    public String toMIPS() {
        StringBuilder str = new StringBuilder();
        str.append("#MATHIEU STEINBACH Hugo & MOSELLE Marie-Luc\n");
        str.append(".data\n");
        str.append("saut_ligne: .asciiz \"\\n\"\n");
        str.append(".text\n");
        str.append("main: \n");
        for (ArbreAbstrait a: programme) {
            System.out.println(a);
            str.append(a.toMIPS());
        }
        str.append("#Fin du programme :\nli $v0, 10\nsyscall");
        return str.toString();
    }

    @Override
    public String toString() {
        return programme.toString() ;
    }

}
