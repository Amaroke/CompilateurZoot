package zoot.exceptions;

public class AnalyseLexicaleSemantiqueException extends AnalyseException {

    public AnalyseLexicaleSemantiqueException(String m) {
        super("ERREUR LEXICALE SEMANTIQUE : " + m);
    }

}
