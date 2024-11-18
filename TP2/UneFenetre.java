import javax.swing.*;
import java.awt.*;

public class UneFenetre extends JFrame {
    private UnMobile sonMobile1;
    private UnMobile sonMobile2;
    private JButton BoutonArretMarche1 ;
    private JButton BoutonArretMarche2 ;
    semaphoreBinaire semaphoreBinaire ;

    public UneFenetre(int largeur, int hauteur) {
        // Définir les propriétés de la fenêtre
        setTitle("Simulation de Mobile");
        setSize(largeur, hauteur);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        setLayout(new GridLayout(4, 1));

        // Création du sémaphore
        semaphoreBinaire = new semaphoreBinaire(1) ;

        //Création 1er mobile
        UnMobile sonMobile1 = new UnMobile(largeur, hauteur, semaphoreBinaire);
        add(sonMobile1) ;
        Thread threadmobile1 = new Thread(sonMobile1) ;
        threadmobile1.start();

        //Création 2e mobile
        UnMobile sonMobile2 = new UnMobile(largeur, hauteur, semaphoreBinaire);
        add(sonMobile2) ;
        Thread threadmobile2 = new Thread(sonMobile2) ;
        threadmobile2.start();

        //Création 3e mobile
        UnMobile sonMobile3 = new UnMobile(largeur, hauteur, semaphoreBinaire);
        add(sonMobile3) ;
        Thread threadmobile3 = new Thread(sonMobile3) ;
        threadmobile3.start();

        //Création 4e mobile
        UnMobile sonMobile4 = new UnMobile(largeur, hauteur, semaphoreBinaire);
        add(sonMobile4) ;
        Thread threadmobile4 = new Thread(sonMobile4) ;
        threadmobile4.start();

        // Rendre la fenêtre visible
        setVisible(true);
    }
}
