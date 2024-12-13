public class BAL {
    private String[] tampon; // Tableau pour stocker les lettres
    private int tete;        // Indice de la première lettre
    private int queue;       // Indice de la prochaine position disponible
    private int nbLettres;   // Nombre actuel de lettres dans le tampon
    private int capacite;    // Capacité maximale du tampon

    public BAL(int capacite) {
        this.capacite = capacite;
        tampon = new String[capacite];
        tete = 0;
        queue = 0;
        nbLettres = 0;
    }

    public synchronized void deposer(String lettre) {
        try {
            while (nbLettres == capacite) { // Si le tampon est plein, attendre
                wait();
            }
            tampon[queue] = lettre;
            queue = (queue + 1) % capacite; // Avancer dans le tampon circulaire
            nbLettres++;
            System.out.println("Lettre déposée : " + lettre);
            notifyAll(); // Notifier les consommateurs en attente
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Erreur dans deposer.");
        }
    }

    public synchronized String retirer() {
        String lettre = null;
        try {
            while (nbLettres == 0) { // Si le tampon est vide, attendre
                wait();
            }
            lettre = tampon[tete];
            tete = (tete + 1) % capacite; // Avancer dans le tampon circulaire
            nbLettres--;
            System.out.println("Lettre retirée : " + lettre);
            notifyAll(); // Notifier les producteurs en attente
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Erreur dans retirer.");
        }
        return lettre;
    }
}
