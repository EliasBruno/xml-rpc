package fatorial;

import java.math.BigInteger;
import java.util.*;

import helma.xmlrpc.XmlRpcClient;

public class Client {
	public static void main(String[] args) {

		try {
			XmlRpcClient server = new XmlRpcClient("http://localhost:8080/RPC2");
			Vector params = new Vector();

			// cria um objeto Scanner
			Scanner scannerKeyboard = new Scanner(System.in);
			System.out.println("Por favor entre com o número");
			String number = scannerKeyboard.next();
			if (number.length() > 512) {
				System.out.println("\nNúmeros de caracteres excedem 512");
			} else {
				params.addElement(number);
				Object result = server.execute("sample.fatorial", params);

				// BigInteger sum = ((BigInteger) result);
				System.out.println("O fatorial de " + number + " é " + result);
			}
		} catch (Exception exception) {
			System.err.println("JavaClient: " + exception);
		}
	}
}