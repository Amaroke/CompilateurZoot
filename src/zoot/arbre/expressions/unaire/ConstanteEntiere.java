package zoot.arbre.expressions.unaire;

import zoot.arbre.expressions.Constante;

import java.util.ArrayList;

public class ConstanteEntiere extends Constante {

    public ConstanteEntiere(String texte, int n) {
        super(texte, n);
    }

    @Override
    public String toMIPS() {
        return "li $v0, " + this.cste;
    }

    @Override
    public boolean isBool() {
        return false;
    }

    @Override
    public boolean isFonction() {
        return false;
    }

    @Override
    public String getType() {
        return "entier";
    }

    @Override
    public int nombreDErchov() {
        return 1;
    }
}
