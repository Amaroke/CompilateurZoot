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
    private int etiquetteCourante;

    private TDS() {
        this.etiquetteCourante = 0;
        this.blocCourant = 0;
        this.blocs = new ArrayList<>();
        this.blocs.add(new HashMap<>());
    }

    public static TDS getInstance() {
        return INSTANCE;
    }

    public int getEtiquetteCourante() {
        this.etiquetteCourante++;
        return this.etiquetteCourante-1;
    }

    public void ajouter(Entree e, Symbole symbole) throws DoubleDeclaration {
        for (Map.Entry<Entree, Symbole> m : this.blocs.get(blocCourant).entrySet()) {
            if (m.getKey().getNom().equals(e.getNom())) {
                throw new DoubleDeclaration("Le symbole : \"" + e.getNom() + "\" a été déclaré deux fois.");
            }
        }
        symbole.setDeplacement(this.getTailleZoneVariable());
        this.blocs.get(symbole.getNumBloc()).put(e, symbole);

    }

    public Symbole identifier(Entree e) throws VariableNonDeclaree {
        Symbole symbole = new Symbole("", 0, this.blocCourant);
        for (Map.Entry<Entree, Symbole> m : this.blocs.get(0).entrySet()) {
            if (m.getKey().getNom().equals(e.getNom()) && m.getKey().getType().equals(e.getType())) {
                symbole.setDeplacement(m.getValue().getDeplacement());
                symbole.setType(m.getValue().getType());
            }
        }
        for (Map.Entry<Entree, Symbole> m : this.blocs.get(blocCourant).entrySet()) {
            if (m.getKey().getNom().equals(e.getNom()) && m.getKey().getType().equals(e.getType())) {
                symbole.setDeplacement(m.getValue().getDeplacement());
                symbole.setType(m.getValue().getType());
            }
        }
        return symbole;
    }

    public SymboleFonction trouverFonction(String idf, int nbParam) {
        SymboleFonction symbole = new SymboleFonction(idf, this.blocCourant);
        for (Map.Entry<Entree, Symbole> m : this.blocs.get(0).entrySet()) {
            if (m.getKey().getNom().equals(idf) && (m.getValue().getNbParams() == nbParam)) {
                symbole.setDeplacement(m.getValue().getDeplacement());
                symbole.setType(m.getValue().getType());
                symbole.setNbParams(m.getValue().getNbParams());
            }
        }
        return symbole;
    }

    public int getTailleZoneVariable() {
        int taille = 0;
        for (HashMap<Entree, Symbole> bloc : this.blocs) {
            taille += bloc.size();
        }
        return taille * (-4);
    }

    public void entreeBloc() {
        if (blocCourant == 0) {
            this.blocs.add(new HashMap<>());
        }
        this.blocCourant = this.blocs.size() - 1;
    }

    public void sortieBloc() {
        this.blocCourant = 0;
    }

    public int getBlocCourant() {
        return this.blocCourant;
    }

    public void setBlocCourant(int blocCourant) {
        this.blocCourant = blocCourant;
    }

    public int getNbParam() {
        int nb = 0;
        for (Map.Entry<Entree, Symbole> m : this.blocs.get(blocCourant).entrySet()) {
            if (m.getValue().isParam()) {
                nb++;
            }
        }
        return nb;
    }

    public ArrayList<HashMap<Entree, Symbole>> getBlocs() {
        return blocs;
    }

    @Override
    public String toString() {
        return "TDS{" +
                "blocs=" + blocs +
                ", blocCourant=" + blocCourant +
                '}';
    }
}
