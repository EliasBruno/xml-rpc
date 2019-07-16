package matrizes;
import java.util.Vector;

import helma.xmlrpc.WebServer;

public class Server1 { 

	public Vector sum(int start, int end,Vector soma1, Vector soma2, int dim) {
		Vector resultado = new Vector();
				
		for (int i = start; i < end; i++) {
			for (int j = start; j < end ; j++) {
				int res = Integer.parseInt(soma1.get(i).toString()) + Integer.parseInt(soma2.get(j).toString());
				resultado.add(res);
			}
		}
		return resultado;
    }
	
	public Vector mult(Vector mult1, Vector mult2) { 	
	   	Vector resultado = new Vector();
	    
		int matrizA[][] = this.convertMatrixToBi(mult1,4);
		int matrizB[][] = this.convertMatrixToBi(mult2,4);   	
		int start = 0, end = 1; 
		for(int i=start;i< end;i++) {
			for(int j=0;j< 4;j++){
	           for(int k=0;k < 4;k++){
				   int res = (matrizA[i][k] * matrizB[k][j]);
				   //System.out.println("Multiplicação 1: "+matrizA[i][k]+"|"+matrizB[k][j]);
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
   
   public static void main (String [] args){
   
      try {

         System.out.println("Attempting to start XML-RPC Server...");
         
         WebServer server = new WebServer(8081);
         server.addHandler("atividade3", new Server1());
         server.start();
         
         System.out.println("Started Server 1 successfully.");
         System.out.println("Accepting requests. (Halt program to stop.)");
         
      } catch (Exception exception){
         System.err.println("JavaServer: " + exception);
      }
   }
}