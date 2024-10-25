import javax.swing.JPanel;
import java.awt.Graphics;

public class UnMobile extends JPanel implements Runnable {
	private int saLargeur, saHauteur, coordMobile;
	private final int sonPas = 10, sonTemps = 50, sonCote = 40;
	private boolean dirDroite = true;  // Variable de direction, True = droite, false = gauche
	private boolean enMarche = true; // Variable pour la marche/arrêt

	public UnMobile(int telleLargeur, int telleHauteur) {
		super();
		this.saLargeur = telleLargeur;
		this.saHauteur = telleHauteur;
		setSize(telleLargeur, telleHauteur);
	}

	public void run() {
		while (true) { // Boucle infinie pour que le mouvement continue
			// Avance ou recule en fonction de la direction actuelle
			if(enMarche) {
				if (dirDroite) {
					coordMobile += sonPas;
					if (coordMobile >= saLargeur - sonCote) {
						dirDroite = false;  // Inverse la direction
					}
				} else {
					coordMobile -= sonPas;
					if (coordMobile <= 0) {
						dirDroite = true;  // Inverse la direction
					}
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

	public void setEnMarche(boolean enMarche) {
		this.enMarche = enMarche;
	}

	public boolean isEnMarche() {
		return enMarche;
	}

	public void paintComponent(Graphics telContexteGraphique) {
		super.paintComponent(telContexteGraphique);
		telContexteGraphique.fillRect(coordMobile, saHauteur / 2, sonCote, sonCote);
	}
}