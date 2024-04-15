package tresenrayatexto;

import java.util.Scanner;

public class TresEnRaya {

	private static final int TAMANYO = 3;

	public static void main(String[] args) {
		Partida partida = new Partida(TAMANYO);
		try (Scanner s = new Scanner(System.in)) {
			do {
				int fila = pideJugada(s, "fila");
				int columna = pideJugada(s, "columna");
				partida.jugar(fila, columna);
				System.out.println(partida);
				if (partida.terminada()) {
					System.out.println("Partida terminada");
					Ficha ganador = partida.ganador();
					if (ganador != null) {
						System.out.println("Ha ganado " + ganador);
					} else {
						System.out.println("Tablas");
					}
					return;
				}
			} while (!partida.terminada());
		}
	}
	
	private static int pideJugada(Scanner s, String tipo) {
		while (true) {
			System.out.println("Indica la " + tipo);
			int pos = s.nextInt();
			if (pos >= 0 && pos < TAMANYO) {
				return pos;
			} else {
				System.err.println("El valor debe estar entre 0 y " 
						+ (TAMANYO - 1));
			}
		}
	}
}
