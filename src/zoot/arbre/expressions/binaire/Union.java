package zoot.arbre.expressions.binaire;

import zoot.arbre.expressions.Expression;

import java.util.ArrayList;
import java.util.Arrays;

public class Union extends Binaire{

    public Union(Expression gauche, Expression droite, int n) {
        super(gauche, droite, n);
    }


    @Override
    public String toMIPS() {
        return "";
    }
}
