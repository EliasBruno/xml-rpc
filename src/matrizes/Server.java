package matrizes;

import java.util.Vector;

import helma.xmlrpc.WebServer;

public class Server {

	public Vector sum(Vector soma1, Vector soma2, int m, int n, int p) {
		Vector resultado = new Vector();

		int matrizA[][] = this.convertMatrixToBi(soma1, m);
		int matrizB[][] = this.convertMatrixToBi(soma2, m);

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < m; j++) {
				resultado.add(Integer.parseInt(soma1.get(i).toString()) + Integer.parseInt(soma2.get(i).toString()));
			 }
		}
		return resultado;
	}

	public Vector mult(Vector mult1, Vector mult2, int m, int n, int p) {
		Vector resultado = new Vector();
		int matrizA[][] = this.convertMatrixToBi(mult1, m);
		int matrizB[][] = this.convertMatrixToBi(mult2, m);
		int matrizC[][] = new int[m][m];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < m; j++) {
				for (int k = 0; k < m; k++) {
					matrizC[i][j] += matrizA[i][k] * matrizB[k][j];
				}
			}
		 }
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < m; j++) {
				resultado.add(matrizC[i][j]);
					//System.out.println(matrizC[i][j]);
			}
		}
				
		return resultado;
	}

	public int[][] convertMatrixToBi(Vector vetor, int largura) {
		int altura = vetor.size() / largura;
		int[][] ret = new int[altura][largura];
		for (int i = 0; i < vetor.size(); i++) {
			ret[i / largura][i % largura] = (int) vetor.get(i);
			System.out.println(i);
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