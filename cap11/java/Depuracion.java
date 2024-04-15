public class Depuracion {
	public static void main(String[] args) {
		int a = 3;
		int b = 4;
		int c = suma(a, b);
		int d = suma(b, c);
		System.out.println("d vale " + d);
	}
	
	private static int suma(int num1, int num2) {
		System.out.println("num1 vale " + num1);
		System.out.println("num2 vale " + num2);
		int res = num1 + num2;
		return res;
	}
}
