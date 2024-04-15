package oo;

public class Calculadora {
	
	public int calcularPrevisor(int a, int b, int c) {
		int aux;
		try {
			aux = dividir(b, c);
		} catch (DatoInvalidoException e) {
			aux = 0;
		}
		return a + aux;
	}
	
	public int calcularValiente(int a, int b, int c) 
			throws DatoInvalidoException {
		return a + dividir(b, c);
	}
	
	private int dividir(int a, int b) throws DatoInvalidoException {
		if (b == 0) {
			throw new DatoInvalidoException();
		}
		return a / b;
	}
}
