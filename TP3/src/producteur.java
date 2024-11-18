import java.util.Scanner;

public class producteur extends Thread{

    private BAL Bal ;

    public producteur (BAL Bal) {
        this.Bal = Bal ;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in) ;
        String lettre ;
        System.out.println("Entrez la prochaine lettre (Q pour arrêter");
        lettre = scanner.nextLine() ;
        Bal.deposer(lettre);
    }
}
