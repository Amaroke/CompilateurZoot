package zoot.arbre.declarations;

public class Entree {

    private final String nom;
    private final String type;

    public Entree(String nom, String type) {
        this.nom = nom;
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Entree{" + "nom='" + nom + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
