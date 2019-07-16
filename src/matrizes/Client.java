package matrizes;
import java.util.*;

import helma.xmlrpc.XmlRpcClient;

public class Client {
   public static void main (String [] args) {
   
      try {
         XmlRpcClient server = new XmlRpcClient("http://localhost:8080/RPC2"); 
         Vector params = new Vector();
         
         //cria um objeto Scanner
 		 Scanner scannerKeyboard = new Scanner(System.in);
 		 System.out.println("Por favor, entre com o número de M");
         int m=scannerKeyboard.nextInt();
         
         System.out.println("Por favor entre com o número de N");
         int n=scannerKeyboard.nextInt();
         
         System.out.println("Por favor entre com o número de P");
         int p=scannerKeyboard.nextInt();
         
         System.out.println("Por favor entre com o valor de inicialização A");
         int a=scannerKeyboard.nextInt();
         
         System.out.println("Por favor entre com o número de inicialização B");
         int b=scannerKeyboard.nextInt();
 		 
        Vector vetor1 = new Vector();
 		for (int i = 0; i < m; i++) {
 			for (int j = 0; j < n; j++) {
 				vetor1.add(new Integer(a));
 			}
 		}
 		
 		Vector vetor2 = new Vector();
 		for (int i = 0; i < n; i++) {
 			for (int j = 0; j < p; j++) {
 				vetor2.add(new Integer(b));
 			}
 		}
	     params.addElement(vetor1);
	     params.addElement(vetor2);
	     params.addElement(new Integer(m));
	     params.addElement(new Integer(n));
	     params.addElement(new Integer(p));
	     Object result = server.execute("atividade3.sum", params);
	     Object result2 = server.execute("atividade3.mult", params);
	
	     Vector soma = ((Vector) result);
	     for (int i = 0; i < soma.size(); i++) {
		   System.out.println("A soma é: "+soma);			
		 }
	     
	     Vector mult = ((Vector) result2);
	     for (int i = 0; i < mult.size(); i++) {
		   System.out.println("O produto é: "+mult);			
		 }
 		  
      } catch (Exception exception) {
         System.err.println("JavaClient: " + exception);
      }
   }
}