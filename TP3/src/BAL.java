public class BAL {
    private String lettre ;
    private boolean estVide ;

    public synchronized void deposer (String lettre) {
        try {
            while(!estVide){
                wait();
            }
            estVide = false ;
            this.lettre = lettre ;
        } catch(InterruptedException e){}
    }

    public synchronized String retirer () {
        String returnLettre = null;
        try {
            while(estVide){
                wait();
            }
            returnLettre = this.lettre;
            estVide = true ;
        } catch (InterruptedException e) {}
        return returnLettre ;
    }
}
