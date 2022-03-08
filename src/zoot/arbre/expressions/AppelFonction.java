package zoot.arbre.expressions;

public class AppelFonction extends Expression {

    private final Idf idf;

    public AppelFonction(Idf idf, int n) {
        super(n);
        this.idf = idf;
    }


    @Override
    public void verifier() {
        idf.verifier();
    }


    @Override
    public String toMIPS() {
        StringBuilder str = new StringBuilder();
        str.append("   #Sauvegarde des registres\n");
        str.append("\tsw $ra,0($sp)\n");
        str.append("\tsw $s1,-4($sp)\n");
        str.append("\taddi $sp,$sp,-8\n");
        str.append("   #Appel de la fonction\n");
        str.append("\tjal " + idf.getNom() + "\n");
        str.append("   #Restauration des registres\n");
        str.append("\tlw $s1,4($sp)\n");
        str.append("\tlw $ra,8($sp)\n");
        str.append("\taddi $sp,$sp,8\n");
        return str.toString();
    }

    @Override
    public boolean isIdf() {
        return false;
    }

    @Override
    public boolean isBool() {
        return false;
    }

    @Override
    public boolean isFonction() {
        return true;
    }

    @Override
    public String getType() {
        return idf.getType();
    }

    @Override
    public String getNom() {
        return this.idf.getNom();
    }
}
