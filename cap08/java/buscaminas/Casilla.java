package buscaminas;

import java.awt.Color;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Casilla extends JButton {

	private static final long serialVersionUID = 5729276532058609798L;

	private static final Font FONT = 
			new Font("Arial", Font.BOLD, Tamanyos.TAM_FUENTE);
	private static final String[] COLORES = { "#CBE5ED", "#021AFD", 
			"#1D7E00", "#F80701", "#02077F", "#830203",
			"#1E8080", "#464646", "#808080" };

	private final Icon EXPLOSION = 
			new ImageIcon(Casilla.class.getResource("explosion.png"));
	private final Icon MINA = 
			new ImageIcon(Casilla.class.getResource("mina.png"));

	private Celda celda;
	private boolean visible;
	private JugadaListener listener;

	public Casilla(Celda celda, JugadaListener listener) {
		setFont(FONT);
		this.celda = celda;
		celda.setCasilla(this);
		this.listener = listener;
		addMouseListener(listener);
	}

	public Resultado mostrar() {
		visible = true;
		if (!celda.estaMarcada()) {
			if (celda.esMina()) {
				setIcon(EXPLOSION);
				celda.explotar();
				return Resultado.BOMBA;
			} else {
				setText(celda.getContador() + "");
				setForeground(
						Color.decode(COLORES[celda.getContador()]));
				removeMouseListener(listener);
				if (celda.getContador() == 0) {
					destaparContorno();
				}
				return Resultado.BIEN;
			}
		}
		return Resultado.ERROR;
	}

	public void invertirMarca() {
		celda.invertirMarca();
		setIcon(celda.estaMarcada() ? MINA : null);
	}

	public boolean descubierta() {
		return visible;
	}

	private void destaparContorno() {
		for (Celda celdaVecina : celda.getContorno()) {
			Casilla casillaVecina = celdaVecina.getCasilla();
			if (!casillaVecina.descubierta()) {
				casillaVecina.mostrar();
			}
		}
	}
}
