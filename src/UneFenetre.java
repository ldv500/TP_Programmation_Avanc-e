import javax.swing.JFrame;

public class UneFenetre extends JFrame {
    private UnMobile sonMobile;

    public UneFenetre(int largeur, int hauteur) {
        // Définir les propriétés de la fenêtre
        setTitle("Simulation de Mobile");
        setSize(largeur, hauteur);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Créer l'objet UnMobile
        sonMobile = new UnMobile(largeur, hauteur);

        // Ajouter le mobile à la fenêtre
        add(sonMobile);

        // Créer et démarrer le thread pour le mobile
        Thread threadMobile = new Thread(sonMobile);
        threadMobile.start();

        // Rendre la fenêtre visible
        setVisible(true);
    }

    public static void main(String[] args) {
        // Créer une instance de UneFenetre pour tester
        new UneFenetre(400, 300);  // taille arbitraire pour la fenêtre
    }
}
