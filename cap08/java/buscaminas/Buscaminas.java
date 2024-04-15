package buscaminas;

import static buscaminas.Mensajes.NUMERO_COLUMNAS;
import static buscaminas.Mensajes.NUMERO_FILAS;
import static buscaminas.Mensajes.NUMERO_MINAS;

import java.util.Scanner;

public class Buscaminas {

	public static void main(String[] args) {
		int alto;
		int ancho;
		int numMinas;
		if (args.length == 3) {
			alto = Integer.parseInt(args[0]);
			ancho = Integer.parseInt(args[1]);
			numMinas = Integer.parseInt(args[2]);
		} else {
			try (Scanner sc = new Scanner(System.in)) {
				System.out.println(NUMERO_FILAS);
				alto = sc.nextInt();
				System.out.println(NUMERO_COLUMNAS);
				ancho = sc.nextInt();
				System.out.println(NUMERO_MINAS);
				numMinas = sc.nextInt();
			}
		}
		try {
			new Tablero(alto, ancho, numMinas);
		} catch (CifrasException ce) {
			System.err.println(ce.getMessage());
		}
	}
}
