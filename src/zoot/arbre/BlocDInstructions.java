package zoot.arbre;

import zoot.arbre.declarations.Fonction;
import zoot.arbre.declarations.ListeFonctions;
import zoot.arbre.declarations.TDS;
import zoot.exceptions.ListeErreurs;
import zoot.exceptions.RetourneHorsFonction;

import java.util.ArrayList;

/**
 * 21 novembre 2018
 *
 * @author brigitte wrobel-dautcourt
 */

public class BlocDInstructions extends ArbreAbstrait {
    
    protected final ArrayList<ArbreAbstrait> programme;

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
        if (ListeFonctions.getInstance().getCpt() == 0) {
            ListeFonctions.getInstance().setCpt(1);
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
            str.append("   #Initialisation de $s1 avec la valeur faux :\n");
            str.append("\tla $s1, faux\n\n");
            str.append("   #Debut du programme :\n\n");
            for (ArbreAbstrait a : programme) {
                if (a.isRetourne() || ListeErreurs.getInstance().getNbErreurs() != 0 && ListeErreurs.getInstance().getErreur(0).getLigne() == a.getNoLigne()) {
                    str.append("   #Affichage de l'erreur d'execution\n");
                    str.append("\tla $a0, AffichageErreur\n");
                    str.append("\tli $v0, 4\n");
                    str.append("\tsyscall\n");
                    str.append("\tb end\n\n");
                }
                if (a.isRetourne()) {
                    throw new RetourneHorsFonction("L'instruction retourne ne peut pas être en dehors d'une fonction", getNoLigne());
                } else {
                    str.append(a.toMIPS());
                }

            }
            str.append("\tb end\n\n");
            for (Fonction f : ListeFonctions.getInstance().getFonctions()) {
                str.append(f.toMIPS());
            }
            str.append("   #Fin du programme :\n\tend:\n\tli $v0, 10\n\tsyscall");
        } else {
            for (ArbreAbstrait a : programme) {
                str.append(a.toMIPS());
            }
        }
        return str.toString();
    }

    @Override
    protected boolean isRetourne() {
        return false;
    }

    @Override
    public String toString() {
        return programme.toString();
    }

}
