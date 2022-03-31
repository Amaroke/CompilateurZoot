package zoot.arbre.expressions.binaire;

import zoot.arbre.expressions.Expression;

import java.util.ArrayList;
import java.util.Arrays;

public class Different extends Binaire{
    public Different(Expression gauche, Expression droite, int n) {
        super(gauche, droite, n);
    }

    @Override
    public String toMIPS() {
        return "";
    }
}
