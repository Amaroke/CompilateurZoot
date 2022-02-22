package zoot.exceptions;

public class AnalyseSemantiqueException extends AnalyseException{

    protected AnalyseSemantiqueException(String m) {
        super("ERREUR SÉMANTIQUE : " + m);
    }
}
