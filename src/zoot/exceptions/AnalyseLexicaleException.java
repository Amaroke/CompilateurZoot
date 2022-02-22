package zoot.exceptions;

public class AnalyseLexicaleException extends AnalyseException {
 
    public AnalyseLexicaleException(int ligne, int colonne, String m) {
        super("ERREUR LEXICALE : ligne n°" + ligne + " colonne n°" + colonne + " : " + m + " caractère non reconnu.");
    }

}
