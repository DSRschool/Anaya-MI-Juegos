package buscaminas;

import static buscaminas.Mensajes.EMPIEZA;
import static buscaminas.Mensajes.TITULO;
import static buscaminas.Tamanyos.ALTO_BOTON;
import static buscaminas.Tamanyos.ANCHO_BOTON;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class TableroGrafico extends JFrame {
	
	private static final long serialVersionUID = -6611725743780259686L;

	private JugadaListener listener;
	
	private Casillero casillero;
	private JLabel barraEstado;
	
	public TableroGrafico(Tablero tablero) {
		super(TITULO);
		listener = new JugadaListener(tablero, this);
		
		casillero = new Casillero(tablero, listener);
		add(casillero, BorderLayout.CENTER);

		barraEstado = new JLabel(
				String.format(EMPIEZA, tablero.getNumeroMinas()));
		add(barraEstado, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, ANCHO_BOTON * tablero.getAncho(), 
				ALTO_BOTON * tablero.getAlto());
		setVisible(true);
	}
	
	protected void actualizarEstado(String mensaje) {
		barraEstado.setText(mensaje);
	}
}
