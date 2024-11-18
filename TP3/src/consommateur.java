class Consommateur extends Thread {
    private BAL Bal;

    public Consommateur(BAL bal) {
        this.Bal = bal;
    }

    @Override
    public void run() {
        String lettre;
        do {
            lettre = Bal.retirer();
        } while (!lettre.equals("Q"));
    }
}