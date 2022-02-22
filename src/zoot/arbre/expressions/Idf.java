package zoot.arbre.expressions;

import zoot.arbre.declarations.Symbole;
import zoot.arbre.declarations.TDS;
import zoot.exceptions.Erreur;
import zoot.exceptions.ListeErreurs;
import zoot.exceptions.VariableNonDeclaree;

public class Idf extends Expression {

    private final String nom;
    private Symbole symbole;

    public Idf(String nom, int n) {
        super(n) ;
        this.nom = nom;
        this.noLigne = n;
    }

    @Override
    public void verifier() throws VariableNonDeclaree {
        try {
            this.symbole = TDS.getInstance().identifier(this.nom);
        } catch (VariableNonDeclaree m) {
            ListeErreurs.getInstance().ajouter(new Erreur("Le symbole : " + nom + " n'a pas été déclaré.", this.noLigne));
        }
    }

    @Override
    public String toMIPS() {
        try {
            return (TDS.getInstance().identifier(nom).getDeplacement() + ("($s7)\n"));
        } catch (VariableNonDeclaree m) {
            ListeErreurs.getInstance().ajouter(new Erreur(m.getMessage(), this.noLigne));
        }
        return ("0($s7)\n");
    }

    @Override
    public boolean isIdf() {
        return true;
    }

    @Override
    public boolean isBool() {
        return (symbole.getType().equals("booleen"));
    }

    @Override
    public String getType() throws VariableNonDeclaree {
        return TDS.getInstance().identifier(nom).getType();
    }

    @Override
    public String getNom() {
        return this.nom;
    }

}
