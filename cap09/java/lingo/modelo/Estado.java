package lingo.modelo;

public class Estado {
	private int lineaActual;
	private boolean terminada;
	private boolean solucionada;

	public int getLineaActual() {
		return lineaActual;
	}

	public boolean isTerminada() {
		return terminada;
	}

	public boolean isSolucionada() {
		return solucionada;
	}

	public void reiniciarEstado() {
		lineaActual = 0;
		terminada = false;
		solucionada = false;
	}

	public void avanzarLinea() {
		lineaActual++;
	}

	public void terminar() {
		terminada = true;
	}
	
	public void solucionar() {
		solucionada = true;
	}
}
