class AffichageSemaphore extends Thread {
    String texte;
    public AffichageSemaphore(String txt){
        texte = txt;
    }
    public void run() {
        for(int i=0; i<texte.length(); i++) {
            System.out.print(texte.charAt(i));
            try {
                sleep(100);
            } catch (InterruptedException e){};
        }
    }
}
