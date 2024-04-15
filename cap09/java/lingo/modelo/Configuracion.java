package lingo.modelo;

public class Configuracion {
	public static final int MAX_LONG = 8;
	public static final int MIN_LONG = 4;
	public static final int LONG_INICIAL = 5;

	private int longitudPalabra = LONG_INICIAL;
	private int numeroLineas = longitudPalabra;
	
	private boolean aleatoria = false;
	private boolean distintaLongitud = true;
	private int nuevaLongitud = LONG_INICIAL;

	public void alternarAleatoria() {
		aleatoria = !aleatoria;
	}
	
	public void nuevaLongitud(int nuevaLongitud) {
		distintaLongitud = this.nuevaLongitud != nuevaLongitud;
		this.nuevaLongitud = nuevaLongitud;
	}
	
	public int comprobarLongitud() {
		if (distintaLongitud) {
			longitudPalabra = nuevaLongitud;
			numeroLineas = longitudPalabra;
			distintaLongitud = false;
		}
		return longitudPalabra;
	}
	
	public int getLongitudPalabra() {
		return longitudPalabra;
	}

	public int getNumeroLineas() {
		return numeroLineas;
	}
	
	public boolean isAleatoria() {
		return aleatoria;
	}
}
