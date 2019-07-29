package matrizes;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

import helma.xmlrpc.XmlRpcClient;

public class Client {
	public static void main(String[] args) {

		try {
			XmlRpcClient server = new XmlRpcClient("http://localhost:8080/RPC2");
			Vector params = new Vector();

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

			Vector vetor1 = new Vector();
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					vetor1.add(new Integer(a));
				}
			}
			Vector vetor2 = new Vector();
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < p; j++) {
					vetor2.add(new Integer(b));
				}
			}
			params.addElement(vetor1);
			params.addElement(vetor2);
			params.addElement(new Integer(m));
			params.addElement(new Integer(n));
			params.addElement(new Integer(p));
			Object result = server.execute("atividade3.sum", params);
			Object result2 = server.execute("atividade3.mult", params);

			Vector soma = ((Vector) result);
			FileWriter fileSum = new FileWriter("sumOneServer.txt");
			PrintWriter WfileSum = new PrintWriter(fileSum);
			String text = "";
			//for (int i = 0; i < soma.size(); i++) {
				String resultSum = soma.toString();
				if (!resultSum.isEmpty()) {
					text = resultSum.replaceAll(",", ";").replaceAll("]", "/");
					WfileSum.printf(text);
				}
				System.out.println("A soma é: " + soma);
			//}
			fileSum.close();

			Vector mult = ((Vector) result2);
			FileWriter fileMult = new FileWriter("multiOneServer.txt");
			PrintWriter WfileMult = new PrintWriter(fileMult);
			//for (int i = 0; i < mult.size(); i++) {
				String resultMult = mult.toString();
				if (!resultMult.isEmpty()) {
					text = resultMult.replaceAll(",", ";").replaceAll("]", "/");
					WfileMult.printf(text);
				}
				System.out.println("O produto é: " + mult);
			//}
			fileMult.close();

		} catch (Exception exception) {
			System.err.println("JavaClient: " + exception);
		}
	}
}