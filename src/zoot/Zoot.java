package zoot;

import zoot.analyse.AnalyseurLexical;
import zoot.analyse.AnalyseurSyntaxique;
import zoot.arbre.ArbreAbstrait;
import zoot.arbre.declarations.ListeFonctions;
import zoot.exceptions.AnalyseException;
import zoot.exceptions.Erreur;
import zoot.exceptions.ListeErreurs;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Zoot {

    public Zoot(String nomFichier) {
        try {
            AnalyseurSyntaxique analyseur = new AnalyseurSyntaxique(new AnalyseurLexical(new FileReader(nomFichier)));
            ArbreAbstrait arbre = (ArbreAbstrait) analyseur.parse().value;
            arbre.verifier();
            // Je suis obligé de mettre le compteur à 1 pour le test, autrement les instructions retounre seront considérés comme hors de la fonction.
            ListeFonctions.getInstance().setCpt(1);
            ListeFonctions.getInstance().verifier();
            ListeFonctions.getInstance().setCpt(0);
            if (ListeErreurs.getInstance().getNbErreurs() == 0) {
                System.out.println("COMPILATION OK");
                String nomSortie = nomFichier.replaceAll("[.]zoot", ".mips");
                PrintWriter flot = new PrintWriter(new BufferedWriter(new FileWriter(nomSortie)));
                flot.println(arbre.toMIPS());
                flot.close();
            } else {
                for (Erreur e : ListeErreurs.getInstance().getErreurs()) {
                    System.err.println("ERREUR SEMANTIQUE : Ligne n°" + e.getLigne() + " : " + e.getMessage());
                }
            }
        }
        catch (FileNotFoundException ex) {
            System.err.println("Fichier " + nomFichier + " inexistant") ;
        }
        catch (AnalyseException ex) {
            System.err.println(ex.getMessage());
        }
        catch (Exception ex) {
            Logger.getLogger(Zoot.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Nombre incorrect d'arguments") ;
            System.err.println("\tjava -jar zoot.jar <fichierSource.zoot>") ;
            System.exit(1) ;
        }
        new Zoot(args[0]) ;
    }

}
