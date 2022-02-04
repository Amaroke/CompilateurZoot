package zoot.exceptions;

public class VariableNonDeclaree extends AnalyseSemantiqueException{

    public VariableNonDeclaree(String m) {
        super("VARIABLE NON DECLAREE : " + m);
    }
}
