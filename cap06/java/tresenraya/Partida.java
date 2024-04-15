package tresenraya;

public class Partida {
	private Tablero tablero;
	private Ficha turno;

	public Partida(int tamanyo) {
		tablero = new Tablero(tamanyo); // inicializar tablero lógico
		turno = Ficha.values()[
				(int) (Math.random() * Ficha.values().length)
			]; // primer turno aleatorio
		new TableroGrafico(this); // lanzar interfaz gráfica
	}

	public boolean jugar(int fila, int columna) {
		if (tablero.jugar(turno, fila, columna)) {
			turno = turno.siguiente();
			return true;
		}
		return false;
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

	public Ficha getTurno() {
		return turno;
	}

	public int getTamanyo() {
		return tablero.getTamanyo();
	}
}
