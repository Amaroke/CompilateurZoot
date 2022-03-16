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
        return "   #Sauvegarde des registres\n" +
                "\tsw $ra,0($sp)\n" +
                "\tsw $s1,-4($sp)\n" +
                "\taddi $sp,$sp,-8\n" +
                "   #Appel de la fonction\n" +
                "\tjal " + idf.getNom() + "\n" +
                "   #Restauration des registres\n" +
                "\tlw $s1,4($sp)\n" +
                "\tlw $ra,8($sp)\n" +
                "\taddi $sp,$sp,8\n";
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
        return idf.getType();
    }

    @Override
    public String getNom() {
        return this.idf.getNom();
    }
}
