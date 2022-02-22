package zoot.exceptions;

public class Erreur {

    private final String message;
    private final int ligne;

    public Erreur(String m, int ligne) {
        this.message = m;
        this.ligne = ligne;
    }

    public String getMessage() {
        return message;
    }

    public int getLigne() {
        return ligne;
    }
}
