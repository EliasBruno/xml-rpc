package fractalMandelbrot;
import helma.xmlrpc.WebServer;
import java.util.Vector;

public class Server4 extends Fractal { 
	
   public static void main (String [] args){
   
      try {

         System.out.println("Attempting to start XML-RPC Server...");
         
         WebServer server = new WebServer(8084);
         server.addHandler("atividade2", new Server4());
         server.start();
         
         System.out.println("Started Server 4 successfully.");
         System.out.println("Accepting requests. (Halt program to stop.)");
         
      } catch (Exception exception){
         System.err.println("JavaServer: " + exception);
      }
   }
}