package oo;

public class PruebaExcepcionesKamikaze {
	public static void main(String[] args) throws DatoInvalidoException {
		Calculadora c = new Calculadora();
		System.out.println("// versión kamikaze");
		System.out.println(c.calcularValiente(3, 4, 0));
	}
}
