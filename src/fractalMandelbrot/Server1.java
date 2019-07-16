package fractalMandelbrot;
import helma.xmlrpc.WebServer;
import java.util.Vector;

public class Server1 extends Fractal{ 
	
   public static void main (String [] args){
   
      try {

         System.out.println("Attempting to start XML-RPC Server...");
         
         WebServer server = new WebServer(8081);
         server.addHandler("atividade2", new Server1());
         server.start();
         
         System.out.println("Started Server 1 successfully.");
         System.out.println("Accepting requests. (Halt program to stop.)");
         
      } catch (Exception exception){
         System.err.println("JavaServer: " + exception);
      }
   }
}