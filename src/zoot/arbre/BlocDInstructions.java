package zoot.arbre;

import zoot.arbre.declarations.TDS;
import zoot.exceptions.ListeErreurs;

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
        for(ArbreAbstrait a : programme){
            a.verifier();
        }
    }
    
    @Override
    public String toMIPS() {
        StringBuilder str = new StringBuilder();
        str.append("#MATHIEU STEINBACH Hugo & MOSELLE Marie-Luc\n\n");
        str.append(".data\n\n");
        str.append("vrai: .word 1\n");
        str.append("faux: .word 0\n");
        str.append("AffichageVrai: .asciiz \"vrai\"\n");
        str.append("AffichageFaux: .asciiz \"faux\"\n");
        str.append("AffichageErreur: .asciiz \"ERREUR EXECUTION\"\n");
        str.append("saut_ligne: .asciiz \"\\n\"\n\n");
        str.append(".text\n\n");
        str.append("main: \n");
        str.append("   #Initialisation de la base des variables :\n");
        str.append("\tmove $s7, $sp\n");
        str.append("\taddi $sp, $sp, ").append(TDS.getInstance().getTailleZoneVariable()).append("\n\n");
        str.append("   #Debut du programme :\n\n");
        for (ArbreAbstrait a : programme) {
            if (ListeErreurs.getInstance().getNbErreurs() != 0 && ListeErreurs.getInstance().getErreur(0).getLigne() == a.getNoLigne()) {
                str.append("   #Affichage de l'erreur d'execution\n");
                str.append("\tla $a0, AffichageErreur\n");
                str.append("\tli $v0, 4\n");
                str.append("\tsyscall\n");
                str.append("\tb end\n\n");
            }
            str.append(a.toMIPS());
        }
        str.append("   #Fin du programme :\n\tend:\n\tli $v0, 10\n\tsyscall");
        return str.toString();
    }

    @Override
    public String toString() {
        return programme.toString() ;
    }

}
