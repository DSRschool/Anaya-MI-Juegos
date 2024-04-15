package memory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tablero {

	private static final Simbolo[] SIMBOLOS_DISPONIBLES = Simbolo.values();

	private static final int NUM_REPETICIONES = 2;
	
	private Tarjeta[][] tablero;
	private int alto;
	private int ancho;
	private int numParejas;
	
	public Tablero(int alto, int ancho) throws DimensionesException {
		this.alto = alto;
		this.ancho = ancho;
		validarDimensiones();
		tablero = new Tarjeta[alto][ancho];
		Simbolo[] simbolos = generarListaSimbolos();
		for (int i = 0; i < alto; i++) {
			for (int j = 0; j < ancho; j++) {
				tablero[i][j] = new Tarjeta(simbolos[i * ancho + j]);
			}
		}
	}
	
	private void validarDimensiones() throws DimensionesException {
		if ((alto * ancho) % 2 != 0) {
			throw new CasillasImparesException();
		}
		numParejas = (alto * ancho) / NUM_REPETICIONES;
		if (numParejas > SIMBOLOS_DISPONIBLES.length) {
			throw new SimbolosInsuficientesException();
		}
	}
	
	/**
	 * Genera una lista desordenada de tantos pares de símbolos como 
	 * se necesiten.
	 * @return lista de símbolos desordenados
	 */
	private Simbolo[] generarListaSimbolos() {
		List<Simbolo> simbolos = new ArrayList<>();
		for (int i = 0; i < numParejas; i ++) {
			simbolos.add(SIMBOLOS_DISPONIBLES[i]);
			simbolos.add(SIMBOLOS_DISPONIBLES[i]);
		}
		Collections.shuffle(simbolos);
		return simbolos.toArray(new Simbolo[numParejas * NUM_REPETICIONES]);
	}

	public boolean estaLleno() {
		for (Tarjeta[] tarjetas : tablero) {
			for (Tarjeta tarjeta : tarjetas) {
				if (tarjeta.getEstado() != Estado.EMPAREJADA) {
					return false;
				}
			}
		}
		return true;
	}

	public int getAlto() {
		return alto;
	}
	
	public int getAncho() {
		return ancho;
	}

	public Tarjeta getTarjeta(int fila, int columna) {
		return tablero[fila][columna];
	}
}
