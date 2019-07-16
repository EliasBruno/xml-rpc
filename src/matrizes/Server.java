package matrizes;
import java.util.Vector;

import helma.xmlrpc.WebServer;

public class Server { 

	public Vector sum(Vector soma1, Vector soma2, int m, int n, int p) {
		Vector resultado = new Vector();
				
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				resultado.add(Integer.parseInt(soma1.get(i).toString()) + Integer.parseInt(soma2.get(j).toString()));
			}
		}
		return resultado;
    }
	
   public Vector mult(Vector mult1, Vector mult2, int m, int n, int p) {
		Vector resultado = new Vector();
		int res=0;
		int matrizA[][] = this.convertMatrixToBi(mult1,m);
		int matrizB[][] = this.convertMatrixToBi(mult2,m);
		
		for(int i=0;i< m;i++) {
			for(int j=0;j< n;j++){
	           for(int k=0;k < m;k++){
	        	   res += (matrizA[i][k] * matrizB[k][j]);
	        	   resultado.add(res);
	       		  System.out.println("J "+res);
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
   
   public static void main (String [] args){
   
      try {

         System.out.println("Attempting to start XML-RPC Server...");
         
         WebServer server = new WebServer(8080);
         server.addHandler("atividade3", new Server());
         server.start();
         
         System.out.println("Started successfully.");
         System.out.println("Accepting requests. (Halt program to stop.)");
         
      } catch (Exception exception){
         System.err.println("JavaServer: " + exception);
      }
   }
}