public class Main {
    public static void main(String[] args) {
        int capacite = 5; // Taille du tampon
        BAL bal = new BAL(capacite);

        producteur producteur = new producteur(bal);
        consommateur consommateur = new consommateur(bal);

        producteur.start();
        consommateur.start();

        try {
            producteur.join();
            consommateur.join();
        } catch (InterruptedException e) {
            System.out.println("Erreur lors de l'attente des threads.");
        }
    }
}
