package matrizes;

import java.util.Vector;

public class Matriz {

	public Vector sum(int start, int end, Vector soma1, Vector soma2, int dim) {
		Vector resultado = new Vector();
		int matrizA[][] = this.convertMatrixToBi(soma1, dim);
		int matrizB[][] = this.convertMatrixToBi(soma2, dim);

		for (int i = start; i < end; i++) {
			for (int j = 0; j < dim; j++) {
				resultado.add((matrizA[i][j] + matrizB[i][j]));
			}
		}
		return resultado;
	}

	public Vector mult(int start, int end, Vector mult1, Vector mult2, int dim) {
		Vector resultado = new Vector();

		int matrizA[][] = this.convertMatrixToBi(mult1, dim);
		int matrizB[][] = this.convertMatrixToBi(mult2, dim);
		for (int i = start; i < end; i++) {
			for (int j = 0; j < dim; j++) {
				for (int k = 0; k < dim; k++) {
					resultado.add((matrizA[i][k] * matrizB[k][j]));
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
}
