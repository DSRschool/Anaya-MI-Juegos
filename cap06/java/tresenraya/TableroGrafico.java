package tresenraya;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TableroGrafico extends JFrame {
	
	private static final long serialVersionUID = -6611725743780259686L;

	private static final String TITULO = "3 en raya";
	private static final String EMPIEZA = "Empieza jugando ";

	private JugadaListener listener;
	
	private Casillero casillero;
	private JLabel barraEstado;
	
	public TableroGrafico(Partida partida) {
		super(TITULO);
		listener = new JugadaListener(partida, this);
		
		casillero = new Casillero(partida, listener);
		add(casillero, BorderLayout.CENTER);
		
		barraEstado = new JLabel(EMPIEZA + partida.getTurno());
		add(barraEstado, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 400, 200, 240);
		setVisible(true);
	}
	
	protected void actualizarEstado(String mensaje) {
		barraEstado.setText(mensaje);
	}
}
