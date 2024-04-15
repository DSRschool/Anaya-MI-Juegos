package lingo.modelo;

import static lingo.modelo.EstadoLetra.DESCONOCIDA;
import static lingo.modelo.EstadoLetra.ENCONTRADA;
import static lingo.modelo.EstadoLetra.MAL;
import static lingo.modelo.EstadoLetra.OK;

public class Palabra {
	private String palabra;
	private Letra[] letras;
	private int longitudPalabra;

	public Palabra(String palabra) {
		this.palabra = palabra;
		longitudPalabra = this.palabra.length();
		letras = new Letra[longitudPalabra];
		for (int i = 0; i < longitudPalabra; i++) {
			letras[i] = new Letra(this.palabra.charAt(i));
		}
	}

	public String getPalabra() {
		return palabra;
	}

	public Letra[] getLetras() {
		return letras;
	}

	public Letra getLetra(int pos) {
		return letras[pos];
	}

	public void letraColocada(int pos) {
		letras[pos].setEstado(OK);
	}

	public Jugada comparar(Palabra entrada) {
		// clonada es copia del objetivo, pero sin estado
		Palabra clonada = new Palabra(palabra);
		buscarVerdes(entrada, clonada);
		buscarAmarillas(entrada, clonada);
		rechazarDesconocidas(entrada);
		boolean palabraOk = verificarFinalizacion(entrada);
		return new Jugada(entrada, palabraOk);
	}

	private void buscarVerdes(Palabra entrada, Palabra clonada) {
		for (int i = 0; i < longitudPalabra; i++) {
			if (getLetra(i).equals(entrada.getLetra(i))) {
				clonada.letraColocada(i);
				/* this.*/letraColocada(i);
				entrada.letraColocada(i);
			}
		}
	}
	
	private void buscarAmarillas(Palabra entrada, Palabra clonada) {
		for (int i = 0; i < longitudPalabra; i++) {
			Letra letraEntrada = entrada.getLetra(i);
			if (letraEntrada.getEstado() == DESCONOCIDA) {
				// aún no ha sido encontrada
				for (int j = 0; j < longitudPalabra; j++) {
					Letra letraClonada = clonada.getLetra(j);
					if (letraClonada.getEstado() == DESCONOCIDA &&
							letraClonada.equals(letraEntrada)) {
						letraClonada.setEstado(ENCONTRADA);
						letraEntrada.setEstado(ENCONTRADA);
						Letra letraObjetivo = getLetra(j);
						if (letraObjetivo.getEstado() == DESCONOCIDA) {
							// si era desconocida, la ponemos amarilla
							letraObjetivo.setEstado(ENCONTRADA);
						}
						break; // a por la siguiente letra
					}
				}
			}
		}
	}
	
	private void rechazarDesconocidas(Palabra entrada) {
		for (int i = 0; i < longitudPalabra; i++) {
			Letra entradaI = entrada.getLetra(i);
			if (entradaI.getEstado() == DESCONOCIDA) {
				entradaI.setEstado(MAL);
			}
		}
	}
	
	private boolean verificarFinalizacion(Palabra entrada) {
		boolean palabraOk = true;
		for (int i = 0; i < longitudPalabra && palabraOk; i++) {
			palabraOk &= entrada.getLetra(i).getEstado() == OK;
		}
		return palabraOk;
	}
	
	public int darPista() throws NoMasPistasException {
		int restantes = contarNoColocadas();
		if (restantes <= 1) { // no se pueden dar más pistas
			throw new NoMasPistasException();
		}
		Letra letra;
		int pos;
		do {
			pos = Lingo.RANDOM.nextInt(longitudPalabra);
			letra = getLetra(pos);
		} while (letra.getEstado() == OK);

		letra.setEstado(OK); // damos esa pista
		return pos;
	}
	
	private int contarNoColocadas() {
		int cont = 0;
		for (Letra letra : letras) {
			if (letra.getEstado() != OK) {
				cont++;
			}
		}
		return cont;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Letra letra : letras) {
			builder.append(letra);
		}
		return builder.toString();
	}
}
