package zoot.exceptions;

import java.util.ArrayList;

public class ListeErreurs {

    private static final ListeErreurs INSTANCE = new ListeErreurs();
    private final ArrayList<Erreur> erreurs;

    private ListeErreurs() {
        this.erreurs = new ArrayList<>();
    }

    public static ListeErreurs getInstance() {
        return INSTANCE;
    }

    public void ajouter(Erreur e) {
        this.erreurs.add(e);
    }

    public int getNbErreurs() {
        return this.erreurs.size();
    }

    public ArrayList<Erreur> getErreurs() {
        return erreurs;
    }
}
