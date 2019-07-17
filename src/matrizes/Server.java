package matrizes;

import java.util.Vector;

import helma.xmlrpc.WebServer;

public class Server {

	public Vector sum(Vector soma1, Vector soma2, int m, int n, int p) {
		Vector resultado = new Vector();

		int matrizA[][] = this.convertMatrixToBi(soma1, 2);
		int matrizB[][] = this.convertMatrixToBi(soma2, 2);

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				resultado.add(matrizA[i][j] + matrizB[i][j]);
			}
		}
		return resultado;
	}

	public Vector mult(Vector mult1, Vector mult2, int m, int n, int p) {
		Vector resultado = new Vector();
		int matrizA[][] = this.convertMatrixToBi(mult1, 2);
		int matrizB[][] = this.convertMatrixToBi(mult2, 2);

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					resultado.add(matrizA[i][k] * matrizB[k][j]);
				}
			}
		}

		return resultado;
	}

	public int[][] convertMatrixToBi(Vector vetor, int tam) {
		int altura = vetor.size() / tam;
		int[][] ret = new int[altura][tam];
		for (int i = 0; i < vetor.size(); i++) {
			ret[i / tam][i % tam] = (int) vetor.get(i);
		}
		return ret;
	}

	public static void main(String[] args) {

		try {

			System.out.println("Attempting to start XML-RPC Server...");

			WebServer server = new WebServer(8080);
			server.addHandler("atividade3", new Server());
			server.start();

			System.out.println("Started successfully.");
			System.out.println("Accepting requests. (Halt program to stop.)");

		} catch (Exception exception) {
			System.err.println("JavaServer: " + exception);
		}
	}
}