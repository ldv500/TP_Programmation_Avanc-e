import javax.swing.*;
import java.awt.*;

public class UnMobile extends JPanel implements Runnable {
	private int saLargeur, saHauteur, coordMobile, lenSegment;
	private final int sonPas = 10, sonTemps = 50, sonCote = 40;
	private boolean dirDroite = true;  // Variable de direction, True = droite, false = gauche
	semaphoreBinaire semaphoreBinaire ;

	public UnMobile(int telleLargeur, int telleHauteur, semaphoreBinaire semaphoreBinaire) {
		super();
		this.saLargeur = telleLargeur;
		this.saHauteur = telleHauteur;
		this.lenSegment= telleLargeur / 3 ;
		setSize(telleLargeur, telleHauteur);
		this.semaphoreBinaire = semaphoreBinaire ;
	}

	public void run() {
		System.out.println(saLargeur);
		System.out.println(lenSegment);
		while (true) { // Boucle infinie pour que le mouvement continue
			// Avance ou recule en fonction de la direction actuelle
			if (dirDroite) {
				coordMobile += sonPas;
				if (lenSegment <= coordMobile &  coordMobile <= lenSegment + sonPas) {
					semaphoreBinaire.syncWait();
					System.out.println("Entrée SC");
				}
				if (saLargeur - lenSegment <= coordMobile &  coordMobile <= saLargeur- lenSegment + sonPas) {
					semaphoreBinaire.syncSignal();
					System.out.println("Sortie SC");
				}
				if (coordMobile >= saLargeur - sonCote) {
					dirDroite = false;  // Inverse la direction
				}
			} else {
				coordMobile -= sonPas;
				if (saLargeur - lenSegment - sonPas <= coordMobile & coordMobile <= saLargeur -lenSegment ) {
					semaphoreBinaire.syncWait();
					System.out.println("Entrée SC");
				}
				if (lenSegment - sonPas <= coordMobile & coordMobile <= lenSegment){
					semaphoreBinaire.syncSignal();
					System.out.println("Sortie SC");
				}
				if (coordMobile <= 0) {
					dirDroite = true;  // Inverse la direction
				}
			}

			// Redessine le mobile
			repaint();

			try {
				Thread.sleep(sonTemps);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void paintComponent(Graphics telContexteGraphique) {
		super.paintComponent(telContexteGraphique);
		telContexteGraphique.fillRect(coordMobile, saHauteur / 2, sonCote, sonCote);
	}
}