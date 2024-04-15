package memory;

import java.awt.Font;

import javax.swing.JButton;

public class Casilla extends JButton {

	private static final long serialVersionUID = 5729276532058609798L;

	private static final Font FONT = new Font("Arial", Font.BOLD, 100);
	
	private Tarjeta tarjeta;

	public Casilla(Tarjeta tarjeta, JugadaListener listener) {
		setFont(FONT);
		this.tarjeta = tarjeta;
		addActionListener(listener);
	}

	public boolean esJugable() {
		return tarjeta.getEstado().equals(Estado.OCULTA);
	}
	
	public void mostrar() {
		tarjeta.mostrar();
		Simbolo simbolo = tarjeta.getSimbolo();
		setText(simbolo.name());
		setForeground(simbolo.getColor());
	}
	
	public void bloquear() {
		tarjeta.emparejar();
	}

	public void ocultar() {
		tarjeta.ocultar();
		setText("");
	}

	public Simbolo getSimbolo() {
		return tarjeta.getSimbolo();
	}
}
