package matrizes;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

import helma.xmlrpc.XmlRpcClient;

public class ClientDistribuition extends Thread {
   public int server;
   public String operation;
   
   public static ArrayList<Object> vectorResultSoma = new ArrayList<>();
   public static ArrayList<Object> vectorResultMulti = new ArrayList<>();
   
   public static Vector vetor1 = new Vector();
   public static Vector vetor2 = new Vector();
   public static int m;
   
   public ClientDistribuition(int server) {
	   this.server = server;
   }
   
   public static void main (String [] args) {
   
      try {
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
 		 
 		for (int i = 0; i < m; i++) {
 			for (int j = 0; j < n; j++) {
 				vetor1.add(new Integer(a));
 			}
 		}
 		
 		for (int i = 0; i < n; i++) {
 			for (int j = 0; j < p; j++) {
 				vetor2.add(new Integer(b));
 			}
 		}
 		
		ClientDistribuition thread1 = new ClientDistribuition(1);
		
		ClientDistribuition thread2 = new ClientDistribuition(2);
		
		ClientDistribuition thread3 = new ClientDistribuition(3);
		
		ClientDistribuition thread4 = new ClientDistribuition(4);
		
		thread1.start();
		
		thread2.start();
		
		thread3.start();
		
		thread4.start();
		
		thread1.join();

		thread2.join();

	    thread3.join();

		thread4.join();
		
		FileWriter fileSum = new FileWriter("sum.txt");
	    PrintWriter WfileSum = new PrintWriter(fileSum);
	    String text = "";
	    for (int i = 0; i < vectorResultSoma.size(); i++) {
	      String resultSum = vectorResultSoma.get(i).toString();	
	      if(!resultSum.isEmpty()) {
		      text = resultSum.replaceAll(",", ";").replaceAll("]", "/");
			  WfileSum.printf(text);
	      }
		  System.out.println("A soma é: "+text);	
		}
	    fileSum.close();
	    
	    FileWriter fileMulti = new FileWriter("multi.txt");
	    PrintWriter WfileMulti = new PrintWriter(fileMulti);
	    for (int i = 0; i < vectorResultMulti.size(); i++) {
           String resultMult = vectorResultMulti.get(i).toString();	
           if(!resultMult.isEmpty()) {
 		      text = resultMult.replaceAll(",", ";").replaceAll("]", "/");
	          WfileMulti.printf(text);
           }
	       System.out.println("O produto é: "+text);			
		}
	    fileMulti.close();
 		  
      } catch (Exception exception) {
         System.err.println("JavaClient: " + exception);
      }
   }
   
   public void run() {
	   try{
		   String port = "";
		   int start = 0, end = 0; 
		   switch (this.server) {
			case 1:
				start = 0;
				end = (vetor1.size() / 4) * 1;
			    port = "8081";	
				break;
			case 2:
				start = (vetor1.size() / 4) * 1;
				end = (vetor1.size() / 4) * 2;
			    port = "8082";				
				break;
			case 3:
				start = (vetor1.size() / 4) * 2;
				end = (vetor1.size() / 4) * 3;
			    port = "8083";			
				break;
			case 4:
				start = (vetor1.size() / 4) * 3;
				end = vetor1.size();
			    port = "8084";
				break;
			default:
				break;
		   }
		   
		   XmlRpcClient xmlRpcClient = new XmlRpcClient("http://localhost:"+port+"/RPC2"); 
	       Vector paramsSum = new Vector();
	       
	       paramsSum.addElement(new Integer(start));
	       paramsSum.addElement(new Integer(end));
	       paramsSum.addElement(vetor1);
	       paramsSum.addElement(vetor2);
	       paramsSum.addElement(new Integer(m));
	       
           Object result1 = xmlRpcClient.execute("atividade3.sum", paramsSum);   
    	   vectorResultSoma.add(result1);
	       
	       Vector paramsMulti = new Vector();
	       paramsMulti.addElement(vetor1);
	       paramsMulti.addElement(vetor2);
	       Object result2 = xmlRpcClient.execute("atividade3.mult", paramsMulti);
    	   vectorResultMulti.add(result2);
	       
	   } catch (Exception e){}
   }
}