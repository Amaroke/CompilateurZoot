package zoot;

import zoot.analyse.AnalyseurLexical;
import zoot.analyse.AnalyseurSyntaxique;
import zoot.arbre.ArbreAbstrait;
import zoot.arbre.declarations.ListeFonctions;
import zoot.exceptions.AnalyseException;
import zoot.exceptions.Erreur;
import zoot.exceptions.ListeErreurs;
import zoot.exceptions.RetourneHorsFonction;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Zoot {
    
    public Zoot(String nomFichier) {
        boolean compilationOK = true;
        try {
            AnalyseurSyntaxique analyseur = new AnalyseurSyntaxique(new AnalyseurLexical(new FileReader(nomFichier)));
            ArbreAbstrait arbre = (ArbreAbstrait) analyseur.parse().value;

            arbre.verifier();
            ListeFonctions.getInstance().verifier();
            if (ListeErreurs.getInstance().getNbErreurs() == 0) {
                System.out.println("COMPILATION OK");
            } else {
                for (Erreur e : ListeErreurs.getInstance().getErreurs()) {
                    System.err.println("ERREUR SEMANTIQUE : Ligne n°" + e.getLigne() + " : " + e.getMessage());
                }
            }
            String nomSortie = nomFichier.replaceAll("[.]zoot", ".mips");
            PrintWriter flot = new PrintWriter(new BufferedWriter(new FileWriter(nomSortie)));
            flot.println(arbre.toMIPS());
            flot.close();
        } catch (RetourneHorsFonction r) {
            compilationOK = false;
            System.err.println(r.getMessage());
        } catch (FileNotFoundException ex) {
            System.err.println("Fichier " + nomFichier + " inexistant");
        } catch (AnalyseException ex) {
            System.err.println(ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(Zoot.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(compilationOK ? "COMPILATION OK" : "");
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
