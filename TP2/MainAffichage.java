public class MainAffichage {

    public static void main (String[] args) {

        semaphoreBinaire semaphoreAffichage = new semaphoreBinaire(1);
        AffichageSemaphore TA = new AffichageSemaphore("AAA");
        AffichageSemaphore TB = new AffichageSemaphore("BB");

        // Création des threads des affichages
        Thread threadAffichageTA = new Thread(() -> {
            semaphoreAffichage.syncWait();
            System.out.println("\nJ'entre en section critique");
            TA.run();
            System.out.println("\nJe sors de section critique");
            semaphoreAffichage.syncSignal();
        });
        Thread threadAffichageTB = new Thread(() -> {
            semaphoreAffichage.syncWait();
            System.out.println("\nJ'entre en section critique");
            TB.run();
            System.out.println("\nJe sors de section critique");
            semaphoreAffichage.syncSignal();
        });

        threadAffichageTA.start();
        threadAffichageTB.start();
    }
}
