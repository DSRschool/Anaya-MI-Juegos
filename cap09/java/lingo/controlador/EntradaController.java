package lingo.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import lingo.modelo.Jugada;
import lingo.modelo.Lingo;
import lingo.vista.LingoGrafico;

public class EntradaController implements ActionListener {
	
	private Lingo modelo;
	private LingoGrafico vista;
	
	public EntradaController(Lingo modelo, LingoGrafico vista) {
		this.modelo = modelo;
		this.vista = vista;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String entrada = e.getActionCommand();
		Jugada jugada = modelo.jugar(entrada);
		if (jugada.isValida()) {
			vista.pintarJugada();
		} else {
			vista.entradaNoValida(entrada);
		}
		if (modelo.getEstado().isSolucionada()) {
			modelo.solucionar();
			vista.solucionar();
		} else {
			modelo.avanzarLinea();
			vista.prepararLineaSiguiente();
		}
		vista.repintarPuntuacion();
	}
}
