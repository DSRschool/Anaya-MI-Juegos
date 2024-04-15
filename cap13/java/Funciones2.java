public class Funciones2 {
	private static void saludar(String nombre) {
		System.out.println("Hola " + nombre + "!");
	}
	
	private static void saludar() {
		System.out.println("Hola tú!");
	}

	public static void main(String[] args) {
		saludar("Pepe");
		saludar("Pepa");
		saludar();
	}
}
