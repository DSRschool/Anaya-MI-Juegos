import java.util.Random;
import java.util.Scanner;

public class ParesNones {

	public static void main(String[] args) {
		Random r = new Random();
		int ordenador = r.nextInt(1, 3);

		System.out.println("¿1 o 2?");
		Scanner s = new Scanner(System.in);
		String jugador = s.next();
		
		if (!jugador.equals("1") && !jugador.equals("2")) {
			System.err.println("¡Mal!");
		} else {
			int numero = Integer.parseInt(jugador);
			int suma = ordenador + numero;
			
			if (suma % 2 == 0) {
				System.out.println("¡Pares!");
			} else {
				System.out.println("¡Nones!");
			}
			
			System.out.println("El ordenador dijo " + ordenador + " y el jugador " + jugador);
		}
		s.close();
	}
}
