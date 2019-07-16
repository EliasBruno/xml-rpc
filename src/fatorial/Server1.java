package fatorial;
import java.math.BigInteger;

import helma.xmlrpc.WebServer;

public class Server1 { 

	public String fatorial (String end, String start) {
		
		BigInteger numeroFactorial = new BigInteger(end.toString());
		BigInteger limitFactorial = new BigInteger(start.toString());
	    BigInteger result = BigInteger.ONE;
	    
        while(!numeroFactorial.equals(limitFactorial)){
        	result = result.multiply(numeroFactorial);
        	numeroFactorial = numeroFactorial.subtract(BigInteger.ONE);
        }
        //System.out.println("Valor: "+result);
        return result.toString();
    }

   public static void main (String [] args){
   
      try {

         System.out.println("Attempting to start XML-RPC Server...");
         
         WebServer server = new WebServer(8081);
         server.addHandler("atividade1", new Server1());
         server.start();
         
         System.out.println("Started Server 1 successfully.");
         System.out.println("Accepting requests. (Halt program to stop.)");
         
      } catch (Exception exception){
         System.err.println("JavaServer: " + exception);
      }
   }
}