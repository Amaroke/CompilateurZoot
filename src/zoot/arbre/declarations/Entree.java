package zoot.arbre.declarations;

public class Entree {

    private int numeroBloc;
    private final String nom;
    private final String type;

    public Entree(int numeroBloc, String nom, String type) {
        this.numeroBloc = numeroBloc;
        this.nom = nom;
        this.type = type;
    }

    public int getNumeroBloc(){
        return numeroBloc;
    }

    public String getNom() {
        return nom;
    }

    public String getType() {
        return type;
    }
}
