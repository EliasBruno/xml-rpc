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

	public int[][] convertMatrixToBi(Vector vetor, int largura) {
		int altura = vetor.size() / largura;
		int[][] ret = new int[altura][largura];
		for (int i = 0; i < vetor.size(); i++) {
			ret[i / largura][i % largura] = (int) vetor.get(i);
		}
		return ret;
	}
}
