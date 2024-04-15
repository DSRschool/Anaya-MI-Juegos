package lingo.modelo;

public class Jugada {
	private Palabra entrada;
	private boolean solucionada;
	private boolean valida;

	public static final Jugada PALABRA_INVALIDA = new Jugada();
	
	public Jugada(Palabra entrada, boolean solucionada) {
		this.entrada = entrada;
		this.solucionada = solucionada;
		this.valida = true;
	}

	/**
	 * Constructor para jugadas con palabras inválidas.
	 */
	private Jugada() {
		this.valida = false;
	}

	public Palabra getEntrada() {
		return entrada;
	}
	
	public boolean isSolucionada() {
		return solucionada;
	}

	public boolean isValida() {
		return valida;
	}
}
