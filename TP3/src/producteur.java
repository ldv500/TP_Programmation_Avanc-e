import java.util.Scanner;

public class producteur extends Thread {
    private BAL bal;

    public producteur(BAL bal) {
        this.bal = bal;
    }

    @Override
    public void run() {
        String lettres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ*";
        for (char c : lettres.toCharArray()) {
            bal.deposer(String.valueOf(c));
            try {
                Thread.sleep(100); // Simulation de délai de production
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Producteur interrompu.");
            }
        }
    }
}
