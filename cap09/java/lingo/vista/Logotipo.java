package lingo.vista;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Logotipo extends JPanel {
	private static final String FICHERO_LOGO = "minilingo.gif";

	public Logotipo() {
		JLabel logo = new JLabel(new ImageIcon(
				LingoGrafico.class.getResource(FICHERO_LOGO)));
		add(logo);
	}
}
