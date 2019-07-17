package fatorial;

import helma.xmlrpc.WebServer;

public class Server2 extends Fatorial {

	@Override
	public String calculate(String end, String start) {
		// TODO Auto-generated method stub
		return super.calculate(end, start);
	}

	public static void main(String[] args) {

		try {

			System.out.println("Attempting to start XML-RPC Server...");

			WebServer server = new WebServer(8082);
			server.addHandler("atividade1", new Server2());
			server.start();

			System.out.println("Started Server 2 successfully.");
			System.out.println("Accepting requests. (Halt program to stop.)");

		} catch (Exception exception) {
			System.err.println("JavaServer: " + exception);
		}
	}
}