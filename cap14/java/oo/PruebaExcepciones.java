package oo;

public class PruebaExcepciones {
	public static void main(String[] args) throws DatoInvalidoException {
		Calculadora c = new Calculadora();
		System.out.println("// versión previsora");
		System.out.println(c.calcularPrevisor(3, 4, 0));
		
		System.out.println("// versión valiente");
		try {
			System.out.println(c.calcularValiente(3, 4, 0));
		} catch (DatoInvalidoException e) {
			System.err.println("Se ha producido un error");
		}
	}
}
