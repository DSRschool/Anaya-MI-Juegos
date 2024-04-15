package lingo.modelo;

import java.util.Random;

public class Lingo {
	public static final Random RANDOM = new Random(123); // TODO

	private Configuracion configuracion;
	private Palabra objetivo;
	private Puntuacion puntuacion;
	private Estado estado;
	private Jugada jugada;

	public Lingo() {
		configuracion = new Configuracion();
		puntuacion = new Puntuacion();
		estado = new Estado();
	}

	public Configuracion getConfiguracion() {
		return configuracion;
	}

	public Palabra getObjetivo() {
		return objetivo;
	}

	public Puntuacion getPuntuacion() {
		return puntuacion;
	}

	public Estado getEstado() {
		return estado;
	}
	
	public Jugada getJugada() {
		return jugada;
	}

	public int getLongitudPalabra() {
		return configuracion.getLongitudPalabra();
	}

	public int getNumeroLineas() {
		return configuracion.getNumeroLineas();
	}

	public Palabra nuevaPalabra() throws NoDiccionarioException {
		int longitud = configuracion.comprobarLongitud();
		if (Diccionario.getLongitudPalabra() != longitud) {
			Diccionario.cargarDiccionario(longitud);
		}
		puntuacion.nuevaPalabra(configuracion.isAleatoria());
		estado.reiniciarEstado();
		objetivo = new Palabra(Diccionario.obtenerPalabra());
		System.out.println(objetivo); // TODO no mostrar
		return objetivo;
	}

	public Jugada jugar(String texto) {
		texto = texto.toUpperCase();
		if (Diccionario.palabraValida(texto)) {
			Palabra entrada = new Palabra(texto);
			Jugada resultado = objetivo.comparar(entrada);
			puntuacion.puntuar(resultado);
			if (resultado.isSolucionada()) {
				estado.solucionar();
			}
			jugada = resultado;
		} else {
			jugada = Jugada.PALABRA_INVALIDA;
		}
		if (estado.getLineaActual() == getNumeroLineas() - 1) {
			estado.terminar();
		}
		return jugada;
	}

	public void avanzarLinea() {
		estado.avanzarLinea();
	}

	public void solucionar() {
		puntuacion.solucionar(estado.getLineaActual(), 
				configuracion.getLongitudPalabra());
	}

	public void otraPista() {
		puntuacion.otraPista();
	}

	public void alternarAleatoria() {
		configuracion.alternarAleatoria();
	}

	public void nuevaLongitud(int nuevaLongitud) {
		configuracion.nuevaLongitud(nuevaLongitud);
	}
}
