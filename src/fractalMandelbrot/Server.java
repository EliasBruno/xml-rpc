package fractalMandelbrot;

import helma.xmlrpc.WebServer;
import java.util.Vector;

public class Server {

	public Vector fractalMandelbrot(int width, int height) {
		
		int max = 1000;
		int black = 0x000000, white = 0xFFFFFF;

		Vector vector = new Vector();

		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				double c_re = (col - width / 2) * 4.0 / width;
				double c_im = (row - height / 2) * 4.0 / width;
				double x = 0, y = 0;
				int iterations = 0;
				while (x * x + y * y < 4 && iterations < max) {
					double x_new = x * x - y * y + c_re;
					y = 2 * x * y + c_im;
					x = x_new;
					iterations++;
				}

				if (iterations < max)
					vector.addElement(new String(col + "_" + row + "_" + white));
				else
					vector.addElement(new String(col + "_" + row + "_" + black));
			}
		}
		return vector;
	}

	public static void main(String[] args) {

		try {

			System.out.println("Attempting to start XML-RPC Server...");

			WebServer server = new WebServer(8080);
			server.addHandler("atividade2", new Server());
			server.start();

			System.out.println("Started successfully.");
			System.out.println("Accepting requests. (Halt program to stop.)");

		} catch (Exception exception) {
			System.err.println("JavaServer: " + exception);
		}
	}
}