package lingo.modelo;

public class Puntuacion {
	private int puntuacionLinea;
	private int puntuacionPalabra;
	private int puntuacionTotal;
	private int palabrasGanadas;
	private int palabrasTotales;
	private int pistas;

	public void nuevaPalabra(boolean aleatoria) {
		puntuacionLinea = 0;
		puntuacionPalabra = 0;
		palabrasTotales++;
		if (aleatoria) {
			pistas = 0; // si es aleatoria no penalizamos
		} else {
			pistas = 1; // descontamos los puntos de la pista			
		}
	}

	public void puntuar(Jugada resultado) {
		puntuacionLinea = 0;
		Palabra entrada = resultado.getEntrada();
		for (Letra letra : entrada.getLetras()) {
			EstadoLetra estado = letra.getEstado();
			puntuacionLinea += estado.getPuntos();
		}
		puntuacionLinea -= EstadoLetra.OK.getPuntos() * pistas;
		puntuacionPalabra += puntuacionLinea;
	}

	public void solucionar(int lineaActual, int numeroLineas) {
		int ratioLinea;
		if (lineaActual == 0) { // esto es suerte
			ratioLinea = 1;
		} else { // cuanto antes, mejor
			ratioLinea = numeroLineas - lineaActual;
		}
		puntuacionPalabra *= ratioLinea;
		puntuacionTotal += puntuacionPalabra;
		palabrasGanadas++;
	}

	public String getPuntuacionLinea() {
		return String.valueOf(puntuacionLinea);
	}

	public String getPuntuacionPalabra() {
		return String.valueOf(puntuacionPalabra);
	}

	public String getPuntuacionTotal() {
		return String.valueOf(puntuacionTotal);
	}

	public String getPalabrasGanadas() {
		return String.valueOf(palabrasGanadas);
	}

	public String getPalabrasTotales() {
		return String.valueOf(palabrasTotales);
	}

	public int getPistas() {
		return pistas;
	}

	public void otraPista() {
		pistas++;
	}
}
