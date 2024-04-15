package tresenrayatexto;

public class Partida {
	private Tablero tablero;
	private Ficha turno;
	
	public Partida(int tamanyo) {
		tablero = new Tablero(tamanyo);
		turno = Ficha.values()[
				(int) (Math.random() * Ficha.values().length)
			]; // primer turno aleatorio
		System.out.println("Empieza jugando " + turno);
	}

	public void jugar(int fila, int columna) {
		if (tablero.jugar(turno, fila, columna)) {
			turno = turno.siguiente();
		} else {
			System.err.printf("Casilla [%s, %s] ocupada, "
					+ "vuelve a jugar %s%n", fila, columna, turno);
		}
	}
	
	public boolean terminada() {
		return tablero.estaLleno() || ganador() != null;
	}

	public Ficha ganador() {
		for (Ficha f : Ficha.values()) {
			if (tablero.gana(f)) {
				return f;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return tablero + "\nTurno: " + turno;
	}
}
