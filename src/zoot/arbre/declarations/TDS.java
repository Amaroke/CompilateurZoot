package zoot.arbre.declarations;


import zoot.exceptions.DoubleDeclaration;
import zoot.exceptions.VariableNonDeclaree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TDS {

    private static final TDS INSTANCE = new TDS();
    private final ArrayList<HashMap<Entree, Symbole>> blocs;

    private int blocCourant;
    private int nbBlocs;

    private TDS() {
        this.blocCourant = 0;
        this.blocs = new ArrayList<>();
        this.nbBlocs = 0;
    }

    public static TDS getInstance() {
        return INSTANCE;
    }

    public void ajouter(Entree e, Symbole symbole) throws DoubleDeclaration {
        if (this.blocs.size() > this.blocCourant){
            for (Map.Entry<Entree, Symbole> m : this.blocs.get(blocCourant).entrySet()) {
                if (m.getKey().getNom().equals(e.getNom())) {
                    throw new DoubleDeclaration("Le symbole : \"" + e.getNom() + "\" a été déclaré deux fois.");
                }
            }
        }else {
            this.blocs.add(new HashMap<>());
        }
        symbole.setDeplacement(this.getTailleZoneVariable());
        this.blocs.get(blocCourant).put(e, symbole);
    }

    public Symbole identifier(Entree e) throws VariableNonDeclaree {
        System.out.println(e);
        Symbole symbole = new Symbole("type", 0, this.blocCourant);
        for (Map.Entry<Entree, Symbole> m : this.blocs.get(blocCourant).entrySet()) {
            if (m.getKey().getNom().equals(e.getNom()) && m.getKey().getType().equals(e.getType())) {
                symbole.setDeplacement(m.getValue().getDeplacement());
                symbole.setType(m.getValue().getType());
            }
        }
        if (!symbole.getType().equals("entier") && !symbole.getType().equals("booleen")) {
            throw new VariableNonDeclaree("Problème dans TDS, un type non reconnu s'y trouve.");
        }
        return symbole;
    }

    public int getTailleZoneVariable() {
        return this.blocs.get(blocCourant).size() * (-4);
    }

    public void entreeBloc(){
        this.blocCourant++;
    }

    public void sortieBloc(){
        this.blocCourant--;
    }

    public int getBlocCourant(){
        return this.blocCourant;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TDS{");
        sb.append("blocs=").append(blocs);
        sb.append(", blocCourant=").append(blocCourant);
        sb.append(", nbBlocs=").append(nbBlocs);
        sb.append('}');
        return sb.toString();
    }
}
