import java.io.*;
import java.net.*;

public class MasterSocket {
	static int maxServer = 8;
	static final int[] tab_port = {25545, 25546, 25547, 25548, 25549, 25550, 25551, 25552};
	static String[] tab_total_workers = new String[maxServer];
	static final String ip = "127.0.0.1";
	static BufferedReader[] reader = new BufferedReader[maxServer];
	static PrintWriter[] writer = new PrintWriter[maxServer];
	static Socket[] sockets = new Socket[maxServer];

	public static void main(String[] args) throws Exception {
		int totalCount = 2000000;
		int total = 0;
		double pi;
		int numWorkers = maxServer;
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		String s;

		System.out.println("\n How many workers for computing PI (< maxServer): ");
		try {
			s = bufferRead.readLine();
			numWorkers = Integer.parseInt(s);
		} catch (IOException ioE) {
			ioE.printStackTrace();
		}

		for (int i = 0; i < numWorkers; i++) {
			sockets[i] = new Socket(ip, tab_port[i]);
			reader[i] = new BufferedReader(new InputStreamReader(sockets[i].getInputStream()));
			writer[i] = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sockets[i].getOutputStream())), true);
		}

		String message_to_send = String.valueOf(totalCount / numWorkers);
		String message_repeat = "y";
		long stopTime, startTime;

		while (message_repeat.equals("y")) {
			startTime = System.currentTimeMillis();
			for (int i = 0; i < numWorkers; i++) {
				writer[i].println(message_to_send);
			}

			for (int i = 0; i < numWorkers; i++) {
				tab_total_workers[i] = reader[i].readLine();
			}

			total = 0;
			for (int i = 0; i < numWorkers; i++) {
				total += Integer.parseInt(tab_total_workers[i]);
			}

			pi = 4.0 * total / totalCount;
			stopTime = System.currentTimeMillis();
			long duration = (stopTime - startTime);
			double error = (Math.abs((pi - Math.PI)) / Math.PI);

			logToCSV(totalCount, numWorkers, duration, error);

			System.out.println("\n Repeat computation (y/N): ");
			try {
				message_repeat = bufferRead.readLine();
			} catch (IOException ioE) {
				ioE.printStackTrace();
			}
		}

		for (int i = 0; i < numWorkers; i++) {
			writer[i].println("END");
			reader[i].close();
			writer[i].close();
			sockets[i].close();
		}
	}

	public static void logToCSV(int totalThrows, int numWorkers, long duration, double error) {
		try (FileWriter fw = new FileWriter("MW_data.txt", true);
			 BufferedWriter bw = new BufferedWriter(fw);
			 PrintWriter out = new PrintWriter(bw)) {
			out.println(error + ";" + totalThrows + ";" + numWorkers + ";" + duration);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
