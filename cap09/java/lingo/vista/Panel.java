package lingo.vista;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import lingo.modelo.EstadoLetra;
import lingo.modelo.Letra;
import lingo.modelo.Lingo;
import lingo.modelo.Palabra;

public class Panel extends JPanel {
	private static final int SEP_ELEM = 5;
	
	private JTextField[][] casillas;
	private JLabel[] numeracion;
	
	private Lingo modelo;

	public Panel(Lingo modelo) {
		this.modelo = modelo;

		GridLayout grid = new GridLayout(modelo.getNumeroLineas(), 
				modelo.getLongitudPalabra() + 1);
		grid.setHgap(SEP_ELEM);
		grid.setVgap(SEP_ELEM);
		setLayout(grid);

		generarCasillas();
	}

	private void generarCasillas() {
		casillas = new JTextField
			[modelo.getNumeroLineas()][modelo.getLongitudPalabra()];
		numeracion = new JLabel[modelo.getNumeroLineas()];
		for (int i = 0; i < casillas.length; i++) {
			numeracion[i] = new JLabel(" " + (i + 1) + " ");
			numeracion[i].setForeground(Color.GRAY);
			add(numeracion[i]);
			for (int j = 0; j < casillas[i].length; j++) {
				casillas[i][j] = new JTextField(1);
				casillas[i][j].setEditable(false);
				casillas[i][j].setBackground(null);
				casillas[i][j].setHorizontalAlignment(JTextField.CENTER);
				add(casillas[i][j]);
			}
		}
	}

	public void rellenarCasilla(int linea, int posicion, Letra letra) {
		casillas[linea][posicion].setText(letra.getLetra());
	}

	public void pintarPista() {
		Palabra pista = modelo.getJugada().getEntrada();
		int lineaActual = modelo.getEstado().getLineaActual();
		for (int i = 0; i < modelo.getLongitudPalabra(); i++) {
			Letra letra = pista.getLetra(i);
			EstadoLetra estado = letra.getEstado();
			casillas[lineaActual][i].setBackground(estado.getColor());
			casillas[lineaActual][i].setText(letra.getLetra());
		}
	}

	public void prepararLineaSiguiente() {
		int lineaActual = modelo.getEstado().getLineaActual();
		for (int i = 0; i < modelo.getLongitudPalabra(); i++) { 
			// escribimos las ya colocadas en la siguiente linea
			Letra letra = modelo.getObjetivo().getLetra(i);
			if (letra.getEstado() == EstadoLetra.OK) {
				casillas[lineaActual][i].setText(letra.getLetra());
			}
		}
	}
	
	public void limpiar() {
		for (int i = 0; i < casillas.length; i++) {
			numeracion[i].setText(" " + (i + 1) + " ");
			for (int j = 0; j < casillas[i].length; j++) {
				casillas[i][j].setBackground(null);
				casillas[i][j].setText("");
			}
		}
	}

	public int getTamanyo() {
		return casillas.length;
	}
}
