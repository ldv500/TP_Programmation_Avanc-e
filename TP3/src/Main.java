public class Main {
    public static void main(String[] args) {
        BAL bal = new BAL();
        producteur producteur = new producteur(bal);
        Consommateur consommateur = new Consommateur(bal);
    }
}