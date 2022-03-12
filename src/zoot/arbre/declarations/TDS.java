package zoot.arbre.declarations;

import zoot.exceptions.DoubleDeclaration;
import zoot.exceptions.VariableNonDeclaree;

import java.util.HashMap;
import java.util.Map;

public class TDS {

    private static final TDS INSTANCE = new TDS();
    private final HashMap<Entree, Symbole> variables;

    private int bloc;

    private TDS() {
        this.variables = new HashMap<>();
        this.bloc = 0;
    }

    public static TDS getInstance() {
        return INSTANCE;
    }

    public void ajouter(Entree e, Symbole symbole) throws DoubleDeclaration {
        for (Map.Entry<Entree, Symbole> m : this.variables.entrySet()) {
            if (m.getKey().getNom().equals(e.getNom())) {
                throw new DoubleDeclaration("Le symbole : \"" + e.getNom() + "\" a été déclaré deux fois.");
            }
        }
        symbole.setDeplacement(this.getTailleZoneVariable());
        this.variables.put(e, symbole);
    }

    public Symbole identifier(Entree e) throws VariableNonDeclaree {
        Symbole symbole = new Symbole(0, "");
        for (Map.Entry<Entree, Symbole> m : this.variables.entrySet()) {
            if (m.getKey().getNom().equals(e.getNom()) && m.getKey().getType().equals(e.getType())) {
                symbole.setDeplacement(m.getValue().getDeplacement());
                symbole.setType(m.getValue().getType());
            }
        }
        if (!symbole.getType().equals("entier") && !symbole.getType().equals("booleen")) {
            throw new VariableNonDeclaree("Problème dans TDS.");
        }
        return symbole;
    }

    public int getTailleZoneVariable() {
        return this.variables.size() * (-4);
    }

    public void entreeBloc(){
        this.bloc++;
    }

    public void sortieBloc(){
        this.bloc--;
    }

    public int getBloc(){
        return this.bloc;
    }
}
