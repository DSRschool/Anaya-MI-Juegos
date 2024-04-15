package lingo.vista;

import javax.swing.JPanel;
import javax.swing.JTextField;

import lingo.controlador.EntradaController;

public class Entrada extends JPanel {
	private static final int LONG_ENTRADA = 12;

	private JTextField campoEntrada;

	public Entrada(EntradaController listener) {
		campoEntrada = new JTextField(LONG_ENTRADA);
		campoEntrada.setEditable(false);
		campoEntrada.addActionListener(listener);
		add(campoEntrada);
	}
	
	public void limpiar() {
		campoEntrada.setText("");
	}

	public void bloquear() {
		campoEntrada.setEditable(false);
	}

	public void abrir() {
		campoEntrada.setEditable(true);
	}
}
