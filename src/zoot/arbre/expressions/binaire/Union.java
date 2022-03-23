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
        ArrayList<String> registres = new ArrayList<>(Arrays.asList("$v0", "$t0", "$t1", "$t2"));
        return this.toMIPS(registres);
    }
}
