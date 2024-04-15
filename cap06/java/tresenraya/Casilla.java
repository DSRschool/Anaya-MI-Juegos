package tresenraya;

import java.awt.Font;
import javax.swing.JButton;

public class Casilla extends JButton {

	private static final long serialVersionUID = 5729276532058609798L;

	private static final Font FONT = new Font("Arial", Font.BOLD, 30);
	
	private int fila;
	private int columna;

	public Casilla(int fila, int col, JugadaListener listener) {
		setFont(FONT);
		this.fila = fila;
		this.columna = col;
		addActionListener(listener);
	}
	
	protected void ocupar(Ficha jugador) {
		setText(jugador.name());
		setForeground(jugador.getColor());
	}

	public int getFila() {
		return fila;
	}

	public int getColumna() {
		return columna;
	}
}
