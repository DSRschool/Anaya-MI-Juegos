import java.util.Random;
import java.util.Scanner;

public class Adivina {

	public static void main(String[] args) {
		// Título del juego
		System.out.println("=== Adivina un número del 1 al 100 ===");
		
		// Creación de la instancia de Random
		Random r = new Random();
		// Generación del número aleatorio
		int objetivo = r.nextInt(1, 101);
		// Chuleta para pruebas, mantener comentado
//		System.out.println(objetivo);
		
		// Iniciación del Scanner para la lectura del teclado
		Scanner s = new Scanner(System.in);

		// Inicialización de variables auxiliares
		boolean adivinado = false;
		int contador = 0;
		
		// Bucle para repetir hasta que no se haya adivinado
		while (!adivinado) {
			// Incremento del contador de iteraciones
			contador++;
			// Petición de jugada al usuario como entero
			System.out.print("Dime un número del 1 al 100: ");
			int numero = s.nextInt();
			
			// Evaluación
			if (numero == objetivo) { // Acierto
				System.out.println("¡¡Bien!! Era " + numero);
				adivinado = true;
			} else {
				if (numero < objetivo) { // Número demasiado pequeño
					System.out.println("Mayor, mayor");
				} else { // Número demasiado grande
					System.out.println("Menor, menor");
				} // if mayor/menor
			} // if acierto
		} // while
		
		// Al terminar, indicación del número de intentos
		System.out.println("Lo has conseguido en " + contador + " intentos.");
		// Cierre del escáner (aprenderemos a hacerlo mejor)
		s.close();
	} // main
}
