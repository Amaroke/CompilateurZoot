package zoot.arbre.expressions;

import zoot.arbre.declarations.Symbole;
import zoot.arbre.declarations.TDS;
import zoot.exceptions.VariableNonDeclaree;

public class Idf extends Expression {

    private String nom;
    private Symbole symbole;

    public Idf(String nom, int n) {
        super(n) ;
        this.nom = nom;
        this.noLigne = n;
    }

    @Override
    public void verifier() throws VariableNonDeclaree {
            this.symbole = TDS.getInstance().identifier(this.nom);
        }

    @Override
    public String toMIPS() {
        StringBuilder s = new StringBuilder();
        s.append("\tlw $v0, ")
                .append(TDS.getInstance().identifier(nom).getDeplacement() + ("($s7)\n"))
                .append("\tmove $a0, $v0\n")
                .append("\tli $v0, 1\n")
                .append("\tsyscall\n");

        return s.toString();

    }

    public String getNom() {
        return nom;
    }

    public String getType(){
        return this.symbole.getType();
    }

    public Symbole getSymbole(){
        return this.symbole;
    }


    @Override
    public boolean isConstante() {
        return false;
    }
}
