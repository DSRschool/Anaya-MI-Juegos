package lingo.modelo;

import java.awt.Color;

public enum EstadoLetra {
	DESCONOCIDA(Color.LIGHT_GRAY, 0), 
	MAL(Color.RED, 0), 
	ENCONTRADA(Color.YELLOW, 3), 
	OK(Color.GREEN, 15);

	private Color color;
	private int puntos;

	EstadoLetra(Color color, int puntos) {
		this.color = color;
		this.puntos = puntos;
	}
	
	public Color getColor() {
		return color;
	}
	
	public int getPuntos() {
		return puntos;
	}
}
