package fractalMandelbrot;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

import helma.xmlrpc.XmlRpcClient;
import helma.xmlrpc.XmlRpcException;

public class ClientDistribuition extends Thread {
	private static int tam = 1080;
	public int server;
	public static Vector vector = new Vector();
	public static BufferedImage image = new BufferedImage(tam, tam, BufferedImage.TYPE_INT_RGB);

	public ClientDistribuition(int server) {
		this.server = server;
	}

	public static void main(String[] args) {

		try {
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

			ImageIO.write(image, "png", new File("mandelbrotMultiServer.png"));

		} catch (Exception exception) {
			System.err.println("JavaClient: " + exception);
		}
	}

	public void run() {
		try {
			String port = "";
			int init = 0, end = 0;

			switch (this.server) {
			case 1:
				init = 0;
				end = (tam / 4) * 1;
				this.callServer(init, end, 8081);
				populate(vector);
				break;
			case 2:
				init = (tam / 4) * 1;
				end = (tam / 4) * 2;
				this.callServer(init, end, 8082);
				populate(vector);
				break;
			case 3:
				init = (tam / 4) * 2;
				end = (tam / 4) * 3;
				this.callServer(init, end, 8083);
				populate(vector);
				break;
			case 4:
				init = (tam / 4) * 3;
				end = tam;
				this.callServer(init, end, 8084);
				populate(vector);
				break;
			default:
				break;
			}

		} catch (Exception e) {
		}
	}

	public void callServer(int init, int end, int port) {
		try {
			Vector params = new Vector();
			params.addElement(new Integer(init));
			params.addElement(new Integer(end));
			params.addElement(new Integer(tam));
			XmlRpcClient xmlRpcClient = new XmlRpcClient("http://localhost:" + port + "/RPC2");
			Object result = xmlRpcClient.execute("atividade2.calculate", params);
			vector = ((Vector) result);
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	public static void populate(Vector vetor) throws IOException {
		for (int i = 0; i < vetor.size(); i++) {
			String[] new_vector = vetor.get(i).toString().split("_");
			image.setRGB(Integer.parseInt(new_vector[0]), Integer.parseInt(new_vector[1]),
					Integer.parseInt(new_vector[2]));
		}
	}
}