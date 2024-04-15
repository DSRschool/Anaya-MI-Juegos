package lingo.vista;

import static lingo.vista.Textos.MAS_PISTAS;
import static lingo.vista.Textos.NUEVA;
import static lingo.vista.Textos.PISTA_ALEATORIA;
import static lingo.vista.Textos.SALIR;
import static lingo.vista.Textos.SOLUCION;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import lingo.controlador.BotoneraController;

public class Botonera extends JPanel {

	private static final int NUM_ELEMS = 5;

	private JButton bNueva;
	private JButton bMasPistas;
	private JButton bSolucion;
	private JButton bSalir;
	private JCheckBox cbAleatorio;

	public Botonera(BotoneraController listener) {
		super(new GridLayout(NUM_ELEMS, 1));
		
		bNueva = new JButton(NUEVA);
		bNueva.addActionListener(listener);
		add(bNueva);

		bMasPistas = new JButton(MAS_PISTAS);
		bMasPistas.setEnabled(false);
		bMasPistas.addActionListener(listener);
		add(bMasPistas);

		bSolucion = new JButton(SOLUCION);
		bSolucion.setEnabled(false);
		bSolucion.addActionListener(listener);
		add(bSolucion);

		bSalir = new JButton(SALIR);
		bSalir.addActionListener(listener);
		add(bSalir);

		cbAleatorio = new JCheckBox(PISTA_ALEATORIA);
		cbAleatorio.addActionListener(listener);
		add(cbAleatorio);
	}

	public void resolver() {
		bMasPistas.setEnabled(false);
		bSolucion.setEnabled(false);
	}

	public void inhabilitar() {
		bNueva.setEnabled(false);
		bMasPistas.setEnabled(false);
		bSolucion.setEnabled(false);
	}

	public void noMasPistas() {
		bMasPistas.setEnabled(false);
	}

	public void reiniciar() {
		bMasPistas.setEnabled(true);
		bSolucion.setEnabled(true);
	}
}
