package zoot.exceptions;

public class RetourneHorsFonction extends AnalyseSemantiqueException {
    public RetourneHorsFonction(String m, int ligne) {
        super("ligne n°" + ligne + " : " + m);
    }
}
