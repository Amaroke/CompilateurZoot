package zoot.arbre.expressions;

import zoot.arbre.declarations.Fonction;
import zoot.arbre.declarations.ListeFonctions;
import zoot.arbre.declarations.SymboleFonction;
import zoot.arbre.declarations.TDS;
import zoot.exceptions.Erreur;
import zoot.exceptions.ListeErreurs;

import java.util.ArrayList;

public class AppelFonction extends Expression {

    private final Idf idf;
    private final ArrayList<Expression> parametresEffectifs;
    private final int nbParam;

    public AppelFonction(Idf idf, int n) {
        super(n);
        this.idf = idf;
        this.parametresEffectifs = new ArrayList<>(ListeFonctions.getInstance().getParametresEffectifs());
        this.nbParam = parametresEffectifs.size();
        ListeFonctions.getInstance().viderParametresEffectifs();
    }

    @Override
    public void verifier() {
        for (Fonction f : ListeFonctions.getInstance().getFonctions()) {
            SymboleFonction s = TDS.getInstance().trouverFonction(f.getIdf(), f.getNbParam());
            if (s.getNbParams() == this.nbParam) {
                for (int i = 0; i < f.getNbParam(); i++) {
                    if (!this.parametresEffectifs.get(i).getType().equals(f.getTypeParam(i))) {
                        ListeErreurs.getInstance().ajouter(new Erreur("Mauvais type de paramètre fourni à " + idf.getNom(), noLigne));
                    }
                }
            }
        }
        if (this.nbParam != 0) {
            boolean bonNombreParam = false;
            for (Fonction f : ListeFonctions.getInstance().getFonctions()) {
                if (f.getNbParam() == this.nbParam && f.getIdf().equals(this.idf.getNom())) {
                    bonNombreParam = true;
                }
            }
            if (!bonNombreParam) {
                ListeErreurs.getInstance().ajouter(new Erreur("Mauvais nombre de paramètres donné à " + idf.getNom(), noLigne));
            }
        }
        idf.verifier();
    }

    @Override
    public String toMIPS() {
        StringBuilder str = new StringBuilder();
        str.append("   #Sauvegarde des registres\n");
        str.append("\tsw $ra,0($sp)\n");
        str.append("\tsw $s1,-4($sp)\n");
        str.append("\taddi $sp,$sp,-8\n");
        str.append("   #Empilage des paramètres\n");
        for (int i = nbParam - 1; i >= 0; i--) {
            str.append("\t").append(parametresEffectifs.get(i).toMIPS());
            str.append("\n\tsw $v0, ").append(i * (-4)).append("($sp)\n");
        }
        str.append("\taddi $sp, $sp, ").append(nbParam * (-4)).append("\n");

        str.append("   #Appel de la fonction\n" + "\tjal ").append(idf.getNom()).append(nbParam).append("\n").append("   #Restauration des registres\n").append("\tlw $s1,4($sp)\n").append("\tlw $ra,8($sp)\n").append("\taddi $sp,$sp,8\n");
        return str.toString();
    }

    @Override
    public boolean isIdf() {
        return false;
    }

    @Override
    public boolean isBool() {
        return this.getType().equals("booleen");
    }

    @Override
    public boolean isFonction() {
        return true;
    }

    @Override
    public String getType() {
        return TDS.getInstance().trouverFonction(idf.getNom(), nbParam).getType();
    }

    @Override
    public String getNom() {
        return this.idf.getNom();
    }
}
