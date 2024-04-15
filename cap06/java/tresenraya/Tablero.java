package tresenraya;

public class Tablero {

	private Ficha[][] tablero;
	
	public Tablero(int tamanyo) {
		tablero = new Ficha[tamanyo][tamanyo];
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
	
	public int getTamanyo() {
		return tablero.length;
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
				gana &= tablero[i][j] == jugador; // TODO = o &= ??
			}
		}
		return gana;
	}

	protected boolean ganaVertical(Ficha jugador) {
		boolean gana = false;
		for (int i = 0; i < tablero.length && !gana; i ++) {
			gana = true;
			for (int j = 0; j < tablero[i].length && gana; j ++) {
				gana &= tablero[j][i] == jugador; // TODO = o &= ??
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
}
