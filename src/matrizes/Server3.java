package matrizes;

import java.util.Vector;

import helma.xmlrpc.WebServer;

public class Server3 extends Calculate {
	@Override
	public Vector sum(int start, int end, Vector soma1, Vector soma2) {
		// TODO Auto-generated method stub
		return super.sum(start, end, soma1, soma2);
	}

	@Override
	public Vector mult(Integer start, Integer end, Vector mult1, Vector mult2) {
		// TODO Auto-generated method stub
		return super.mult(start, end, mult1, mult2);
	}

	public static void main(String[] args) {

		try {

			System.out.println("Attempting to start XML-RPC Server...");

			WebServer server = new WebServer(8083);
			server.addHandler("atividade3", new Server3());
			server.start();

			System.out.println("Started Server 3 successfully.");
			System.out.println("Accepting requests. (Halt program to stop.)");

		} catch (Exception exception) {
			System.err.println("JavaServer: " + exception);
		}
	}
}