package tresenraya;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JugadaListener implements ActionListener {

	private static final String TURNO = "Turno: %s";
	private static final String OCUPADA = "Casilla ocupada - Turno: %s";
	private static final String GANADOR = "¡%s gana!";
	private static final String TERMINADA = "Partida terminada: ganó %s";
	private static final String EMPATE = "Partida terminada: empate";

	private Partida partida;
	private TableroGrafico gui;

	public JugadaListener(Partida partida, TableroGrafico gui) {
		this.partida = partida;
		this.gui = gui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (partida.terminada()) {
			gui.actualizarEstado(
					String.format(TERMINADA, partida.ganador()));
			return;
		}
		Ficha jugador = partida.getTurno();

		Casilla casilla = (Casilla) e.getSource();
		Integer fila = casilla.getFila();
		Integer col = casilla.getColumna();
		
		if (partida.jugar(fila, col)) {
			casilla.ocupar(jugador); // actualizar interfaz

			Ficha ganador = partida.ganador();
			if (ganador == null) {
				if (partida.terminada()) {
					gui.actualizarEstado(EMPATE);
				} else {
					gui.actualizarEstado(
						String.format(TURNO, partida.getTurno()));
				}
			} else {
				gui.actualizarEstado(String.format(GANADOR, jugador));
			}
		} else {
			gui.actualizarEstado(String.format(OCUPADA, jugador));
		}
	}
}
