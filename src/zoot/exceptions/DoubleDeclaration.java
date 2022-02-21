package zoot.exceptions;

public class DoubleDeclaration extends AnalyseSemantiqueException {
    public DoubleDeclaration(String m) {
        super("DOUBLE DECLARATION : " + m);
    }
}
