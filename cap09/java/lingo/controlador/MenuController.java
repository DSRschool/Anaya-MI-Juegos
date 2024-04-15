package lingo.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButtonMenuItem;

import lingo.modelo.Lingo;

public class MenuController implements ActionListener {

	private Lingo modelo;

	public MenuController(Lingo modelo) {
		this.modelo = modelo;
	}

	public void actionPerformed(ActionEvent e) {
		JRadioButtonMenuItem item = (JRadioButtonMenuItem) e.getSource();
		int valor = Integer.parseInt(item.getText());
		modelo.nuevaLongitud(valor);
	}
}
