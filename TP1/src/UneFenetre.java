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
        BoutonArretMarche1 = new JButton("Arrêt") ;
        BoutonArretMarche1.setPreferredSize(new Dimension(100, 40));
        add(sonMobile1) ;
        add(BoutonArretMarche1);
        Thread threadmobile1 = new Thread(sonMobile1) ;
        threadmobile1.start();

        //Création 2e mobile et bouton
        UnMobile sonMobile2 = new UnMobile(largeur, hauteur);
        BoutonArretMarche2 = new JButton("Arrêt") ;
        BoutonArretMarche1.setPreferredSize(new Dimension(100, 40));
        add(sonMobile2) ;
        add(BoutonArretMarche2);
        Thread threadmobile2 = new Thread(sonMobile2) ;
        threadmobile2.start();

        //Configuration des boutons
        BoutonArretMarche1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sonMobile1.isEnMarche()) {
                    // Stopper le mobile
                    sonMobile1.setEnMarche(false);
                    BoutonArretMarche1.setText("Start");
                } else {
                    // Relancer le mobile
                    sonMobile1.setEnMarche(true);
                    BoutonArretMarche1.setText("Stop");
                }
            }
        });

        BoutonArretMarche2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sonMobile2.isEnMarche()) {
                    // Stopper le mobile
                    sonMobile2.setEnMarche(false);
                    BoutonArretMarche2.setText("Start");
                } else {
                    // Relancer le mobile
                    sonMobile2.setEnMarche(true);
                    BoutonArretMarche2.setText("Stop");
                }
            }
        });

        // Rendre la fenêtre visible
        setVisible(true);
    }
}
