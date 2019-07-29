package fatorial;

import java.math.BigInteger;

public class Fatorial {

	public String calculate(String end, String start) {
		BigInteger numeroFactorial = new BigInteger(end.toString());
		BigInteger limitFactorial = new BigInteger(start.toString());
		BigInteger result = BigInteger.ONE;
		while (!numeroFactorial.equals(limitFactorial)) {
			result = result.multiply(numeroFactorial);
			numeroFactorial = numeroFactorial.subtract(BigInteger.ONE);
		}
		return result.toString();
	}
}
