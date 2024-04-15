package memory;

import static memory.Mensajes.DIMENSIONES_INVIABLES;
import static memory.Mensajes.NUMERO_COLUMNAS;
import static memory.Mensajes.NUMERO_FILAS;

import java.util.Scanner;

public class MemoryGrafico {

	public static void main(String[] args) {
		int alto;
		int ancho;
		if (args.length == 2) {
			alto = Integer.parseInt(args[0]);
			ancho = Integer.parseInt(args[1]);
		} else {
			try (Scanner sc = new Scanner(System.in)) {
				System.out.println(NUMERO_FILAS);
				alto = sc.nextInt();
				System.out.println(NUMERO_COLUMNAS);
				ancho = sc.nextInt();
			}
		}
		try {
			new Partida(alto, ancho);
		} catch (DimensionesException de) {
			System.err.println(DIMENSIONES_INVIABLES + de.getMessage());
		}
	}
}
