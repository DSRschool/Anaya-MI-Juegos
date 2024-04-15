package memory;

import static memory.Mensajes.NO_JUGABLE;
import static memory.Mensajes.PAREJA;
import static memory.Mensajes.SIGUE_JUGANDO;
import static memory.Mensajes.TERMINADA;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JugadaListener implements ActionListener {

	private Partida partida;
	private TableroGrafico gui;
	private Casilla primeraCasilla;
	private Casilla segundaCasilla;
	private boolean pareja;

	public JugadaListener(Partida partida, TableroGrafico gui) {
		this.partida = partida;
		this.gui = gui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Casilla casilla = (Casilla) e.getSource();
		
		if (casilla.esJugable()) {
			if (segundaCasilla != null) {
				if (!pareja) {
					primeraCasilla.ocultar();
					segundaCasilla.ocultar();
				}
				primeraCasilla = null;
				segundaCasilla = null;
				pareja = false;
			}
			casilla.mostrar();
			if (primeraCasilla == null) {
				primeraCasilla = casilla;
			} else {
				partida.registrarIntento();
				segundaCasilla = casilla;
				if (segundaCasilla.getSimbolo().equals(
						primeraCasilla.getSimbolo())) {
					primeraCasilla.bloquear();
					segundaCasilla.bloquear();
					pareja = true;
					gui.actualizarEstado(PAREJA);
					if (partida.terminada()) {
						gui.actualizarEstado(String.format(
								TERMINADA, partida.getIntentos()));
						return;
					}
				} else {
					pareja = false;
					gui.actualizarEstado(SIGUE_JUGANDO);
				}
			}
		} else {
			gui.actualizarEstado(NO_JUGABLE);
		}
	}
}
