package zoot.exceptions;

public class DoubleDeclaration extends AnalyseSemantiqueException {
    public DoubleDeclaration(String m, int ligne) {
        super("ligne : " + ligne + " " + m);
    }
}
