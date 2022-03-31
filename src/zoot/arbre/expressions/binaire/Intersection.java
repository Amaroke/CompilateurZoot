package zoot.arbre.expressions.binaire;

import zoot.arbre.expressions.Expression;

import java.util.ArrayList;
import java.util.Arrays;

public class Intersection extends Binaire{

    public Intersection(Expression gauche, Expression droite, int n) {
        super(gauche, droite, n);
    }


    @Override
    public String toMIPS() {
        return "";
    }
}
