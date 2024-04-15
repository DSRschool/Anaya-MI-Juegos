package buscaminas;

import java.awt.GridLayout;
import javax.swing.JPanel;

public class Casillero extends JPanel {

	private static final long serialVersionUID = -6658073821475084359L;

	public Casillero(Tablero tablero, JugadaListener listener) {
		int alto = tablero.getAlto();
		int ancho = tablero.getAncho();
		
		setLayout(new GridLayout(alto, ancho));
		
		for (int i = 0; i < alto; i++) {
			for (int j = 0; j < ancho; j++) {
				add(new Casilla(tablero.getCelda(i, j), listener));
			}
		}
	}
}
