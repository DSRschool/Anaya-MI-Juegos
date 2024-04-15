package lingo.vista;

import static lingo.vista.Textos.MENS_NUEVA;

import javax.swing.JTextField;

public class BarraEstado extends JTextField {

	public BarraEstado() {
		setEditable(false);
		setText(MENS_NUEVA);
	}
	
	public void actualizarEstado(String mensaje) {
		setText(mensaje);
	}
}
