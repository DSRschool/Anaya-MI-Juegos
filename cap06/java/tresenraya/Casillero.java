package tresenraya;

import java.awt.GridLayout;
import javax.swing.JPanel;

public class Casillero extends JPanel {

	private static final long serialVersionUID = -6658073821475084359L;

	private final int tamanyo;

	private Casilla[][] botones;

	public Casillero(Partida partida, JugadaListener listener) {
		this.tamanyo = partida.getTamanyo();
		
		setLayout(new GridLayout(tamanyo, tamanyo));
		
		botones = new Casilla[tamanyo][tamanyo];
		for (int i = 0; i < tamanyo; i++) {
			for (int j = 0; j < tamanyo; j++) {
				botones[i][j] = new Casilla(i, j, listener);
				add(botones[i][j]);
			}
		}
	}
}
