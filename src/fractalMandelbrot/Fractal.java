package fractalMandelbrot;

import java.util.Vector;

import helma.xmlrpc.XmlRpcClient;

public class Fractal {
	public Vector calculate (int init, int end, int tam){
		int max = 1000;
        int black = 0x000000, white = 0xFFFFFF;

        //BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Vector vector = new Vector();
        
        for (int row = init; row < end; row++) {
        	for (int col = 0; col < tam; col++) {
                double c_re = (col - tam/2)*4.0/tam;
                double c_im = (row - tam/2)*4.0/tam;
                double x = 0, y = 0;
                int iterations = 0;
                while (x*x+y*y < 4 && iterations < max) {
                    double x_new = x*x-y*y+c_re;
                    y = 2*x*y+c_im;
                    x = x_new;
                    iterations++;
                } 
                
                if (iterations < max) vector.addElement(new String(col+"_"+row+"_"+white));
                else vector.addElement(new String(col+"_"+row+"_"+black));
            }
        }
        return vector;
    }
}
