package zoot.arbre.declarations;

import zoot.arbre.expressions.Idf;
import zoot.exceptions.DoubleDeclaration;
import zoot.exceptions.VariableNonDeclaree;

import java.util.HashMap;
import java.util.Map;

public class TDS {

    private static TDS INSTANCE = new TDS();
    private HashMap<String, Symbole> variables;

    private TDS() {
        this.variables = new HashMap<>();
    }

    public static TDS getInstance(){
        return INSTANCE;
    }

    public void ajouter(String idf, Symbole symbole) throws DoubleDeclaration {
        if(this.variables.containsKey(idf)){
            throw new DoubleDeclaration("Le symbole" + idf + "a été déclaré deux fois");
        }
        symbole.setDeplacement(this.getTailleZoneVariable());
        this.variables.put(idf, symbole);
    }

    public Symbole identifier(String nom) throws VariableNonDeclaree {
        Symbole symbole = new Symbole(0, "");
        for (Map.Entry<String, Symbole> m : this.variables.entrySet()){
            if(m.getKey().equals(nom)) {
                symbole.setDeplacement(m.getValue().getDeplacement());
                symbole.setType(m.getValue().getType());
            }

        }
        if(!symbole.getType().equals("entier") && !symbole.getType().equals("booleen")){
            throw new VariableNonDeclaree(nom);
        }
        return symbole;
    }

    public int getTailleZoneVariable(){
        return this.variables.size() * (-4);
    }

}
