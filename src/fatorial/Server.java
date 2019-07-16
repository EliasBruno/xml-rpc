package fatorial;
import java.math.BigInteger;

import helma.xmlrpc.WebServer;

public class Server { 

	public String fatorial (String numero) {
		
		BigInteger valor = new BigInteger(numero.toString());
	    BigInteger result = BigInteger.ONE;
	    
        while(!valor.equals(BigInteger.ONE)){
        	result = result.multiply(valor);
        	valor = valor.subtract(BigInteger.ONE);
        }
        System.out.println("Valor: "+result);
        return result.toString();
    }

   public static void main (String [] args){
   
      try {

         System.out.println("Attempting to start XML-RPC Server...");
         
         WebServer server = new WebServer(8080);
         server.addHandler("sample", new Server());
         server.start();
         
         System.out.println("Started successfully.");
         System.out.println("Accepting requests. (Halt program to stop.)");
         
      } catch (Exception exception){
         System.err.println("JavaServer: " + exception);
      }
   }
}