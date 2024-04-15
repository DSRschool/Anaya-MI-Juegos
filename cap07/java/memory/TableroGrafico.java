package memory;

import static memory.Mensajes.EMPIEZA;
import static memory.Mensajes.TITULO;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class TableroGrafico extends JFrame {
	
	private static final long serialVersionUID = -6611725743780259686L;

	private static final int ANCHO_BOTON = 120;
	private static final int ALTO_BOTON = 140;

	private JugadaListener listener;
	
	private Casillero casillero;
	private JLabel barraEstado;
	
	public TableroGrafico(Partida partida) {
		super(TITULO);
		listener = new JugadaListener(partida, this);
		
		casillero = new Casillero(partida, listener);
		add(casillero, BorderLayout.CENTER);
		

		barraEstado = new JLabel(EMPIEZA);
		add(barraEstado, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, ANCHO_BOTON * partida.getAncho(), 
				ALTO_BOTON * partida.getAlto());
		setVisible(true);
	}
	
	protected void actualizarEstado(String mensaje) {
		barraEstado.setText(mensaje);
	}
}
