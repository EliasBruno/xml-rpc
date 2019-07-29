package matrizes;

import java.util.Vector;

public class Test {
	public static void main(String[] args)

	{

	            int[] a = new int[]{4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4};

	            //int[][] b1 = dimensionar_uma_em_duas (a, 3);

	            int[][] b2 = dimensionar_uma_em_duas (a, 4);

	            int[][] b3 = dimensionar_uma_em_duas (a, 4);

	            //int[][] b4 = dimensionar_uma_em_duas (a, 6);     
	    		int matrizC[][] = new int[4][4];
	    		Vector resultado = new Vector();

	    		for (int i = 0; i < b3.length; i++) {
	    			for (int j = 0; j < b3.length; j++) {
	    				//System.out.println(matrizA[i][j]);
	    				for (int k = 0; k < b3.length; k++) {
	    					matrizC[i][j] += b3[i][k] * b2[k][j];
	    				}
	    			}
	    		 }
	            
	            for (int i = 0; i < matrizC.length; i++) {
	    			for (int j = 0; j < matrizC.length; j++) {
	    				System.out.println(matrizC[i][j]);
	    			}
	    		}
	      }
	static int[][] dimensionar_uma_em_duas (int[] matriz, int largura)

	{

	            int altura = matriz.length / largura;

	            int[][] ret = new int[altura][largura];

	            for(int i=0; i<matriz.length; i++) {

	                  ret[i/largura][i%largura] = matriz[i];

	            }

	            return ret;

	      }
}
