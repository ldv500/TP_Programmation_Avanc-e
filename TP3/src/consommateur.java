public class consommateur extends Thread {
    private BAL bal;

    public consommateur(BAL bal) {
        this.bal = bal;
    }

    @Override
    public void run() {
        String lettre;
        do {
            lettre = bal.retirer();
            try {
                Thread.sleep(200); // Simulation de délai de consommation
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Consommateur interrompu.");
            }
        } while (!lettre.equals("*"));
    }
}
