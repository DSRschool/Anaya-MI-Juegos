package memory;

public class Partida {
	private Tablero tablero;
	private int intentos;

	public Partida(int alto, int ancho) throws DimensionesException {
		tablero = new Tablero(alto, ancho); // inicializar tablero lógico
		new TableroGrafico(this); // lanzar interfaz gráfica
	}

	public void registrarIntento() {
		intentos++;
	}

	public boolean terminada() {
		return tablero.estaLleno();
	}

	public int getAlto() {
		return tablero.getAlto();
	}

	public int getAncho() {
		return tablero.getAncho();
	}
	
	public int getIntentos() {
		return intentos;
	}
	
	public Tarjeta getTarjeta(int fila, int columna) {
		return tablero.getTarjeta(fila, columna);
	}
}
