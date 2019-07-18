package matrizes;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import helma.xmlrpc.XmlRpcClient;
import helma.xmlrpc.XmlRpcException;

public class ClientDistribuition extends Thread {
	public int server;

	public static ArrayList<Object> vectorResultSoma = new ArrayList<>();
	public static ArrayList<Object> vectorResultMulti = new ArrayList<>();

	public static Vector vetor1 = new Vector();
	public static Vector vetor2 = new Vector();
	public static int m;

	public ClientDistribuition(int server) {
		this.server = server;
	}

	public static void main(String[] args) {

		try {
			// cria um objeto Scanner
			Scanner scannerKeyboard = new Scanner(System.in);

			System.out.println("Por favor, entre com o número de M");
			int m = scannerKeyboard.nextInt();

			System.out.println("Por favor entre com o número de N");
			int n = scannerKeyboard.nextInt();

			System.out.println("Por favor entre com o número de P");
			int p = scannerKeyboard.nextInt();

			System.out.println("Por favor entre com o valor de inicialização A");
			int a = scannerKeyboard.nextInt();

			System.out.println("Por favor entre com o número de inicialização B");
			int b = scannerKeyboard.nextInt();

			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					vetor1.add(new Integer(a));
				}
			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < p; j++) {
					vetor2.add(new Integer(b));
				}
			}
			
			ClientDistribuition thread1 = new ClientDistribuition(1);
			ClientDistribuition thread2 = new ClientDistribuition(2);
			ClientDistribuition thread3 = new ClientDistribuition(3);
			ClientDistribuition thread4 = new ClientDistribuition(4);

			thread1.start();
			thread2.start();
			thread3.start();
			thread4.start();

			thread1.join();
			thread2.join();
			thread3.join();
			thread4.join();

			FileWriter fileSum = new FileWriter("sum.txt");
			PrintWriter WfileSum = new PrintWriter(fileSum);
			String text = "";
			for (int i = 0; i < vectorResultSoma.size(); i++) {
				String resultSum = vectorResultSoma.get(i).toString();
				if (!resultSum.isEmpty()) {
					text = resultSum.replaceAll(",", ";").replaceAll("]", "/");
					WfileSum.printf(text);
				}
				System.out.println("A soma é: " + resultSum);
			}
			fileSum.close();

			FileWriter fileMult = new FileWriter("multi.txt");
			PrintWriter WfileMult = new PrintWriter(fileMult);
			for (int i = 0; i < vectorResultMulti.size(); i++) {
				String resultMult = vectorResultMulti.get(i).toString();
				if (!resultMult.isEmpty()) {
					text = resultMult.replaceAll(",", ";").replaceAll("]", "/");
					WfileMult.printf(text);
				}
				System.out.println("O produto é: " + resultMult);
			}
			fileMult.close();

		} catch (Exception exception) {
			System.err.println("JavaClient: " + exception);
		}
	}

	public void run() {
		try {
			int tam = (vetor1.size()/2);
			int init, end;
			switch (this.server) {
			case 1:				
				end =(tam/4)*1;
				this.callServer(0, end, vetor1, vetor2, 8081);
				break;
			case 2:
				init = (tam/4)*1;
				end =(tam/4)*2;
				this.callServer(init, end, vetor1, vetor2, 8082);
				break;
			case 3:
				init = (tam/4)*2;
				end =(tam/4)*3;
				this.callServer(init, end, vetor1, vetor2, 8083);
				break;
			case 4:
				init = (tam/4)*3;
				init += tam%4;
				this.callServer(init, tam, vetor1, vetor2, 8084);
				break;
			default:
				break;
			}

		} catch (Exception e) {
			System.err.println("Erro Thread: " + e.getMessage());
		}
	}

	public void callServer(int start, int end, Vector vetor1, Vector vetor2, int port) {
		try {
			Vector params = new Vector();
			params.addElement(start);
			params.addElement(end);
			params.addElement(vetor1);
			params.addElement(vetor2);
			params.addElement(2);

			XmlRpcClient xmlRpcClient = new XmlRpcClient("http://localhost:" + port + "/RPC2");
			Object resultSum = xmlRpcClient.execute("atividade3.sum", params);
			vectorResultSoma.add(resultSum);

			Object resultMult = xmlRpcClient.execute("atividade3.mult", params);
			vectorResultMulti.add(resultMult);
		} catch (Exception e) {
			System.err.println("Erro Call Server: " + e.getMessage());
		}
	}
}