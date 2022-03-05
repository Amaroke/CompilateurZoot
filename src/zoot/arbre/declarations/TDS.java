package zoot.arbre.declarations;

import zoot.exceptions.DoubleDeclaration;
import zoot.exceptions.VariableNonDeclaree;

import java.util.HashMap;
import java.util.Map;

public class TDS {

    private static final TDS INSTANCE = new TDS();
    private final HashMap<String, Symbole> variables;

    private TDS() {
        this.variables = new HashMap<>();
    }

    public static TDS getInstance() {
        return INSTANCE;
    }

    public void ajouter(Entree e, Symbole symbole) throws DoubleDeclaration {
        if (this.variables.containsKey(e.getNom())) {
            throw new DoubleDeclaration("Le symbole : \"" + e.getNom() + "\" a été déclaré deux fois.", symbole.getNoLigne());
        }
        symbole.setDeplacement(this.getTailleZoneVariable());
        this.variables.put(e.getNom(), symbole);
    }

    public Symbole identifier(Entree e) throws VariableNonDeclaree {
        Symbole symbole = new Symbole(0, "");
        for (Map.Entry<String, Symbole> m : this.variables.entrySet()) {
            if (m.getKey().equals(e.getNom())) {
                symbole.setDeplacement(m.getValue().getDeplacement());
                symbole.setType(m.getValue().getType());
            }
        }
        if (!symbole.getType().equals("entier") && !symbole.getType().equals("booleen") && !symbole.getType().equals("fonction")) {
            throw new VariableNonDeclaree("Problème dans TDS.");
        }
        return symbole;
    }

    public int getTailleZoneVariable() {
        return this.variables.size() * (-4);
    }

}
