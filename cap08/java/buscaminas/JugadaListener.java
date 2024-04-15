package buscaminas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JugadaListener extends MouseAdapter {

	private Tablero tablero;
	private TableroGrafico gui;
	
	public JugadaListener(Tablero tablero, TableroGrafico gui) {
		this.tablero = tablero;
		this.gui = gui;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Casilla casilla = (Casilla) e.getSource();
		if (tablero.partidaPerdida() || tablero.partidaGanada()) {
			gui.actualizarEstado(Resultado.FIN.getMensaje());
		} else {
			switch (e.getButton()) {
			case MouseEvent.BUTTON1: // botón principal
				Resultado resultado = casilla.mostrar();
				gui.actualizarEstado(resultado.getMensaje());
				if (!tablero.partidaPerdida() && 
						tablero.partidaGanada()) {
					gui.actualizarEstado(
							Resultado.GANADA.getMensaje());
				}
				break;
			case MouseEvent.BUTTON3: // botón secundario
				casilla.invertirMarca();
				gui.actualizarEstado(Resultado.ANOTADO.getMensaje());
				break;
			}
		}
	}
}
