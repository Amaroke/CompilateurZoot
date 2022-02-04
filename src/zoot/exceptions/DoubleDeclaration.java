package zoot.exceptions;

public class DoubleDeclaration extends AnalyseSemantiqueException{
    protected DoubleDeclaration(String m) {
        super("DOUBLE DECLARATION : " + m);
    }
}
