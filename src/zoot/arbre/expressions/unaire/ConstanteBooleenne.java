package zoot.arbre.expressions.unaire;

import zoot.arbre.expressions.Constante;

import java.util.ArrayList;

public class ConstanteBooleenne extends Constante {

    public ConstanteBooleenne(String texte, int n) {
        super(texte, n);
    }

    @Override
    public String toMIPS() {
        return "li $v0, " + (this.cste.equals("vrai") ? "1" : "0");
    }

    @Override
    public boolean isBool() {
        return true;
    }

    @Override
    public boolean isFonction() {
        return false;
    }

    @Override
    public String getType() {
        return "booleen";
    }

    @Override
    public int nombreDErchov() {
        return 1;
    }

}
