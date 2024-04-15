package tresenrayatexto;

public class Tablero {
	private static final String MARGEN = " ";
	private static final String LINEA_VERTICAL = "|";
	private static final String LINEA_HORIZONTAL = "---";
	private static final String CRUZ = "+";
	private static final String N = System.lineSeparator();
	
	private Ficha[][] tablero;

	public Tablero(int tamanyo) {
		tablero = new Ficha[tamanyo][tamanyo]; // inicializa las casillas a null
	}

	public boolean jugar(Ficha ficha, int fila, int columna) {
		if (tablero[fila][columna] == null) {
			tablero[fila][columna] = ficha;
			return true;
		}
		return false;
	}

	public boolean estaLleno() {
		for (Ficha[] fichas : tablero) {
			for (Ficha ficha : fichas) {
				if (ficha == null) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean gana(Ficha jugador) {
		return ganaHorizontal(jugador) 
				|| ganaVertical(jugador) 
				|| ganaDiagonalDirecta(jugador) 
				|| ganaDiagonalInversa(jugador);
	}

	protected boolean ganaHorizontal(Ficha jugador) {
		boolean gana = false;
		for (int i = 0; i < tablero.length && !gana; i ++) {
			gana = true;
			for (int j = 0; j < tablero[i].length && gana; j ++) {
				gana &= tablero[i][j] == jugador;
			}
		}
		return gana;
	}

	protected boolean ganaVertical(Ficha jugador) {
		boolean gana = false;
		for (int i = 0; i < tablero.length && !gana; i ++) {
			gana = true;
			for (int j = 0; j < tablero[i].length && gana; j ++) {
				gana &= tablero[j][i] == jugador;
			}
		}
		return gana;
	}

	protected boolean ganaDiagonalDirecta(Ficha jugador) {
		boolean gana = true;
		for (int i = 0; i < tablero.length; i ++) {
			gana &= tablero[i][i] == jugador;
		}
		return gana;
	}

	protected boolean ganaDiagonalInversa(Ficha jugador) {
		boolean gana = true;
		for (int i = 0; i < tablero.length; i ++) {
			gana &= tablero[tablero.length-1-i][i] == jugador;
		}
		return gana;
	}
	
	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < tablero.length; i++) {
			// línea de fichas
			for (int j = 0; j < tablero[i].length; j++) {
				s += MARGEN + valueOf(tablero[i][j]) + MARGEN;
				if (j < tablero[i].length - 1) {
					s += LINEA_VERTICAL;
				}
			}
			s += N;
			// línea de separación
			if (i < tablero.length - 1) {
				for (int j = 0; j < tablero[i].length; j++) {
					s += LINEA_HORIZONTAL;
					if (j < tablero[i].length - 1) {
						s += CRUZ;
					}
				}
				s += N;
			}
		}
		return s;
	}

	private Object valueOf(Ficha f) { // devuelve string o ficha
		return f == null ? " " : f; // si ficha llamará a su toString
	}
}
