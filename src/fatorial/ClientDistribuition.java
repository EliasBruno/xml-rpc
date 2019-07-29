package fatorial;

import java.math.BigInteger;
import java.util.*;

import helma.xmlrpc.XmlRpcClient;

public class ClientDistribuition extends Thread {
	public int server;
	public static BigInteger numero;
	public static ArrayList<Object> vetor = new ArrayList<>();
	
	public ClientDistribuition(int server) {
		this.server = server;

	}

	public static void main(String[] args) {

		try {
			// cria um objeto Scanner
			Scanner scannerKeyboard = new Scanner(System.in);
			System.out.println("Por favor entre com o número para cálculo do Fatorial:");
			String number = scannerKeyboard.next();
			numero = new BigInteger(number);
			if (number.length() > 512) {
				System.out.println("\nNúmeros de caracteres excedem 512");
			} else {
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

				System.out.println("Resultados: " + vetor.size());
				BigInteger result = BigInteger.ONE;
				for (int i = 0; i < vetor.size(); i++) {
					result = new BigInteger(vetor.get(i).toString()).multiply(result);
					System.out.println("Valores: " + vetor.get(i));
				}
				System.out.println("Resultado Final: " + result);
			}
		} catch (Exception exception) {
			System.err.println("JavaClient: " + exception);
		}
	}

	public void run() {
		try {
			String port = "";
			BigInteger valor = BigInteger.ONE;
			BigInteger start = BigInteger.ONE;
			BigInteger end = BigInteger.ONE;
			switch (this.server) {
			case 1:
				start = BigInteger.ZERO;
				end = numero.divide(BigInteger.valueOf(4)).multiply(BigInteger.ONE);
				port = "8081";
				break;
			case 2:
				start = numero.divide(BigInteger.valueOf(4)).multiply(BigInteger.ONE);
				end = numero.divide(BigInteger.valueOf(4)).multiply(BigInteger.valueOf(2));
				port = "8082";
				break;
			case 3:
				start = numero.divide(BigInteger.valueOf(4)).multiply(BigInteger.valueOf(2));
				end = numero.divide(BigInteger.valueOf(4)).multiply(BigInteger.valueOf(3));
				port = "8083";
				break;
			case 4:
				start = numero.divide(BigInteger.valueOf(4)).multiply(BigInteger.valueOf(3));
				end = numero;
				port = "8084";
				break;
			default:
				break;
			}

			XmlRpcClient xmlRpcClient = new XmlRpcClient("http://localhost:" + port + "/RPC2");
			Vector params = new Vector();
			System.out.println(end+"|"+start);

			params.addElement(end.toString());
			params.addElement(start.toString());
			Object result = xmlRpcClient.execute("atividade1.calculate", params);
			vetor.add(result);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}