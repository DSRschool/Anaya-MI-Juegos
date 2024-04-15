package lingo.vista;

import static lingo.modelo.Configuracion.MAX_LONG;
import static lingo.modelo.Configuracion.MIN_LONG;
import static lingo.vista.Textos.LONGITUD_PALABRAS;
import static lingo.vista.Textos.OPCIONES;

import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;

import lingo.controlador.MenuController;
import lingo.modelo.Lingo;

public class Menu extends JMenuBar {
	public Menu(Lingo modelo, MenuController listener) {
		// Menu Opciones
		JMenu mOpciones = new JMenu(OPCIONES);
		mOpciones.setMnemonic(KeyEvent.VK_O);
		add(mOpciones);
		
		// SubMenu Longitud palabras
		JMenu mLongsPal = new JMenu(LONGITUD_PALABRAS);
		mLongsPal.setMnemonic(KeyEvent.VK_L);
		ButtonGroup bgLongsPal = new ButtonGroup();
		JRadioButtonMenuItem[] rbmiLongsPal = 
				new JRadioButtonMenuItem[MAX_LONG - MIN_LONG + 1];
		for (int i = 0; i < rbmiLongsPal.length; i++) {
			rbmiLongsPal[i] = 
					new JRadioButtonMenuItem((MIN_LONG + i) + "");
			rbmiLongsPal[i].setMnemonic(0x30 + i + MIN_LONG);
			if (MIN_LONG + i == modelo.getLongitudPalabra()) {
				rbmiLongsPal[i].setSelected(true);
			}
			rbmiLongsPal[i].addActionListener(listener);
			bgLongsPal.add(rbmiLongsPal[i]);
			mLongsPal.add(rbmiLongsPal[i]);
		}
		mOpciones.add(mLongsPal);
	}
}
