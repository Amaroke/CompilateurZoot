package zoot.arbre.expressions;

import zoot.arbre.declarations.Entree;
import zoot.arbre.declarations.Symbole;
import zoot.arbre.declarations.TDS;
import zoot.exceptions.Erreur;
import zoot.exceptions.ListeErreurs;
import zoot.exceptions.VariableNonDeclaree;

public class Idf extends Expression {

    private final Entree entree;
    private Symbole symbole;

    public Idf(String nom, int n) {
        super(n) ;
        this.entree = new Entree(nom);
        this.noLigne = n;
    }

    @Override
    public void verifier() throws VariableNonDeclaree {
        try {
            this.symbole = TDS.getInstance().identifier(this.entree);
        } catch (VariableNonDeclaree m) {
            ListeErreurs.getInstance().ajouter(new Erreur("Le symbole : " + this.entree.getNom() + " n'a pas été déclaré.", this.noLigne));
        }
    }

    @Override
    public String toMIPS() {
        try {
            return (TDS.getInstance().identifier(this.entree).getDeplacement() + ("($s7)\n"));
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
        try {
            return (symbole.getType().equals("booleen"));
        } catch (NullPointerException n) {
            return false;
        }
    }

    @Override
    public boolean isFonction() {
        return false;
    }

    @Override
    public String getType() throws VariableNonDeclaree {
        return TDS.getInstance().identifier(this.entree).getType();
    }

    @Override
    public String getNom() {
        return this.entree.getNom();
    }

}
