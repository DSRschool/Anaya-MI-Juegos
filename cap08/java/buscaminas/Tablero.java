package buscaminas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static buscaminas.Mensajes.DEMASIADAS_MINAS;
import static buscaminas.Mensajes.VALORES_NO_POSITIVOS;

public class Tablero {
	private final static double DENSIDAD = 0.3;
	private Random random = new Random();
	private Celda[][] celdas;
	private int alto;
	private int ancho;
	private int numeroMinas;
	private boolean explotado;
	
	public Tablero(int alto, int ancho, int numeroMinas) throws CifrasException {
		this.alto = alto;
		this.ancho = ancho;
		this.numeroMinas = numeroMinas;
		validarCifras();
		celdas = new Celda[alto][ancho];
		for (int i = 0; i < alto; i++) {
			for (int j = 0; j < ancho; j++) {
				celdas[i][j] = new Celda(this, i, j);
			}
		}
		establecerContornos();
		ponerMinas();
		System.out.println(this); // TODO Para Debug
		new TableroGrafico(this); // lanzar interfaz gráfica
	}

	public boolean partidaGanada() {
		int cont = 0;
		for (Celda[] fila : celdas) {
			for (Celda celda : fila) {
				Casilla casilla = celda.getCasilla();
				if (casilla.descubierta()) {
					cont++;
				}
			}
		}
		return cont == ancho * alto - numeroMinas;
	}
	
	public boolean partidaPerdida() {
		return explotado;
	}
	
	public void explotar() {
		explotado = true;
	}
	
	public int getAlto() {
		return alto;
	}
	
	public int getAncho() {
		return ancho;
	}

	public Celda getCelda(int fila, int columna) {
		return celdas[fila][columna];
	}

	public int getNumeroMinas() {
		return numeroMinas;
	}

	private void validarCifras() throws CifrasException {
		if (numeroMinas <= 0 || alto <= 0 || ancho <= 0) {
			throw new CifrasException(VALORES_NO_POSITIVOS);
		}
		if (numeroMinas > alto * ancho * DENSIDAD) {
			throw new CifrasException(DEMASIADAS_MINAS);
		}
	}

	private void establecerContornos() {
		for (Celda[] fila : celdas) {
			for (Celda celda : fila) {
				establecerContorno(celda);
			}
		}
	}
	
	private void establecerContorno(Celda celda) {
		List<Celda> contorno = new ArrayList<>();
		int x = celda.getX();
		int y = celda.getY();
		int xIni = (x != 0) ? x - 1 : 0;
		int xFin = (x != alto - 1) ? x + 1 : alto - 1;
		int yIni = (y != 0) ? y - 1 : 0;
		int yFin = (y != ancho - 1) ? y + 1 : ancho - 1;
		for (int i = xIni; i <= xFin; i++) {
			for (int j = yIni; j <= yFin; j++) {
				contorno.add(celdas[i][j]);
			}
		}
		celda.setContorno(contorno);
	}
	
	private void ponerMinas() {
		for (int i = 0; i < numeroMinas; i++) {
			boolean hayMina;
			do {
				int x = random.nextInt(alto);
				int y = random.nextInt(ancho);
				Celda celda = celdas[x][y];
				hayMina = celda.esMina();
				if (!hayMina) {
					celda.ponerMina();
				}
			} while (hayMina);
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Celda[] fila : celdas) {
			for (Celda celda : fila) {
				sb.append(celda);
			}
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}
}
