package matrizes;

import java.util.Vector;

import helma.xmlrpc.WebServer;

public class Server4 extends Matriz {
	@Override
	public Vector sum(int start, int end, Vector soma1, Vector soma2, int dim) {
		// TODO Auto-generated method stub
		return super.sum(start, end, soma1, soma2, dim);
	}

	@Override
	public Vector mult(int start, int end, Vector mult1, Vector mult2, int dim) {
		// TODO Auto-generated method stub
		return super.mult(start, end, mult1, mult2, dim);
	}

	public static void main(String[] args) {

		try {

			System.out.println("Attempting to start XML-RPC Server...");

			WebServer server = new WebServer(8084);
			server.addHandler("atividade3", new Server4());
			server.start();

			System.out.println("Started Server 4 successfully.");
			System.out.println("Accepting requests. (Halt program to stop.)");

		} catch (Exception exception) {
			System.err.println("JavaServer: " + exception);
		}
	}
}