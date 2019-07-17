package fractalMandelbrot;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

import helma.xmlrpc.XmlRpcClient;

public class Client {
	private static int width = 1080;
	private static int height = 1080;

	public static void main(String[] args) {

		try {
			XmlRpcClient server = new XmlRpcClient("http://localhost:8080/RPC2");
			Vector params = new Vector();

			params.addElement(new Integer(width));
			params.addElement(new Integer(height));
			Object result = server.execute("atividade2.fractalMandelbrot", params);

			Vector vector = ((Vector) result);
			populate(vector);

		} catch (Exception exception) {
			System.err.println("JavaClient: " + exception);
		}
	}

	public static void populate(Vector vector){
		try {
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

			for (int i = 0; i < vector.size(); i++) {
				String[] new_vector = vector.get(i).toString().split("_");

				image.setRGB(Integer.parseInt(new_vector[0]), Integer.parseInt(new_vector[1]),
						Integer.parseInt(new_vector[2]));
			}
			ImageIO.write(image, "png", new File("mandelbrotOneServer.png"));
		} catch (Exception e) {
			System.err.println("Erro Populate: " + e.getMessage());
		}
	}
}