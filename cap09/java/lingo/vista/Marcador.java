package lingo.vista;

import static lingo.vista.Textos.PALABRAS_GANADAS;
import static lingo.vista.Textos.PALABRAS_JUGADAS;
import static lingo.vista.Textos.PUNTUACION_LINEA;
import static lingo.vista.Textos.PUNTUACION_PALABRA;
import static lingo.vista.Textos.PUNTUACION_TOTAL;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import lingo.modelo.Puntuacion;

public class Marcador extends JPanel {
	private static final String PUNTUACION_INICIAL = "0";
	private static final int NUM_ELEMS = 10;
	
	private JLabel puntuacionLineaTxt;
	private JLabel puntuacionLineaVal;
	private JLabel puntuacionPalabraTxt;
	private JLabel puntuacionPalabraVal;
	private JLabel puntuacionTotalTxt;
	private JLabel puntuacionTotalVal;
	private JLabel numeroPalabrasTxt;
	private JLabel numeroPalabrasVal;
	private JLabel palabrasGanadasTxt;
	private JLabel palabrasGanadasVal;
	
	private Puntuacion puntuacion;

	public Marcador(Puntuacion puntuacion) {
		this.puntuacion = puntuacion;
		
		puntuacionLineaTxt = new JLabel(PUNTUACION_LINEA);
		puntuacionLineaVal = 
				new JLabel(PUNTUACION_INICIAL, JLabel.CENTER);
		puntuacionPalabraTxt = new JLabel(PUNTUACION_PALABRA);
		puntuacionPalabraVal = 
				new JLabel(PUNTUACION_INICIAL, JLabel.CENTER);
		puntuacionTotalTxt = new JLabel(PUNTUACION_TOTAL);
		puntuacionTotalVal = 
				new JLabel(PUNTUACION_INICIAL, JLabel.CENTER);
		numeroPalabrasTxt = new JLabel(PALABRAS_JUGADAS);
		numeroPalabrasVal = 
				new JLabel(PUNTUACION_INICIAL, JLabel.CENTER);
		palabrasGanadasTxt = new JLabel(PALABRAS_GANADAS);
		palabrasGanadasVal = 
				new JLabel(PUNTUACION_INICIAL, JLabel.CENTER);
		
		setLayout(new GridLayout(NUM_ELEMS, 1));
		add(puntuacionLineaTxt);
		add(puntuacionLineaVal);
		add(puntuacionPalabraTxt);
		add(puntuacionPalabraVal);
		add(puntuacionTotalTxt);
		add(puntuacionTotalVal);
		add(numeroPalabrasTxt);
		add(numeroPalabrasVal);
		add(palabrasGanadasTxt);
		add(palabrasGanadasVal);
	}
	
	public void repintarPuntuacion() {
		puntuacionLineaVal.setText(puntuacion.getPuntuacionLinea());
		puntuacionPalabraVal.setText(puntuacion.getPuntuacionPalabra());
		puntuacionTotalVal.setText(puntuacion.getPuntuacionTotal());
		numeroPalabrasVal.setText(puntuacion.getPalabrasTotales());
		palabrasGanadasVal.setText(puntuacion.getPalabrasGanadas());
	}
}
