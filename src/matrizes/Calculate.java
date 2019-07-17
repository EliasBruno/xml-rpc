package matrizes;

import java.util.Vector;

public class Calculate {
	
	public Vector sum(int start, int end,Vector soma1, Vector soma2) {
		Vector resultado = new Vector();
		int matrizA[][] = this.convertMatrixToBi(soma1,4);
		int matrizB[][] = this.convertMatrixToBi(soma2,4);
		
		for (int i = start; i < end; i++) {
			for (int j = start; j < end ; j++) {
				int res = Integer.parseInt(soma1.get(i).toString()) + Integer.parseInt(soma2.get(j).toString());
				resultado.add(res);
			}
		}
		return resultado;
    }
	
	public Vector mult(Integer start, Integer end,Vector mult1, Vector mult2) {
    	System.out.println("Produto: "+start+"|"+end);
	   	Vector resultado = new Vector();

		int matrizA[][] = this.convertMatrixToBi(mult1,4);
		int matrizB[][] = this.convertMatrixToBi(mult2,4);   	
		for(int i=start;i< end;i++) {
			for(int j=0;j< 4;j++){
	           for(int k=0;k < 4;k++){
				   int res = (matrizA[i][k] * matrizB[k][j]);
				   System.out.println("Produto: "+res);
	        	   resultado.add(res);
	           }
			}
		}
		
		return resultado;
   }
   
   public int[][] convertMatrixToBi(Vector vetor, int tam) {
	   int altura = vetor.size() / tam;
	   int[][] ret = new int[altura][tam];
	   for(int i=0; i<vetor.size(); i++) {
		   ret[i/tam][i%tam] = (int) vetor.get(i);
	   }
	   return ret;
   }
}
