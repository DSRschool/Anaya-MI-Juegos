public class Funciones1 {
	private static int cuadrado(int n) {
		return n * n;
	}
	
	protected static double hipotenusa(int a, int b) {
		return Math.sqrt(cuadrado(a) + cuadrado(b));
	}
	
	public static void main(String[] args) {
		double hipotenusa = hipotenusa(3, 4);
		System.out.println("La hipotenusa de un triángulo de base 3 y altura 4 es "
				+ hipotenusa);
	}
}
