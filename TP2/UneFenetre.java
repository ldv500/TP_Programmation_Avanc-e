import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UneFenetre extends JFrame {
    private UnMobile sonMobile1;
    private UnMobile sonMobile2;
    private JButton BoutonArretMarche1 ;
    private JButton BoutonArretMarche2 ;

    public UneFenetre(int largeur, int hauteur) {
        // Définir les propriétés de la fenêtre
        setTitle("Simulation de Mobile");
        setSize(largeur, hauteur);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        setLayout(new GridLayout(4, 1));

        //Création 1er mobile et bouton
        UnMobile sonMobile1 = new UnMobile(largeur, hauteur);
        add(sonMobile1) ;
        Thread threadmobile1 = new Thread(sonMobile1) ;
        threadmobile1.start();

        //Création 2e mobile et bouton
        UnMobile sonMobile2 = new UnMobile(largeur, hauteur);
        add(sonMobile2) ;
        Thread threadmobile2 = new Thread(sonMobile2) ;
        threadmobile2.start();

        // Rendre la fenêtre visible
        setVisible(true);
    }

    public static void main(String[] args) {
        // Créer une instance de UneFenetre pour tester
        new UneFenetre(400, 300);  // taille arbitraire pour la fenêtre
    }
}
