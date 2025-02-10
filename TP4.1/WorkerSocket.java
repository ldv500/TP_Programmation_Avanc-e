

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Worker is a server. It computes PI by Monte Carlo method and sends 
 * the result to Master.
 */
public class WorkerSocket {
    static int port = 25545; // default port
    private static boolean isRunning = true;

    public static void main(String[] args) throws Exception {
        if (!("".equals(args[0]))) port = Integer.parseInt(args[0]);
        System.out.println("Worker started on port " + port);

        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket = serverSocket.accept();

        BufferedReader bRead = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter pWrite = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
        
        String str;
        while (isRunning) {
            str = bRead.readLine(); // Read message from Master
            if (!(str.equals("END"))) {
                System.out.println("Worker received totalCount = " + str);

                // Convert received string to integer
                int totalCount = Integer.parseInt(str);

                // Compute Monte Carlo estimation
                long total = new Master().doRun(Integer.parseInt(str), 1);

                // Send result to Master
                pWrite.println(""+total);
            } else {
                isRunning = false;
            }
        }

        bRead.close();
        pWrite.close();
        socket.close();
        serverSocket.close();
    }
}